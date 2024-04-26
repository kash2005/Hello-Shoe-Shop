package lk.ijse.helloshoeshop.service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.SupplierDTO;
import lk.ijse.helloshoeshop.entity.SupplierEntity;
import lk.ijse.helloshoeshop.repository.SupplierServiceDAO;
import lk.ijse.helloshoeshop.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SupplierServiceIMPL implements SupplierService {
    final private SupplierServiceDAO supplierServiceDAO;
    final private ConversionData conversionData;
    @Override
    public void saveSupplier(SupplierDTO supplierDTO) {
        supplierDTO.setSupplierCode(getNextSupplierCode());
        supplierServiceDAO.save(conversionData.convertToSupplierEntity(supplierDTO));
    }

    private String getNextSupplierCode() {
        SupplierEntity firstByOrderBySupplierIdDesc = supplierServiceDAO.findFirstByOrderBySupplierIdDesc();
        return (firstByOrderBySupplierIdDesc != null)
                ? String.format("Sup-%03d",
                Integer.parseInt(firstByOrderBySupplierIdDesc.getSupplierCode().
                        replace("Sup-", "")) + 1)
                : "Sup-001";
    }

    @Override
    public void updateSupplier(String id, SupplierDTO supplierDTO) {

    }

    @Override
    public void deleteSupplier(String id) {

    }

    @Override
    public SupplierDTO getCustomer(String id) {
        return null;
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return null;
    }
}
