package lk.ijse.helloshoeshop.repository;

import lk.ijse.helloshoeshop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserServiceDAO extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);
}
