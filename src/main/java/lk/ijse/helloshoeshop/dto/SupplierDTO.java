package lk.ijse.helloshoeshop.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lk.ijse.helloshoeshop.Enum.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO implements SuperDTO{
    @Null(message = "Supplier Id generate by the program")
    private String supplierCode;

    @NotNull(message = "Supplier Name cannot be null")
    private String supplierName;

    @NotNull(message = "Category cannot be null")
    private Category category;

    @NotNull(message = "Address 1 cannot be null")
    private String address1;

    @NotNull(message = "Address 2 cannot be null")
    private String address2;

    @NotNull(message = "Address 3 cannot be null")
    private String address3;

    @NotNull(message = "Address 4 cannot be null")
    private String address4;

    @NotNull(message = "PostalCode cannot be null")
    @Pattern(regexp = "\\d{5}", message = "Postal code must be 5 digits")
    private String postalCode;

    @NotNull(message = "Country cannot be null")
    private String country;

    @NotNull(message = "ContactNo1 cannot be null")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid contact number format")
    private String contactNo1;

    @NotNull(message = "ContactNo1 cannot be null")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]+$", message = "Invalid contact number format")
    private String contactNo2;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    private String email;
}
