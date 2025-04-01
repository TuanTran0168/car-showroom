package com.tuantran.CarShowroom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<User> userList;
}
