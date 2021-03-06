package com.hdp.smp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SHIFTS")
public class Shift implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3326734258093347648L;

	private int id;
	private String name;
	private String description;

	private Set<StationData> stationData;

	@Id
	@GeneratedValue
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany( fetch = FetchType.LAZY, mappedBy = "shift")
	public Set<StationData> getStationData() {
		return stationData;
	}

	public void setStationData(Set<StationData> stationData) {
		this.stationData = stationData;
	}

}
