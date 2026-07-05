package com.Project.Transaction_Risk_API.Controller;

import com.Project.Transaction_Risk_API.DTO.DashboardResponse;
import com.Project.Transaction_Risk_API.DTO.LoginRequest;
import com.Project.Transaction_Risk_API.DTO.RegisterRequest;
import com.Project.Transaction_Risk_API.DTO.UserResponse;
import com.Project.Transaction_Risk_API.Model.Users;
import com.Project.Transaction_Risk_API.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(
        name = "User API",
        description = "Operations related to user registration, authentication, and user management."
)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(
            summary = "Register a new user",
            description = "Creates a new user account after validating the request."
    )
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest request)
    {
        String str = userService.registerUser(request);
        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Get all users",
            description = "Returns a list of all registered users."
    )
    public ResponseEntity<List<UserResponse>> getUsers()
    {
        List<UserResponse> list = userService.getAllUsers();

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Get user by ID",
            description = "Returns the details of a specific user using the user ID."
    )
    public ResponseEntity<Users> getUserById(@PathVariable int id)
    {
        Users users = userService.getUserById(id);

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Delete user",
            description = "Deletes a user from the database using the user ID."
    )
    public ResponseEntity<String> deleterUserById(@PathVariable int id)
    {

           String msg =  userService.DeleteUserById(id);

           return new ResponseEntity<>(msg,HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "Update user",
            description = "Updates the information of an existing user."
    )
    public ResponseEntity<String> updateUser(@PathVariable int id,@RequestBody Users u)
    {
        String msg = userService.updateUser(id,u);

        return new ResponseEntity<>(msg,HttpStatus.OK);
    }

    @PostMapping("/login")
    @Operation(
            summary = "User login",
            description = "Authenticates the user and returns a JWT token if the credentials are valid."
    )
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginRequest loginRequest)
    {
        String str = userService.loginUser(loginRequest);

        return new ResponseEntity<>(str,HttpStatus.OK);
    }


}
