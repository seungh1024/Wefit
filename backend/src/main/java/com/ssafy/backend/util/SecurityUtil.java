package com.ssafy.backend.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


//Security Context의 Authentication 객체를 이용해 username을 리턴
public class SecurityUtil {

    private SecurityUtil() {
    }

    public static Optional<String> getCurrentUseremail() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return Optional.empty();
        }

        String useremail = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            useremail = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            useremail = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(useremail);
    }
}
