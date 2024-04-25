package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Entity
@Table(name = "sizes")
public class SizeEntity {
    @Id
    private String sizeCode;
    private String sizeCategory;
    private int qty;
}
