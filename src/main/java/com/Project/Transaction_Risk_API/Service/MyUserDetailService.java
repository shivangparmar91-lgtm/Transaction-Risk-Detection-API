package com.Project.Transaction_Risk_API.Service;

import com.Project.Transaction_Risk_API.Model.Users;
import com.Project.Transaction_Risk_API.Repository.UserRepo;
import com.Project.Transaction_Risk_API.SecurityConfiguration.UserPrinicpel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {

        Users u = repo.findByEmail(Email);

        if(u == null)
        {
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrinicpel(u);
    }
}
