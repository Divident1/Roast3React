package com.wecp.w3day5task1.service;


import com.wecp.w3day5task1.entity.User;
import com.wecp.w3day5task1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // method to load a user by its username
       Optional<User> optUser=userRepository.findByUsername(username);


       if(optUser.isPresent())
       {
        User user=optUser.get();
        return new  org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles())
        );

   


       }

       else{
         throw new UsernameNotFoundException("Username not found");
       }


    }
}

