package lk.ijse.helloshoeshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lk.ijse.helloshoeshop.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@RequiredArgsConstructor
public class UserDTO {
    @Null(message = "Id generated by Program")
    private String id;
    @NotNull(message = "Email cannot be null")
    @Email
    private String email;
    @NotNull(message = "Password cannot be null")
    private String password;
    @NotNull(message = "Role cannot be null")
    private Role role;
}