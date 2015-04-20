package com.hdp.smp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "STATIONS" )
public class Station {
	
	private int id;
	private String name;
	private String description;
	private String status;
	


	private boolean isActive;
	
	
    private  Monitor monitor;


    @Id 
	@GeneratedValue
	@GenericGenerator(name="increment", strategy = "increment")
	@Column
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Column ( unique=true, length=30,nullable=false)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Column ( length=30,nullable=true)
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Transient
	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Column
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	 @OneToOne(mappedBy="station",fetch=FetchType.EAGER)  
	public Monitor getActiveMonitor() {
		return monitor;
	}


	public void setActiveMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
	
	
	
	private Set<StationData> stationData;
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "station")
	public Set<StationData> getStationData() {
		return stationData;
	}

	public void setStationData(Set<StationData> stationData) {
		this.stationData = stationData;
	}
	
	
	private Set<Spindle> spindles;

	@OneToMany( fetch = FetchType.LAZY, mappedBy = "station")
	public Set<Spindle> getSpindles() {
		return spindles;
	}


	public void setSpindles(Set<Spindle> spindles) {
		this.spindles = spindles;
	}
	  
}
