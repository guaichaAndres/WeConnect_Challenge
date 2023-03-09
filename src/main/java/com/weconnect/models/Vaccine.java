package com.weconnect.models;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Table(name = "WECONNECT_VACCINE")
@Entity
@Accessors(chain = true)
@Getter
@Setter
public class Vaccine implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vaccine_id", unique = true, nullable = false, precision = 19)
	private Long id;

	@Column(name = "vaccine_type", nullable = false, length = 100)
	private String vaccineType;

	@Column(name = "vaccine_date", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate vaccineDate;

	@Column(name = "vaccine_number", nullable = false, precision = 10)
	private Integer vaccineNumber;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Employee.class)
	@JoinColumn(name = "vaccine_employee", referencedColumnName = "employee_id")
	private Employee employee;

	public Vaccine(String vaccineType, LocalDate vaccineDate, Integer vaccineNumber, Employee employee) {
		super();
		this.vaccineType = vaccineType;
		this.vaccineDate = vaccineDate;
		this.vaccineNumber = vaccineNumber;
		this.employee = employee;
	}

	public Vaccine() {
		super();
	}

	@Override
	public String toString() {
		return "Vaccine [id=" + id + ", vaccineType=" + vaccineType + ", vaccineDate=" + vaccineDate
				+ ", vaccineNumber=" + vaccineNumber + "]";
	}

}
