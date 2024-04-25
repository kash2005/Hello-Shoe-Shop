package lk.ijse.helloshoeshop.repository;

import lk.ijse.helloshoeshop.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerServiceDAO extends JpaRepository<CustomerEntity,String> {
    CustomerEntity findFirstByOrderByCustomerIdDesc();
}
