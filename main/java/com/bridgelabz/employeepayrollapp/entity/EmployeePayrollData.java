package com.bridgelabz.employeepayrollapp.entity;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee_payroll")
public @Data class EmployeePayrollData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private  int employeeId;
    @Column(name = "name")
    private String name;
    @Column(name = "salary")
    private long salary;
    @Column(name = "gender")
    private String gender;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "note")
    private String note;
    @Column(name = "profile_pic")
    private String profilePic;

    @ElementCollection
    @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "departments")
    private List<String> departments;

    public EmployeePayrollData() {

    }

    public EmployeePayrollData(EmployeePayrollDTO employeePayrollDTO) {
        this.updateEmployeePayrollData(employeePayrollDTO);
    }

    public void updateEmployeePayrollData(EmployeePayrollDTO employeePayrollDTO){
        this.name = employeePayrollDTO.name;
        this.salary = employeePayrollDTO.salary;
        this.gender = employeePayrollDTO.gender;
        this.note = employeePayrollDTO.note;
        this.startDate = employeePayrollDTO.startDate;
        this.profilePic = employeePayrollDTO.profilePic;
        this.departments = employeePayrollDTO.departments;

    }

}
