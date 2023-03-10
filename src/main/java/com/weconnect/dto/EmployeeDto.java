package com.weconnect.dto;

import java.time.LocalDate;

public interface EmployeeDto {

	String getNombre();
	String getApellido();
	String getDireccion();
	String getCedula();
	String getTipoVacuna();
	Integer getDosis();
	LocalDate getFechaVacunacion();

}
