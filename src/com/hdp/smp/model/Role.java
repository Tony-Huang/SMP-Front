/**
 * Copyrights are reserved.
 * 2015
 * 
 * @Author hdp214@163.com
 * @since 1.0
 */

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

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table( name = "ROLES"  )
public class Role {

	private int id;
	private String name_CN;
	private String name_EN;
	private String description_CN;
	private String description_EN;
	
	private Set<User> users;
	
	
	public Role (){
		
	}
	
	@Id 
	//@GeneratedValue(strategy = GenerationType.IDENTITY))
	//@GenericGenerator(name="increment", strategy = "increment")
	@Column (name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//@Column ( name="name_CN",unique=true, length=10,nullable=false)
	@Column ( name="name_CN")
	public String getName_CN() {
		return name_CN;
	}
	public void setName_CN(String name_CN) {
		this.name_CN = name_CN;
	}
	
	//@Column(name="name_EN" ,unique=true, nullable=false,length=30)
	@Column(name="name_EN")
	public String getName_EN() {
		return name_EN;
	}
	public void setName_EN(String name_EN) {
		this.name_EN = name_EN;
	}

	
	
	
	@Column
	public String getDescription_CN() {
		return description_CN;
	}

	public void setDescription_CN(String description_CN) {
		this.description_CN = description_CN;
	}

	@Column
	public String getDescription_EN() {
		return description_EN;
	}

	public void setDescription_EN(String description_EN) {
		this.description_EN = description_EN;
	}

	@OneToMany( fetch = FetchType.LAZY, mappedBy = "role")
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	
	
	

}
