package lk.ijse.helloshoeshop.service.IMPL;

import jakarta.transaction.Transactional;
import lk.ijse.helloshoeshop.conversion.ConversionData;
import lk.ijse.helloshoeshop.dto.EmployeeDTO;
import lk.ijse.helloshoeshop.entity.EmployeeEntity;
import lk.ijse.helloshoeshop.entity.UserEntity;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.repository.EmployeeServiceDAO;
import lk.ijse.helloshoeshop.repository.UserServiceDAO;
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
    final private UserServiceDAO userServiceDAO;
    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setEmployeeCode(getNextEmployeeCode());

        System.out.println(employeeDTO.getEmployeeCode());

        EmployeeEntity employeeEntity = conversionData.convertToEmployeeEntity(employeeDTO);

        String email = employeeDTO.getEmail();
        Optional<UserEntity> byEmail = userServiceDAO.findByEmail(email);

        if(!byEmail.isPresent()){ // Check if value is present in the optional
            throw new NotFoundException("User Not Found");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(byEmail.get().getUserId());
        userEntity.setEmail(email);
        userEntity.setPassword(byEmail.get().getPassword());
        userEntity.setRole(byEmail.get().getRole());

        employeeEntity.setUserEntity(userEntity);

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
        if(!employeeServiceDAO.existsById(id)){throw new NotFoundException("Employee Not Found");}
        employeeDTO.setEmployeeCode(id);
        employeeServiceDAO.save(conversionData.convertToEmployeeEntity(employeeDTO));
    }

    @Override
    public void deleteEmployee(String id) {
        if(!employeeServiceDAO.existsById(id)){throw new NotFoundException("Employee Not Found");}
        employeeServiceDAO.deleteById(id);
    }

    @Override
    public EmployeeDTO getEmployee(String id) {
        if (!employeeServiceDAO.existsById(id)){
            throw new NotFoundException("Employee Not Found");
        }
        return conversionData.convertToEmployeeDTO(employeeServiceDAO.findById(id));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return conversionData.getEmployeeDTOList(employeeServiceDAO.findAll());
    }


}
