package lk.ijse.helloshoeshop.service;

import lk.ijse.helloshoeshop.dto.CustomerDTO;
import lk.ijse.helloshoeshop.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {
    void saveSupplier(SupplierDTO supplierDTO);
    void updateSupplier(String id,SupplierDTO supplierDTO);
    void deleteSupplier(String id);
    SupplierDTO getCustomer(String id);
    List<SupplierDTO> getAllSuppliers();
}
