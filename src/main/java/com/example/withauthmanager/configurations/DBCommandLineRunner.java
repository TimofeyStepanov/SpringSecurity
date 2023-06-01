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
            AuthorityEntity authorityEntityDelete = new AuthorityEntity();
            authorityEntityDelete.setId(1L);
            authorityEntityDelete.setAuthorityValue("DELETE");
            authorityRepository.save(authorityEntityDelete);

            AuthorityEntity authorityEntityRead = new AuthorityEntity();
            authorityEntityRead.setId(2L);
            authorityEntityRead.setAuthorityValue("READ");
            authorityRepository.save(authorityEntityRead);

            RoleEntity roleEntityUser = new RoleEntity();
            roleEntityUser.setId(1L);
            roleEntityUser.setRoleValue("USER");
            roleEntityUser.setAuthorityEntityList(Set.of(authorityEntityRead));
            roleRepository.save(roleEntityUser);

            RoleEntity roleEntityAdmin = new RoleEntity();
            roleEntityAdmin.setId(2L);
            roleEntityAdmin.setRoleValue("ADMIN");
            roleEntityAdmin.setAuthorityEntityList(Set.of(authorityEntityDelete, authorityEntityRead));
            roleRepository.save(roleEntityAdmin);
        };
    }
}
