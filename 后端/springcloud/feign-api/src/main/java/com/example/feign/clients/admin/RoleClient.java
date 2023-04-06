package com.example.feign.clients.admin;

import com.example.commons.entity.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("role")
public interface RoleClient {
    @RequestMapping("/admin/role/get")
   Role getRoleById(@RequestParam("role_id") Integer roleId);
    @RequestMapping("/admin/role/list")
   List<Role> roleList();

    }

