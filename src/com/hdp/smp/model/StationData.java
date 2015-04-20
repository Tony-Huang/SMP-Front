package com.hdp.smp.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "STATIONDATA"  )
public class StationData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1567254688422753170L;
	
	private long id;
	private Station  station;
	private Shift  shift;
	
	int brokenSpindles ;
	int instantBrokenHeads ;
	int emptySpindles ;
	int creepSpindles ;
	float twist ;
	float draftTimes ;
	float powerKW ;
	float eneryKWH ;
	float productionEfficiency ;
	float frontRollerSpeed ;
	float brokenEndsPer1000Spindles;
	float avgSpindleSpeed ;
	
	float  realTimeProduction ;
	float grossProductionByShift ;
	Timestamp createdOn ;
	Time startTime ;
	Time endTime ;
	Timestamp doffTime ; 
	String warning ;
	boolean  valid ;
	int doffBorkenEnds ;
	int accumulatedBrokenEnds ;
	String stationStatus;
	boolean isBeforeDoff ;
	String attr1 ;
	Date attr2 ;
	Time attr3 ; 
	int attr4 ;
	boolean  attr5;
	
	
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
	
	@ManyToOne @JoinColumn(name="stationId", updatable=true,nullable=false)
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	
	@ManyToOne @JoinColumn(name="shiftId", updatable=true,nullable=false)
	public Shift getShift() {
		return shift;
	}
	public void setShift(Shift shift) {
		this.shift = shift;
	}
	
	
	@Column
	public int getBrokenSpindles() {
		return brokenSpindles;
	}
	public void setBrokenSpindles(int brokenSpindles) {
		this.brokenSpindles = brokenSpindles;
	}
	
	@Column
	public int getInstantBrokenHeads() {
		return instantBrokenHeads;
	}
	public void setInstantBrokenHeads(int instantBrokenHeads) {
		this.instantBrokenHeads = instantBrokenHeads;
	}
	
	@Column
	public int getEmptySpindles() {
		return emptySpindles;
	}
	public void setEmptySpindles(int emptySpindles) {
		this.emptySpindles = emptySpindles;
	}
	
	@Column
	public int getCreepSpindles() {
		return creepSpindles;
	}
	public void setCreepSpindles(int creepSpindles) {
		this.creepSpindles = creepSpindles;
	}
	
	@Column
	public float getTwist() {
		return twist;
	}
	public void setTwist(float twist) {
		this.twist = twist;
	}
	
	@Column
	public float getDraftTimes() {
		return draftTimes;
	}
	public void setDraftTimes(float draftTimes) {
		this.draftTimes = draftTimes;
	}
	
	@Column
	public float getPowerKW() {
		return powerKW;
	}
	public void setPowerKW(float powerKW) {
		this.powerKW = powerKW;
	}
	
	@Column
	public float getEneryKWH() {
		return eneryKWH;
	}
	public void setEneryKWH(float eneryKWH) {
		this.eneryKWH = eneryKWH;
	}
	
	@Column
	public float getProductionEfficiency() {
		return productionEfficiency;
	}
	public void setProductionEfficiency(float productionEfficiency) {
		this.productionEfficiency = productionEfficiency;
	}
	
	
	@Column
	public float getFrontRollerSpeed() {
		return frontRollerSpeed;
	}
	public void setFrontRollerSpeed(float frontRollerSpeed) {
		this.frontRollerSpeed = frontRollerSpeed;
	}
	
	
	@Column
	public float getBrokenEndsPer1000Spindles() {
		return brokenEndsPer1000Spindles;
	}
	public void setBrokenEndsPer1000Spindles(float brokenEndsPer1000Spindles) {
		this.brokenEndsPer1000Spindles = brokenEndsPer1000Spindles;
	}
	
	
	@Column
	public float getAvgSpindleSpeed() {
		return avgSpindleSpeed;
	}
	public void setAvgSpindleSpeed(float avgSpindleSpeed) {
		this.avgSpindleSpeed = avgSpindleSpeed;
	}
	
	
	@Column
	public float getRealTimeProduction() {
		return realTimeProduction;
	}
	public void setRealTimeProduction(float realTimeProduction) {
		this.realTimeProduction = realTimeProduction;
	}
	
	
	@Column
	public float getGrossProductionByShift() {
		return grossProductionByShift;
	}
	public void setGrossProductionByShift(float grossProductionByShift) {
		this.grossProductionByShift = grossProductionByShift;
	}
	
	
	@Column
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	
	@Column
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	
	
	@Column
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	@Column
	public Timestamp getDoffTime() {
		return doffTime;
	}
	public void setDoffTime(Timestamp doffTime) {
		this.doffTime = doffTime;
	}
	
	@Column
	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}
	
	@Column
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	@Column
	public int getDoffBorkenEnds() {
		return doffBorkenEnds;
	}
	public void setDoffBorkenEnds(int doffBorkenEnds) {
		this.doffBorkenEnds = doffBorkenEnds;
	}
	
	
	@Column
	public int getAccumulatedBrokenEnds() {
		return accumulatedBrokenEnds;
	}
	public void setAccumulatedBrokenEnds(int accumulatedBrokenEnds) {
		this.accumulatedBrokenEnds = accumulatedBrokenEnds;
	}
	
	@Column
	public String getStationStatus() {
		return stationStatus;
	}
	public void setStationStatus(String stationStatus) {
		this.stationStatus = stationStatus;
	}
	
	@Column(name="isBeforeDoff")
	public boolean isBeforeDoff() {
		return isBeforeDoff;
	}
	public void setBeforeDoff(boolean isBeforeDoff) {
		this.isBeforeDoff = isBeforeDoff;
	}
	
	@Column
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	
	@Column
	public Date getAttr2() {
		return attr2;
	}
	public void setAttr2(Date attr2) {
		this.attr2 = attr2;
	}
	
	
	@Column
	public Time getAttr3() {
		return attr3;
	}
	public void setAttr3(Time attr3) {
		this.attr3 = attr3;
	}
	
	
	@Column
	public int getAttr4() {
		return attr4;
	}
	public void setAttr4(int attr4) {
		this.attr4 = attr4;
	}
	
	
	@Column
	public boolean isAttr5() {
		return attr5;
	}
	public void setAttr5(boolean attr5) {
		this.attr5 = attr5;
	}
	
	
}
