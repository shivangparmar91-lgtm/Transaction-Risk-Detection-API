package com.Project.Transaction_Risk_API.Repository;

import com.Project.Transaction_Risk_API.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByEmail(String email);
}
