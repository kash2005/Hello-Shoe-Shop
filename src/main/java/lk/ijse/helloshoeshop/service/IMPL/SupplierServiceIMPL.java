package lk.ijse.helloshoeshop.service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.SupplierDTO;
import lk.ijse.helloshoeshop.entity.SupplierEntity;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.repository.SupplierServiceDAO;
import lk.ijse.helloshoeshop.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SupplierServiceIMPL implements SupplierService {
    final private SupplierServiceDAO supplierServiceDAO;
    final private ConversionData conversionData;
    @Override
    public void saveSupplier(SupplierDTO supplierDTO) {
        supplierDTO.setSupplierCode(getNextSupplierCode());
        SupplierEntity supplierEntity = conversionData.convertToSupplierEntity(Optional.of(supplierDTO));
        supplierServiceDAO.save(supplierEntity);

    }

    private String getNextSupplierCode() {
        SupplierEntity firstByOrderBySupplierCodeDesc = supplierServiceDAO.findFirstByOrderBySupplierCodeDesc();
        return (firstByOrderBySupplierCodeDesc != null) ?
                String.format("Sup-%03d",Integer.parseInt(firstByOrderBySupplierCodeDesc.getSupplierCode()
                        .replace("Sup-",""))+1) : "Sup-001";
    }

    @Override
    public void updateSupplier(String id, SupplierDTO supplierDTO) {

    }

    @Override
    public void deleteSupplier(String id) {

    }

    @Override
    public SupplierDTO getSupplier(String id) {
        if (!supplierServiceDAO.existsById(id)) throw new NotFoundException("Supplier not found");
        return conversionData.convertToSupplierDTO(supplierServiceDAO.findById(id));
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return null;
    }
}
