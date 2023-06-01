package com.example.withauthmanager.db.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "user_role")
public class RoleEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany
    @JoinTable(
            name="roles_authorities",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<AuthorityEntity> authorityEntityList;

    private String roleValue;
}
