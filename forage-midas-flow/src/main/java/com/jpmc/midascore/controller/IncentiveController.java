package com.jpmc.midascore.controller;// put it under your app's base package

import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/incentive")
public class IncentiveController {
    private static final Logger log = LoggerFactory.getLogger(IncentiveController.class);

    // Simple POST: accept JSON Transaction in request body
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> handleIncentive(@RequestBody Transaction transaction) {
        log.info("Received /incentive POST with transaction: {}", transaction);
        // TODO: real logic here
        return ResponseEntity.ok("{\"status\":\"received\"}");
    }

    // Optional: a quick GET to test mapping in browser
    @GetMapping
    public ResponseEntity<String> getInfo() {
        return ResponseEntity.ok("incentive endpoint alive");
    }
}
