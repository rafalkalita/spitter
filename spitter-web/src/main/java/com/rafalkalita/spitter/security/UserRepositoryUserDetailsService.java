package com.rafalkalita.spitter.security;

import com.rafalkalita.spitter.model.Spitter;
import com.rafalkalita.spitter.service.SpitterService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

/**
 * @author Rafal Kalita
 */
@Service("userService")
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private static final Log logger = LogFactory.getLog(UserRepositoryUserDetailsService.class);

    @Inject
    private SpitterService spitterService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("UserRepositoryUserDetailsService.loadUserByUsername("+username+")");
        Spitter spitter = spitterService.getSpitterByUsername(username);

        if(spitter == null) {
            throw new UsernameNotFoundException("Could not find user: " + username);
        }
        return new UserRepositoryUserDetails(spitter);
    }

    private final static class UserRepositoryUserDetails extends Spitter implements UserDetails {

        public static final long serialVersionUID = 1L;

        public UserRepositoryUserDetails(Spitter spitter) {
            super(spitter);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
