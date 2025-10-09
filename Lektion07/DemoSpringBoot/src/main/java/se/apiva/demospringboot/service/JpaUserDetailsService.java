package se.apiva.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.apiva.demospringboot.model.AppUser;
import se.apiva.demospringboot.repository.AppUserRepository;

import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public JpaUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser u = appUserRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("User not found: " + username) );

        // Mappa roller
        var auths = u.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toSet());

        return org.springframework.security.core.userdetails.User
                .withUsername(u.getUsername())
                .password(u.getPassword())
                .authorities(auths)
                .accountExpired(!u.isAccountNonExpired())
                .accountLocked(!u.isAccountNonLocked())
                .credentialsExpired(!u.isCredentialsNonExpired())
                .disabled(!u.isEnabled())
                .build();
    }
}
