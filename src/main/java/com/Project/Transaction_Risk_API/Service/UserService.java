package com.Project.Transaction_Risk_API.Service;

import com.Project.Transaction_Risk_API.DTO.LoginRequest;
import com.Project.Transaction_Risk_API.DTO.RegisterRequest;
import com.Project.Transaction_Risk_API.DTO.UserResponse;
import com.Project.Transaction_Risk_API.Model.Users;
import com.Project.Transaction_Risk_API.Repository.UserRepo;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder PasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwt;

    public String registerUser(RegisterRequest request) {



        Users newUser = new Users();

        newUser.setName(request.getName());
        newUser.setPassword( PasswordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());

        Users existingUser = userRepo.findByEmail(request.getEmail());


        if(existingUser != null)
        {
            return "Email already exists";
        }

            userRepo.save(newUser);

            return "User Register Successfully";

    }

    public List<UserResponse> getAllUsers() {

        List<Users> usersFromDB = userRepo.findAll(); // user from database

        List<UserResponse> userResponses = new ArrayList<>();

        for(Users u : usersFromDB)
        {
            UserResponse UR = new UserResponse(u.getId(),u.getName(),u.getEmail());
            userResponses.add(UR);
        }

        return userResponses;

    }

    public Users getUserById(int id) {


        return userRepo.findById(id).orElseThrow();
    }

    public String DeleteUserById(int id) {

        if(!userRepo.existsById(id))
        {
            return "User Not Found";
        }
        userRepo.deleteById(id);

        return "User Deleted Successful";
    }

    public String updateUser(int id, Users u) {

        Users u1 = userRepo.findById(id).orElseThrow();

        u1.setName(u.getName());
        u1.setEmail(u.getEmail());
        u1.setPassword(u.getPassword());

        userRepo.save(u1);

        return "User updated";

    }

    public String loginUser(@Valid LoginRequest loginRequest) {

        Authentication authentication =
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

        if(authentication.isAuthenticated())
        {
            return jwt.genrateToken(loginRequest.getEmail());
        }

        return "Login Fail";
    }
}
