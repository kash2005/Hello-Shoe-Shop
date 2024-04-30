package lk.ijse.helloshoeshop.service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.EmployeeDTO;
import lk.ijse.helloshoeshop.entity.EmployeeEntity;
import lk.ijse.helloshoeshop.repository.EmployeeServiceDAO;
import lk.ijse.helloshoeshop.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceIMPL implements EmployeeService {
    final private ConversionData conversionData;
    final private EmployeeServiceDAO employeeServiceDAO;
    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setEmployeeCode(getNextEmployeeCode());
        EmployeeEntity employeeEntity = conversionData.convertToEmployeeEntity(Optional.of(employeeDTO));
        employeeServiceDAO.save(employeeEntity);
    }

    private String getNextEmployeeCode() {
        EmployeeEntity firstByOrderByEmployeeIdDesc = employeeServiceDAO.findFirstByOrderByEmployeeCodeDesc();
        return (firstByOrderByEmployeeIdDesc != null) ?
                String.format("Emp-%03d",Integer.parseInt(firstByOrderByEmployeeIdDesc.getEmployeeCode()
                        .replace("Emp-",""))+1) : "Emp-001";
    }

    @Override
    public void updateEmployee(String id, EmployeeDTO employeeDTO) {

    }

    @Override
    public void deleteEmployee(String id) {

    }

    @Override
    public EmployeeDTO getEmployee(String id) {
        return conversionData.getEmployeeDTO(Optional.ofNullable(employeeServiceDAO.findById(id).orElse(null)));
    }

    @Override
    public Iterable<EmployeeDTO> getAllEmployee() {
        return conversionData.getEmployeeDTOList(employeeServiceDAO.findAll());
    }
}
