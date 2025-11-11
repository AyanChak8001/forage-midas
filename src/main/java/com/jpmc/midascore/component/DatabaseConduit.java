package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConduit {
    private final UserRepository userRepository;

    public DatabaseConduit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRecord save(UserRecord userRecord) {
        return userRepository.save(userRecord);
    }

}
