package lk.ijse.helloshoeshop.controller;

import lk.ijse.helloshoeshop.Enum.Gender;
import lk.ijse.helloshoeshop.dto.CustomerDTO;
import lk.ijse.helloshoeshop.dto.EmployeeDTO;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    final private CustomerService customerService;
    @GetMapping("/health")
    public String healthCheck(){
        return "Customer Health Check";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveCustomer(@Validated @RequestBody CustomerDTO customerDTO,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            customerService.saveCustomer(customerDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Customer Details saved Successfully.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Customer saved Unsuccessfully.\nMore Details\n"+exception);
        }
    }

    @GetMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<?> getCustomer(@PathVariable ("id") String id){
        try {
            return ResponseEntity.ok(customerService.getCustomer(id));
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Customer Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getAllCustomers(){
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Customer Details fetched Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable ("id") String id){
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.status(HttpStatus.OK).body("Customer Details deleted Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Customer Details deleted Unsuccessfully.\nMore Reason\n"+exception);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCustomer(@Validated @RequestBody CustomerDTO customerDTO,
                                                 BindingResult bindingResult,
                                                 @PathVariable ("id") String id) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            customerService.updateCustomer(id, customerDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Customer Details Updated Successfully.");
        } catch (NotFoundException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found.");
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("Internal server error | Customer Details Updated Unsuccessfully.\nMore Reason\n"+exception);
        }

    }
}
