package com.tuantran.CarShowroom.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "spring_user_001")
public class User extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    @ToString.Exclude
    private Role role;
}
