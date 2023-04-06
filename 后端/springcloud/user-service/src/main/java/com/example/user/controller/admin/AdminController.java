package com.example.user.controller.admin;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.commons.entity.Role;
import com.example.commons.entity.User;
import com.example.commons.utils.Result;
import com.example.feign.clients.admin.RoleClient;
import com.example.user.service.impl.UserServiceImpl;
import com.example.user.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-09-26
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private RoleClient roleClient;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        User u = userService.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (u == null) {
            return Result.error("用户名或者密码错误!");
        } else if (!SecureUtil.md5(user.getPassword()).equals(u.getPassword())) {
            //System.out.println(SecureUtil.md5(user.getPassword()));
            return Result.error("用户名或者密码错误!");
        }
        String token = JwtUtils.getJwtToken(u.getUsername());
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("token_" + token, u, 2, TimeUnit.DAYS);
        return Result.res(token);
    }

    @RequestMapping("/logout")
    public Map<String, Object> logout(@RequestHeader("token") String token) {
        redisTemplate.delete("token_" + token);
        return Result.success("退出成功");
    }

    @RequestMapping("/admin/add")
    public Map<String, Object> addUser(@RequestBody User user) {
        boolean b = Pattern.matches("^1[0-9]{10}$", user.getPhone());
        if (!b) {
            return Result.error("请输入11位手机号");
        }
       // System.out.println(user.getPhone());
        long u = userService.count(new QueryWrapper<User>().eq("username", user.getUsername()));
        if (u > 0) {
            return Result.error("用户名已存在不可用");
        }
        long u1 = userService.count(new QueryWrapper<User>().eq("phone", user.getPhone()));
        if (u1 > 0) {
            return Result.error("手机号已存在请更换");
        }
        user.setLevel((short) 1);
        user.setPassword(SecureUtil.md5(user.getPassword()));
        boolean save = userService.save(user);
        if (save) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败请重试");
        }
    }

    @RequestMapping("/password")
    public Map<String, Object> modifyPassword(@RequestHeader(value = "token") String token, @RequestBody Map<String, String> param) {
        String oldPassword = param.get("oldPassword");
        String newPassword = param.get("newPassword");
        String confirmPassword = param.get("confirmPassword");
        if (newPassword.length() < 6 || newPassword.length() > 30) {
            return Result.error("请输入6-30位密码");
        }
        if (!newPassword.equals(confirmPassword)) {
            return Result.error("新密码和确认密码输入不一致");
        }
        String username = JwtUtils.getMemberIdByJwtToken(token);
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (!SecureUtil.md5(oldPassword).equals(user.getPassword())) {
            return Result.error("旧密码不正确!");
        }
        Boolean update = userService.update(new UpdateWrapper<User>().eq("username", username).set("password", SecureUtil.md5(newPassword)));
        if (update) {
            redisTemplate.delete("token_" + token);
            return Result.success("密码修改成功");
        } else {
            return Result.error("提交失败请重试");
        }
    }
    @RequestMapping("/admin/password")
    public Map<String, Object> modifyAdminPassword(@RequestBody Map<String, String> param) {
        String newPassword = param.get("newPassword");
        String confirmPassword = param.get("confirmPassword");
        if (newPassword.length() < 6 || newPassword.length() > 30) {
            return Result.error("请输入6-30位密码");
        }
        if (!newPassword.equals(confirmPassword)) {
            return Result.error("新密码和确认密码输入不一致");
        }
        String uid = param.get("uid");
        Boolean update = userService.update(new UpdateWrapper<User>().eq("id", uid).set("password", SecureUtil.md5(newPassword)));
        if (update) {
            return Result.success("密码修改成功");
        } else {
            return Result.error("提交失败请重试");
        }
    }


    @RequestMapping("/admin/list")
    public Map<String, Object> adminList(@RequestBody Map<String, Integer> param) {
        int currentPage = param.get("currentPage");
        int pageSize = param.get("pageSize");
        long total = userService.count(new QueryWrapper<User>().lt("level", 3));
        List<Map<String, Object>> adminList = userService.adminList(3, (currentPage - 1) * pageSize, pageSize);
        List<Role> roleList = roleClient.roleList();
        for (int i = 0; i < adminList.size(); i++) {
            for (int j = 0; j < roleList.size(); j++) {
                String userRid = adminList.get(i).get("r_id")+"";
                String rid = roleList.get(j).getRoleId()+"";
                if (userRid.equals(rid)) {
                    adminList.get(i).put("role_name", roleList.get(j).getRoleName());
                    break;
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", adminList);
        return map;
    }


    @RequestMapping("/admin/edit")
    public Map<String, Object> editUser(@RequestBody Map<String,Object> param) {
        User user=new User();
        user.setUsername(param.get("username")+"");
        user.setPhone(param.get("phone")+"");
        user.setId((Integer) param.get("id"));
        user.setRId(param.get("r_id")+"");
        boolean b = Pattern.matches("^1[0-9]{10}$", user.getPhone());
        if (!b) {
            return Result.error("请输入11位手机号");
        }
        long count = userService.count(new QueryWrapper<User>().ne("id", user.getId()).eq("phone", user.getPhone()));
        if (count > 0) {
            return Result.error("手机号被占用,请更换");
        }
        boolean update = userService.update(new UpdateWrapper<User>().set("phone", user.getPhone()).set("r_id", user.getRId()).eq("id", user.getId()));
        if (update) {
            return Result.success("修改成功");
        } else {
            return Result.error("提交失败请重试");
        }
    }
}
