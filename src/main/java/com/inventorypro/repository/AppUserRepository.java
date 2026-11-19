package com.inventorypro.repository;
import com.inventorypro.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
 java.util.Optional<AppUser> findByUsername(String username);
}
