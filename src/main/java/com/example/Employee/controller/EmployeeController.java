package com.example.employee.controller;

import com.example.employee.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {

        employees.add(employee);

        return employee;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {

        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {

        return employees.stream()
                .filter(employee -> employee.getEmpId() == id)
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable int id,
            @RequestBody Employee updatedEmployee) {

        Employee employee = employees.stream()
                .filter(emp -> emp.getEmpId() == id)
                .findFirst()
                .orElse(null);

        if (employee != null) {
            employee.setEmpName(updatedEmployee.getEmpName());
            employee.setDept(updatedEmployee.getDept());
        }

        return employee;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {

        boolean removed = employees.removeIf(
                employee -> employee.getEmpId() == id
        );

        if (removed) {
            return "Employee deleted successfully";
        }

        return "Employee not found";
    }
}
