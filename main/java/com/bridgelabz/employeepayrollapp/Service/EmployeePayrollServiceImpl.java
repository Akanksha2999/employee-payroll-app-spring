package com.bridgelabz.employeepayrollapp.Service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.entity.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.exceptions.EmployeePayrollException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeePayrollServiceImpl implements EmployeePayrollService{

    private List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
    public List<EmployeePayrollData> getEmployeePayrollData(){
        return employeePayrollDataList;
    }


    public EmployeePayrollData getEmployeePayrollDataById(int empId) {
        return employeePayrollDataList.stream()
                .filter(employeePayrollData -> employeePayrollData.getEmployeeId() == empId)
                .findFirst()
                .orElseThrow(() -> new EmployeePayrollException("Employee Not Found"));
    }

    public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO employeePayrollDTO) {
        EmployeePayrollData employeePayrollData = null;
        employeePayrollData = new EmployeePayrollData(employeePayrollDataList.size()+1, employeePayrollDTO);
        employeePayrollDataList.add(employeePayrollData);
        return employeePayrollData;
    }

    public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO employeePayrollDTO) {
        EmployeePayrollData employeePayrollData = this.getEmployeePayrollDataById(empId);
        employeePayrollData.setName(employeePayrollDTO.name);
        employeePayrollData.setSalary(employeePayrollDTO.salary);
        employeePayrollDataList.set(empId-1, employeePayrollData);
        return employeePayrollData;
    }

    public void deleteEmployeePayrollData(int empId) {
    employeePayrollDataList.remove(empId-1);
    }
}
