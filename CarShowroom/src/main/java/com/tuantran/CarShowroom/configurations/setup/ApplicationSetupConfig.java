package com.tuantran.CarShowroom.configurations.setup;

import com.tuantran.CarShowroom.entity.*;
import com.tuantran.CarShowroom.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Map;

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
    ApplicationRunner applicationRunner(UserRepository userRepo,
                                        RoleRepository roleRepo,
                                        TypeRepository typeRepo,
                                        BrandRepository brandRepo,
                                        SegmentRepository segmentRepo,
                                        FeatureRepository featureRepo,
                                        FeatureValueRepository featureValueRepo) {
        return args -> {
            log.info("🚗 Initializing CarShowroom Application...");

            // Initialize roles
            Map<String, Role> roles = initDefaultRoles(roleRepo);

            // Initialize users
            initDefaultUsers(userRepo, roles);

            // Initialize types (Sedan, SUV, Hatchback, Truck)
            initDefaultTypes(typeRepo);

            // Initialize brands (Mercedes, Toyota, BMW)
            initDefaultBrands(brandRepo);

            // Initialize segments for each brand
            initDefaultSegments(segmentRepo, brandRepo);

            // Initialize features
            initDefaultFeatures(featureRepo);

            // Initialize feature values for each feature
            initDefaultFeatureValues(featureRepo, featureValueRepo);

            log.warn("⚠️ Check `securityFilterChain` if HTTP 403 with empty body occurs.");
            log.info("✅ Initialization complete.");
        };
    }

    private Map<String, Role> initDefaultRoles(RoleRepository roleRepo) {
        return Map.of(
                ROLE_ADMIN, getOrCreateRole(roleRepo, ROLE_ADMIN),
                ROLE_USER, getOrCreateRole(roleRepo, ROLE_USER)
        );
    }

    private void initDefaultUsers(UserRepository userRepo, Map<String, Role> roles) {
        List<User> defaultUsers = List.of(
                createUser(USERNAME_ADMIN, "Trần Đăng Tuấn", PASSWORD_ADMIN, roles.get(ROLE_ADMIN)),
                createUser(USERNAME_USER + "1", "Hà Đăng Nhuận", PASSWORD_USER, roles.get(ROLE_USER)),
                createUser(USERNAME_USER + "2", "Nguyễn Ngọc Phát", PASSWORD_USER, roles.get(ROLE_USER))
        );

        defaultUsers.forEach(user -> saveIfNotExists(userRepo, user));
    }

    private void initDefaultTypes(TypeRepository typeRepo) {
        List<Type> defaultTypes = List.of(
                createType("Sedan"),
                createType("SUV"),
                createType("Hatchback"),
                createType("Truck")
        );

        defaultTypes.forEach(type -> saveIfNotExists(typeRepo, type));
    }

    private void initDefaultBrands(BrandRepository brandRepo) {
        List<Brand> defaultBrands = List.of(
                createBrand("Mercedes", "Germany", 1926, "https://example.com/mercedes.jpg", "https://www.mercedes-benz.com", "Mercedes-Benz is a global automobile marque and a division of Daimler AG."),
                createBrand("Toyota", "Japan", 1937, "https://example.com/toyota.jpg", "https://www.toyota-global.com", "Toyota is a Japanese multinational automotive manufacturer."),
                createBrand("BMW", "Germany", 1916, "https://example.com/bmw.jpg", "https://www.bmw.com", "BMW is a German multinational company that produces luxury vehicles and motorcycles.")
        );

        defaultBrands.forEach(brand -> saveIfNotExists(brandRepo, brand));
    }

    private void initDefaultSegments(SegmentRepository segmentRepo, BrandRepository brandRepo) {
        Brand mercedes = brandRepo.findByName("Mercedes").orElseThrow(() -> new IllegalStateException("Brand 'Mercedes' not found"));
        Brand toyota = brandRepo.findByName("Toyota").orElseThrow(() -> new IllegalStateException("Brand 'Toyota' not found"));
        Brand bmw = brandRepo.findByName("BMW").orElseThrow(() -> new IllegalStateException("Brand 'BMW' not found"));

        List<Segment> defaultSegments = List.of(
                // Mercedes segments
                createSegment("S Class", "Luxury sedan", "Flagship luxury sedan", mercedes),
                createSegment("E Class", "Mid-size luxury sedan", "Elegant, comfortable and powerful sedan", mercedes),
                createSegment("C Class", "Compact luxury sedan", "Compact and stylish luxury sedan", mercedes),

                // Toyota segments
                createSegment("Corolla", "Compact sedan", "Reliable and fuel-efficient compact sedan", toyota),
                createSegment("Camry", "Mid-size sedan", "Comfortable mid-size sedan", toyota),
                createSegment("Highlander", "Mid-size SUV", "Comfortable and versatile mid-size SUV", toyota),

                // BMW segments
                createSegment("X5", "SUV", "Luxury SUV with powerful performance", bmw),
                createSegment("3 Series", "Sedan", "Sporty luxury sedan", bmw),
                createSegment("M5", "Sports sedan", "High-performance sports sedan", bmw)
        );

        defaultSegments.forEach(segment -> saveIfNotExists(segmentRepo, segment));
    }

    private Type createType(String name) {
        return Type.builder()
                .name(name)
                .build();
    }

    private Brand createBrand(String name, String countryOfOrigin, Integer foundedYear, String imageUrl, String websiteUrl, String description) {
        return Brand.builder()
                .name(name)
                .countryOfOrigin(countryOfOrigin)
                .foundedYear(foundedYear)
                .imageUrl(imageUrl)
                .websiteUrl(websiteUrl)
                .description(description)
                .build();
    }

    private Segment createSegment(String name, String description, String keyFeature, Brand brand) {
        return Segment.builder()
                .name(name)
                .description(description)
                .keyFeature(keyFeature)
                .brand(brand)
                .build();
    }

    private Role getOrCreateRole(RoleRepository repo, String name) {
        return repo.findByName(name).orElseGet(() -> {
            log.info("Creating role '{}'", name);
            return repo.save(Role.builder().name(name).build());
        });
    }

    private User createUser(String username, String name, String rawPassword, Role role) {
        return User.builder()
                .username(username)
                .name(name)
                .password(passwordEncoder.encode(rawPassword))
                .role(role)
                .build();
    }

    private void saveIfNotExists(UserRepository repo, User user) {
        repo.findByUsername(user.getUsername()).ifPresentOrElse(
                u -> log.info("User '{}' already exists", user.getUsername()),
                () -> {
                    log.info("Creating user '{}'", user.getUsername());
                    repo.save(user);
                }
        );
    }

    // Overloaded saveIfNotExists method for Type
    private void saveIfNotExists(TypeRepository repo, Type type) {
        repo.findByName(type.getName()).ifPresentOrElse(
                t -> log.info("Type '{}' already exists", type.getName()),
                () -> {
                    log.info("Creating type '{}'", type.getName());
                    repo.save(type);
                }
        );
    }

    // Overloaded saveIfNotExists method for Brand
    private void saveIfNotExists(BrandRepository repo, Brand brand) {
        repo.findByName(brand.getName()).ifPresentOrElse(
                b -> log.info("Brand '{}' already exists", brand.getName()),
                () -> {
                    log.info("Creating brand '{}'", brand.getName());
                    repo.save(brand);
                }
        );
    }

    // Overloaded saveIfNotExists method for Segment
    private void saveIfNotExists(SegmentRepository repo, Segment segment) {
        repo.findByName(segment.getName()).ifPresentOrElse(
                s -> log.info("Segment '{}' already exists", segment.getName()),
                () -> {
                    log.info("Creating segment '{}'", segment.getName());
                    repo.save(segment);
                }
        );
    }

    private void initDefaultFeatures(FeatureRepository featureRepo) {
        List<Feature> features = List.of(
                Feature.builder().name("Số chỗ ngồi").build(),
                Feature.builder().name("Loại nhiên liệu").build(),
                Feature.builder().name("Màu sắc").build(),
                Feature.builder().name("Hệ dẫn động").build()
        );

        features.forEach(f -> featureRepo.findByName(f.getName()).ifPresentOrElse(
                existing -> log.info("Feature '{}' already exists", f.getName()),
                () -> {
                    log.info("Creating feature '{}'", f.getName());
                    featureRepo.save(f);
                }
        ));
    }

    private void initDefaultFeatureValues(FeatureRepository featureRepo, FeatureValueRepository featureValueRepo) {
        featureRepo.findByName("Số chỗ ngồi").ifPresent(feature -> {
            List<FeatureValue> values = List.of("4 chỗ", "5 chỗ", "7 chỗ", "16 chỗ")
                    .stream().map(v -> FeatureValue.builder().name(v).feature(feature).build()).toList();
            values.forEach(v -> saveIfNotExists(featureValueRepo, v));
        });

        featureRepo.findByName("Loại nhiên liệu").ifPresent(feature -> {
            List<FeatureValue> values = List.of("Xăng", "Dầu", "Điện", "Hybrid")
                    .stream().map(v -> FeatureValue.builder().name(v).feature(feature).build()).toList();
            values.forEach(v -> saveIfNotExists(featureValueRepo, v));
        });

        featureRepo.findByName("Màu sắc").ifPresent(feature -> {
            List<FeatureValue> values = List.of("Xanh", "Đỏ", "Trắng", "Bạc")
                    .stream().map(v -> FeatureValue.builder().name(v).feature(feature).build()).toList();
            values.forEach(v -> saveIfNotExists(featureValueRepo, v));
        });
    }

    private void saveIfNotExists(FeatureValueRepository repo, FeatureValue value) {
        repo.findByNameAndFeature(value.getName(), value.getFeature()).ifPresentOrElse(
                v -> log.info("Feature value '{}' already exists for feature '{}'", value.getName(), value.getFeature().getName()),
                () -> {
                    log.info("Creating feature value '{}' for feature '{}'", value.getName(), value.getFeature().getName());
                    repo.save(value);
                }
        );
    }
}
