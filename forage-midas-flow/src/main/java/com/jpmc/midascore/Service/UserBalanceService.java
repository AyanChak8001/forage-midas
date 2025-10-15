package com.jpmc.midascore.Service;

import com.jpmc.midascore.repository.UserRepository; // Your JPA repository
import org.springframework.stereotype.Service;

@Service
public class UserBalanceService {

    private final UserRepository userRepository;

    public UserBalanceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Float getBalance(long userId) {
        return userRepository.findById(userId)
                .map(user -> user.getBalance()) // assuming getBalance() returns Float
                .orElse(0f); // 0f for Float literal
    }

}

