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
@Table(name = "inventory")
public class InventoryEntity implements SuperEntity{
    @Id
    private String itemCode;
    private String itemDescription;
    private String itemPicture;
    private String category;
    private double unitPriceSell;
    private double unitPriceBuy;
    private double expectedProfit;
    private double profitMargin;
    private String status;
    private int size;
    private int quantity;
}
