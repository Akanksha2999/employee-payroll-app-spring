package com.bridgelabz.employeepayrollapp.Service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.entity.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.exceptions.EmployeePayrollException;
import com.bridgelabz.employeepayrollapp.repository.EmployeePayrollRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeePayrollServiceImpl implements EmployeePayrollService{

    @Autowired
    private EmployeePayrollRepository employeePayrollRepository;
    public List<EmployeePayrollData> getEmployeePayrollData(){
        return employeePayrollRepository.findAll();
    }


    public EmployeePayrollData getEmployeePayrollDataById(int empId) {
        return employeePayrollRepository.findById(empId)
                        .orElseThrow( () -> new EmployeePayrollException("Employee with employeeId " + empId + " does not exists..!!"));
    }

    @Override
    public List<EmployeePayrollData> getEmployeesByDepartment(String department) {
        return employeePayrollRepository.findEmployeesByDepartment(department);
    }

    public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO employeePayrollDTO) {
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = new EmployeePayrollData(employeePayrollDTO);
        log.debug("Employee Data: " +employeePayrollData.toString());
        return employeePayrollRepository.save(employeePayrollData);
    }

    public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO employeePayrollDTO) {
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataById(empId);
        employeePayrollData.updateEmployeePayrollData(employeePayrollDTO);
        return employeePayrollRepository.save(employeePayrollData);
    }

    public void deleteEmployeePayrollData(int empId) {
    EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataById(empId);
    employeePayrollRepository.delete(employeePayrollData);
    }
}
