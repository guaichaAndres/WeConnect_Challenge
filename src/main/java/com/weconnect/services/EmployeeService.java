package com.weconnect.services;

import java.time.LocalDate;
import java.util.List;

import com.weconnect.dto.EmployeeDto;
import com.weconnect.models.Employee;

public interface EmployeeService extends AbstractService<Employee, Long> {

	
	public List<Employee> findByStatus(String status);
	public List<EmployeeDto> findByTypeVaccine(String status);
	public List<EmployeeDto> findByRangeDate(LocalDate dateInicial, LocalDate dateFinal);

}

