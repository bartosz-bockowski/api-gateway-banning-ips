package com.example.carparkinggateway.config;

import com.example.carparkinggateway.exception.AccessDeniedException;
import com.example.carparkinggateway.service.BannedIPService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RequiredArgsConstructor
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    private final BannedIPService bannedIPService;

    @Value("${apiKey}")
    private String apiKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String ip = request.getRemoteAddress().getAddress().toString();
        if (bannedIPService.existsByIp(ip)) {
            throw new AccessDeniedException("Access denied - your IP is banned.");
        }

        if (Objects.isNull(request.getHeaders().get("apiKey")) || !Objects.equals(request.getHeaders().get("apiKey").get(0), apiKey)) {
            throw new AccessDeniedException("Access denied - wrong API key.");
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}