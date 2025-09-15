package com.eventhall.utils;





import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.eventhall.entities.Users;

public class Utils {
	

    private Utils() {} 
    


        public static LocalDateTime getCurrentTime() {
            return LocalDateTime.now();
        }

        public static String getCurrentNameUserAuthenticated() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication.getName();
       
        
        }
  
        
        public static UUID getCurrentUserIdAuthenticated() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
                Users user = (Users) authentication.getPrincipal();
                return user.getId();
            }
            return null;
        }
        

}