package com.company.cpluss.userInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String password;
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Role.class)
    private List<Role> roles;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Address.class)
    private List<Address> address;

}
