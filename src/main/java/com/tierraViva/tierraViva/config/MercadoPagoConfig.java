package com.tierraViva.tierraViva.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class MercadoPagoConfig {

    @Value("${mercadopago.access_token}")
    private String accessToken;

    @PostConstruct
    public void init() {
        com.mercadopago.MercadoPagoConfig.setAccessToken(accessToken);
    }
}

