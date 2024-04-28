package lk.ijse.helloshoeshop.controller;

import lk.ijse.helloshoeshop.dto.CustomerDTO;
import lk.ijse.helloshoeshop.dto.SupplierDTO;
import lk.ijse.helloshoeshop.exception.NotFoundException;
import lk.ijse.helloshoeshop.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/supplier")
@AllArgsConstructor
public class SupplierController {
    final private SupplierService supplierService;

    @GetMapping("health")
    public String healthCheck(){
        return "Supplier Health Check";
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveSupplier(@Validated @RequestBody SupplierDTO supplierDTO,
                                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }

        try {
            supplierService.saveSupplier(supplierDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Supplier Saved Successfully.");
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Supplier Saved Unsuccessfully.\nMore details.\n"+exception);
        }
    }

}
