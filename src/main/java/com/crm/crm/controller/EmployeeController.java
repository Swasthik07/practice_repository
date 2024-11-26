package com.crm.crm.controller;

import com.crm.crm.entity.Employee;
import com.crm.crm.payload.EmployeeDto;
import com.crm.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeDto dto, BindingResult result) {
     EmployeeDto employeeDto = employeeService.addEmployee(dto);
     if (result.hasErrors()) {
         return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
     }
//        System.out.println(employee.getName());
//        System.out.println(employee.getEmailId());
//        System.out.println(employee.getMobile());
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestParam Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);

}

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestParam Long id, @RequestBody EmployeeDto dto) {
        EmployeeDto employeeDto = employeeService.updateEmployee(id, dto);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<EmployeeDto>>  getEmployee(@RequestParam(name="pageSize",required=false,defaultValue="5")int pageSize,
                                                          @RequestParam(name="pageNo",required=false,defaultValue="5")int pageNo,
                                                          @RequestParam(name="sortBy",required=false,defaultValue="name")String sortBy,
                                                          @RequestParam(name="sortDir",required=false,defaultValue="asc")String sortDir) {

        List<EmployeeDto> employeesDto = employeeService.getEmployees( pageNo,  pageSize, sortBy,sortDir);
        System.out.println("Employees");
        System.out.println("Employees");
        return new ResponseEntity<>(employeesDto,HttpStatus.OK);

    }
    @GetMapping("/employeeId/{empId}")

    public ResponseEntity<EmployeeDto>  getEmployeeById(
            @PathVariable long empId
    ) {
       EmployeeDto dto = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
