package com.example.withauthmanager.services.impl;

import com.example.withauthmanager.db.models.AuthorityEntity;
import com.example.withauthmanager.db.models.UserEntity;
import com.example.withauthmanager.db.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findUserEntityByEmail(userEmail);
        UserEntity user = userOptional.orElseThrow(() -> new UsernameNotFoundException("No user with such email!"));

        Set<AuthorityEntity> authorityEntityList = user.getRole().getAuthorityEntityList();

        List<SimpleGrantedAuthority> authorityList = authorityEntityList
                .stream()
                .map(authorityEntity -> new SimpleGrantedAuthority(authorityEntity.getAuthorityValue()))
                .collect(Collectors.toList());
        authorityList.add(new SimpleGrantedAuthority(user.getRole().getRoleValue()));

        return new User(
                user.getEmail(),
                user.getPassword(),
                authorityList
        );
    }
}
