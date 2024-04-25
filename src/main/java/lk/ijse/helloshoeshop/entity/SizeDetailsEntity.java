package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "size_details")
public class SizeDetailsEntity {
    @Id
    private String sizeDetailsId;
    @ManyToOne(cascade = CascadeType.ALL)
    private SizeDetailsEntity sizeDetails;
}
