package com.example.gateway.filter;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@CommonsLog
public class HeaderPutFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest().mutate()
                .header("X-Token", "top-secret")
                .build();

        final ServerWebExchange modified = exchange.mutate().request(request).build();
        return chain.filter(modified);
    }
}
