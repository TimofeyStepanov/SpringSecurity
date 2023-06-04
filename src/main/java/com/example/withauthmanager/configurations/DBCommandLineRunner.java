package com.example.withauthmanager.configurations;

import com.example.withauthmanager.db.models.AuthorityEntity;
import com.example.withauthmanager.db.models.RoleEntity;
import com.example.withauthmanager.db.repositories.AuthorityRepository;
import com.example.withauthmanager.db.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DBCommandLineRunner {
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    @Bean
    public CommandLineRunner dataLoader() {
        return args -> {
            AuthorityEntity authorityEntityUpdate = new AuthorityEntity();
            authorityEntityUpdate.setId(1L);
            authorityEntityUpdate.setAuthorityValue("UPDATE_AUTHORITY");
            authorityRepository.save(authorityEntityUpdate);

            AuthorityEntity authorityEntityDelete = new AuthorityEntity();
            authorityEntityDelete.setId(2L);
            authorityEntityDelete.setAuthorityValue("DELETE_AUTHORITY");
            authorityRepository.save(authorityEntityDelete);

            RoleEntity roleEntityUser = new RoleEntity();
            roleEntityUser.setId(1L);
            roleEntityUser.setRoleValue("ROLE_USER");
            roleEntityUser.setAuthorityEntityList(Set.of(authorityEntityUpdate));
            roleRepository.save(roleEntityUser);

            RoleEntity roleEntityAdmin = new RoleEntity();
            roleEntityAdmin.setId(2L);
            roleEntityAdmin.setRoleValue("ROLE_ADMIN");
            roleEntityAdmin.setAuthorityEntityList(Set.of(authorityEntityUpdate, authorityEntityDelete));
            roleRepository.save(roleEntityAdmin);
        };
    }
}
