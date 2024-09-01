package com.emazon.shopping_cart.infraestructure.configuration.security;



import com.emazon.shopping_cart.infraestructure.client.UserFeignClient;
import com.emazon.shopping_cart.infraestructure.client.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserFeignClient userRepository;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        Optional<UserInfoDto> userInfoDtoOptional = userRepository.authenticateUserByToken("Bearer " + token);

        if (userInfoDtoOptional.isPresent()) {
            UserInfoDto userInfo = userInfoDtoOptional.get();
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(userInfo.username)
                    .password(token)
                    .authorities(userInfo.getRoles().get(0))
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
            return userDetails;
        } else {
            throw new UsernameNotFoundException(token);
        }
    }

}
