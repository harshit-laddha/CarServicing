package com.harshit.warba.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.harshit.warba.generators.CustomIDGenerator;

import lombok.Data;

@Entity
@Data
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_seq")
	@GenericGenerator(name = "service_seq", strategy = "com.harshit.warba.generators.CustomIDGenerator", parameters = {
			@Parameter(name = CustomIDGenerator.VALUE_PREFIX_PARAMETER, value = "S_"),
			@Parameter(name = CustomIDGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
	private String serviceID;

	private String serviceNote;

	@Temporal(TemporalType.DATE)
	private Date dateServiced;

	@ManyToOne
	@JoinColumn(name = "CAR_ID")
	private Car car;

	public String getServiceNote() {
		return serviceNote;
	}

	public void setServiceNote(String serviceNote) {
		this.serviceNote = serviceNote;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Date getDateServiced() {
		return dateServiced;
	}

	public void setDateServiced(Date dateServiced) {
		this.dateServiced = dateServiced;
	}
}
