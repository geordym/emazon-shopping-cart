package com.emazon.shopping_cart.infraestructure.configuration.security;



import com.emazon.shopping_cart.infraestructure.client.CustomerFeignClient;
import com.emazon.shopping_cart.infraestructure.client.dto.CustomerResponseDto;
import com.emazon.shopping_cart.infraestructure.client.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final CustomerFeignClient userRepository;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        Optional<UserInfoDto> userInfoDtoOptional = userRepository.authenticateUserByToken("Bearer " + token);

        if (userInfoDtoOptional.isPresent()) {
            UserInfoDto userInfo = userInfoDtoOptional.get();
            JwtCustomUserDetails jwtCustomUserDetails =
                    new JwtCustomUserDetails(userInfo.getIdUser(),
                            userInfo.getUsername(),
                            token,
                            AuthorityUtils.createAuthorityList( userInfo.getRoles().get(0)),
                            false,
                            false,
                            false,
                            true
                            );
            return jwtCustomUserDetails;
        } else {
            throw new UsernameNotFoundException(token);
        }
    }

}
