package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IncentiveClient {
    private final RestTemplate restTemplate;
    private static final String INCENTIVE_API_URL = "http://localhost:8080/incentive";

    public IncentiveClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public float getIncentive(Transaction transaction) {
        try {
            Incentive incentive = restTemplate.postForObject(INCENTIVE_API_URL, transaction, Incentive.class);
            return incentive != null ? incentive.getAmount() : 0;
        } catch (Exception e) {
            // If the incentive API is not available, return 0
            return 0;
        }
    }
}

