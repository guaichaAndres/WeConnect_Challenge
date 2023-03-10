package com.weconnect.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weconnect.dto.EmployeeDto;
import com.weconnect.models.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findByDni(String dni);

	List<Employee> findByStatusVaccine(String statusVaccine);
	
	@Query(value = "SELECT e.employee_name as nombre, e.employee_last_name as apellido, e.employee_address as direccion, e.employee_dni as cedula, "
			+ "v.vaccine_type as tipoVacuna, v.vaccine_number as dosis, v.vaccine_date as fechaVacunacion "
			+ "FROM weconnect_employee e LEFT JOIN weconnect_vaccine v on e.employee_id = v.vaccine_employee WHERE v.vaccine_type = ?1 "
			+ "group by v.vaccine_date, v.vaccine_type, v.vaccine_number, e.employee_name, e.employee_last_name, e.employee_address, e.employee_dni "
			+ "order by e.employee_last_name, e.employee_name", nativeQuery = true)
	List<EmployeeDto> findByType(String name);
	
	@Query(value = "select e.employee_name as nombre, e.employee_last_name as apellido, e.employee_address as direccion, e.employee_dni as cedula, "
			+ "v.vaccine_type as tipoVacuna, v.vaccine_number as dosis, v.vaccine_date as fechaVacunacion "
			+ "from weconnect_employee e left join weconnect_vaccine v on e.employee_id = v.vaccine_employee where v.vaccine_date >= ?1 AND v.vaccine_date <= ?2 "
			+ "group by v.vaccine_date, v.vaccine_type, v.vaccine_number, e.employee_name, e.employee_last_name, e.employee_address, e.employee_dni "
			+ "order by e.employee_last_name, e.employee_name", nativeQuery = true)
	List<EmployeeDto> findByRange(LocalDate dateInicial, LocalDate dateFinal);
}
