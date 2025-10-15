package com.jpmc.midascore.controller;

import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.Service.UserBalanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    private final UserBalanceService userBalanceService;

    public BalanceController(UserBalanceService userBalanceService) {
        this.userBalanceService = userBalanceService;
    }

    @GetMapping("/balance")
    public Balance getBalance(@RequestParam("userId") long userId) {
        double balance = userBalanceService.getBalance(userId); // returns 0 if user not found
        return new Balance(userId, balance);
    }
}
