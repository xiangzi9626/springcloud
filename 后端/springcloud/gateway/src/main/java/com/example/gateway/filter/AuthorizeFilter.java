package com.example.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

// @Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //过滤请求和响应
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //放行白名单
        String[] whiteList = {"/admin/login", "/","/admin/file/img/upload_goods_img","/admin/file/img/editor_upload_img"};
        //获得请求和响应
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 获取请求参数
        //MultiValueMap<String, String> params = request.getQueryParams();
        // 获取参数中的 authorization 参数
        //String auth = params.getFirst("authorization");
        //获取请求的url
        String path = request.getURI().getPath();
        //遍历白名单 放行
        for (String white : whiteList) {
            if (path.equals(white)) {
                return chain.filter(exchange);
            }
        }
        //正则匹配路径
        boolean b = Pattern.matches("^/admin/\\S+$", path);
        if (b) {
            String token = request.getHeaders().get("token").get(0);
            String str = stringRedisTemplate.opsForValue().get("token_" + token)+"";
            if (!str.equals("") && !str.equals("null")) {
                return chain.filter(exchange);
            } else {
                //否，拦截
               // response.setStatusCode(HttpStatus.UNAUTHORIZED);
                String msg = "login";
                DataBuffer wrap = response.bufferFactory().wrap(msg.getBytes());
                return response.writeWith(Mono.just(wrap));
            }
        }
        return chain.filter(exchange);
    }

    //过波器执行顺序 数值越小先执行
    @Override
    public int getOrder() {
        return -1;
    }
}
