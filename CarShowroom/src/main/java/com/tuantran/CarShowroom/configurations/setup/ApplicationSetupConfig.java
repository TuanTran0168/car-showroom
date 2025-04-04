package com.tuantran.CarShowroom.configurations.setup;

import com.tuantran.CarShowroom.entity.Role;
import com.tuantran.CarShowroom.entity.User;
import com.tuantran.CarShowroom.repository.RoleRepository;
import com.tuantran.CarShowroom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationSetupConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @NonFinal
    private final String USERNAME_ADMIN = "admin";
    @NonFinal
    private final String PASSWORD_ADMIN = "123456";
    @NonFinal
    private final String ROLE_ADMIN = "ROLE_ADMIN";

    @NonFinal
    private final String USERNAME_USER = "user";
    @NonFinal
    private final String PASSWORD_USER = "123456";
    @NonFinal
    private final String ROLE_USER = "ROLE_USER";


    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        if ("true".equals(System.getenv("CI"))) {
            log.info("CI environment detected. Skipping master data setup.");
            return args -> {};
        }

        log.info("Initializing Application CarShowroom...");

        return args -> {
            log.info("Checking and inserting default roles...");

            // Role
            if (roleRepository.findByName(ROLE_ADMIN).isEmpty()) {
                log.info("Role '{}' does not exist. Creating now...", ROLE_ADMIN);
                roleRepository.save(Role.builder().name(ROLE_ADMIN).build());
            } else {
                log.info("Role '{}' already exists. Skipping creation.", ROLE_ADMIN);
            }

            if (roleRepository.findByName(ROLE_USER).isEmpty()) {
                log.info("Role '{}' does not exist. Creating now...", ROLE_USER);
                roleRepository.save(Role.builder().name(ROLE_USER).build());
            } else {
                log.info("Role '{}' already exists. Skipping creation.", ROLE_USER);
            }

            Role role_admin = roleRepository.findByName(ROLE_ADMIN).get();
            Role role_user = roleRepository.findByName(ROLE_USER).get();

            log.info("Checking and inserting default users...");

            // User - Admin
            if (userRepository.findByUsername(USERNAME_ADMIN).isEmpty() && role_admin != null) {
                log.info("Admin user '{}' does not exist. Creating now...", USERNAME_ADMIN);
                userRepository.save(User.builder()
                        .name("Trần Đăng Tuấn")
                        .username(USERNAME_ADMIN)
                        .password(passwordEncoder.encode(PASSWORD_ADMIN))
                        .role(role_admin)
                        .build());
            } else {
                log.info("Admin user '{}' already exists. Skipping creation.", USERNAME_ADMIN);
            }

            // User - Hà Đăng Nhuận
            if (userRepository.findByUsername(USERNAME_USER + "1").isEmpty()) {
                log.info("User '{}' does not exist. Creating now...", USERNAME_USER + "1");
                userRepository.save(User.builder()
                        .name("Hà Đăng Nhuận")
                        .username(USERNAME_USER + "1")
                        .password(passwordEncoder.encode(PASSWORD_USER))
                        .role(role_user)
                        .build());
            } else {
                log.info("User '{}' already exists. Skipping creation.", USERNAME_USER + "1");
            }

            // User - Nguyễn Ngọc Phát
            if (userRepository.findByUsername(USERNAME_USER + "2").isEmpty()) {
                log.info("User '{}' does not exist. Creating now...", USERNAME_USER + "2");
                userRepository.save(User.builder()
                        .name("Nguyễn Ngọc Phát")
                        .username(USERNAME_USER + "2")
                        .password(passwordEncoder.encode(PASSWORD_USER))
                        .role(role_user)
                        .build());
            } else {
                log.info("User '{}' already exists. Skipping creation.", USERNAME_USER + "2");
            }

            log.warn("WARNING: CHECK securityFilterChain If you encounter an HTTP status 403 (Forbidden) with an EMPTY RESPONSE body");
            log.info("Application Initialization Completed!");
        };
    }
}
