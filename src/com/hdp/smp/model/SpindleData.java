package com.hdp.smp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table( name = "SPINDLEDATA"  )
public class SpindleData {
	private long id;
	
	private int speed;
	private String status;

	private Spindle spindle;

	@Id 
	@GeneratedValue
	@GenericGenerator(name="increment", strategy = "increment")
	@Column
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	@Column
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Column
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne @JoinColumn(name="spindleId", updatable=true,nullable=false)
	public Spindle getSpindle() {
		return spindle;
	}

	public void setSpindle(Spindle spindle) {
		this.spindle = spindle;
	}
	
	
}
