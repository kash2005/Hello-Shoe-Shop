package lk.ijse.helloshoeshop.repository;

import lk.ijse.helloshoeshop.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierServiceDAO extends JpaRepository<SupplierEntity,String> {
    SupplierEntity findFirstByOrderBySupplierIdDesc();
}
