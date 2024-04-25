package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.*;
import lk.ijse.helloshoeshop.Enum.Gender;
import lk.ijse.helloshoeshop.Enum.Role;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "employee")
public class EmployeeEntity implements SuperEntity{
    @Id
    private String employeeId;
    private String employeeName;
    private String employeeProfilePic;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String status;
    private String designation;
    @Enumerated(EnumType.STRING)
    private Role accessRole;
    private Date dob;
    private Date joinedDate;
    private String attachedBranch;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String address5;
    private String contactNo;
    private String email;
    private String guardian;
    private String emergencyContact;
}
