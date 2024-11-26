package com.crm.crm.service;

import com.crm.crm.entity.Employee;
import com.crm.crm.exception.ResourceNotFound;
import com.crm.crm.payload.EmployeeDto;
import com.crm.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    public EmployeeService(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }


    public EmployeeDto addEmployee(EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        Employee emp = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(emp);
        //employeeDto.setDate(new Date());
        return employeeDto;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        //Optional<Employee> employeeOptional = employeeRepository.findById(id);
        employee.setId(id);
//        if (employeeOptional.isPresent()) {
//            Employee employeeToUpdate = employeeOptional.get();
//            employeeToUpdate.setName(employee.getName());
//            employeeToUpdate.setEmailId(employee.getEmailId());
//            employeeToUpdate.setMobile(employee.getMobile());
        Employee updateEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(updateEmployee);
        return employeeDto;
//        }
    }

    public List<EmployeeDto> getEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page= PageRequest.of(pageNo, pageSize, sort);

        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employees= all.getContent();

        List<EmployeeDto> dto = employees.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return dto;
    }
    Employee mapToEntity(EmployeeDto dto) {
          Employee emp = modelMapper.map(dto, Employee.class);
//        Employee emp = new Employee();
//        emp.setId(dto.getId());
//        emp.setName(dto.getName());
//        emp.setEmailId(dto.getEmailId());
//        emp.setMobile(dto.getMobile());
        return emp;
    }
    EmployeeDto mapToDto(Employee employee) {
        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
//        EmployeeDto dto = new EmployeeDto();
//        dto.setId(employee.getId());
//        dto.setName(employee.getName());
//        dto.setEmailId(employee.getEmailId());
//        dto.setMobile(employee.getMobile());
        return dto;
    }

    public EmployeeDto getEmployeeById(long empId) {
       Employee employee = employeeRepository.findById(empId).orElseThrow(
                () -> new ResourceNotFound("Employee not found with id: " + empId)
        );
        EmployeeDto dto= mapToDto(employee);
        return dto;
        }



}
