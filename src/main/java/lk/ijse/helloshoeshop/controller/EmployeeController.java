package lk.ijse.helloshoeshop.controller;

import lk.ijse.helloshoeshop.dto.EmployeeDTO;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/v1/employee")
@AllArgsConstructor
public class EmployeeController {
    final private EmployeeService employeeService;
    @GetMapping("/health")
    public String healthCheck(){
        return "Employee Health Check";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveEmployee(@Validated  EmployeeDTO employeeDTO,
                                               BindingResult bindingResult,@RequestPart("profilepic") String profilepic){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            String dp = convertBase64(profilepic);
            employeeDTO.setProfilePic(dp);
//            System.out.println(dp);
            employeeService.saveEmployee(employeeDTO);;
            return ResponseEntity.status(HttpStatus.OK).body("Employee Details saved Successfully.");
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Supplier Saved Unsuccessfully.\nMore details.\n"+exception);
        }
    }

//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public boolean saveEmployee(EmployeeDTO employeeDTO,@RequestPart("profilepic") String profilepic) throws Exception  {
//        String dp = convertBase64(profilepic);
//        employeeDTO.setProfilePic(dp);
//        System.out.println(dp);
//        employeeService.saveEmployee(employeeDTO);
//        return true;
//    }

    @GetMapping(value = "/getEmployeeById",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public EmployeeDTO getEmployee(@RequestPart("id") String id){
        return employeeService.getEmployee(id);
    }

    @GetMapping(value = "/getEmployees")
    public Iterable<EmployeeDTO> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    public static String convertBase64(String data){
        return Base64.getEncoder().encodeToString(data.getBytes());
    }
}
