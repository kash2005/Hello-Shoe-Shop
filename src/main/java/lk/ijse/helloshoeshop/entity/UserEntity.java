package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.*;
import lk.ijse.helloshoeshop.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table (name = "User")
public class UserEntity {
    @Id
    private String email;
    private String password;
    private Role role;
    @OneToMany (mappedBy = "userEntity",cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntities;
}
