package lk.ijse.helloshoeshop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "order")
@RequiredArgsConstructor
public class OrderEntity implements SuperEntity{
    @Id
    private String orderId;
    private String customerName;
    private double addedPoint;
    private double totalPrice;
    private String paymentMethod;
    private Timestamp purchaseDate;
    private String cashierName;
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private CustomerEntity customer;
}