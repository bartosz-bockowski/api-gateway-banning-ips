package com.example.carparkinggateway.config;

import com.example.carparkinggateway.service.BannedIPService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final BannedIPService bannedIPService;

    @Bean
    public GlobalFilter customFilter() {
        return new CustomGlobalFilter(bannedIPService);
    }

}
