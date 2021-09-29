package com.example.clinicaOdontologica.service.auth;

import com.example.clinicaOdontologica.persistence.entities.auth.AppUser;
import com.example.clinicaOdontologica.persistence.entities.auth.AppUserRole;
import com.example.clinicaOdontologica.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordUser = passwordEncoder.encode("user");
        String passwordAdmin = passwordEncoder.encode("admin");

        userRepository.save(new AppUser("Mariel", "mariel", "mariel@digital.com", passwordUser, AppUserRole.USER));
        userRepository.save(new AppUser("Christian", "christian", "christian@digital.com", passwordAdmin, AppUserRole.ADMIN));

    }
}
