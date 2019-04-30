package com.eteg.backend.etegbackend.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** 
 * Represents a User
 *
 * @author Renan Mattos
 */
@Entity
public class User
{
	@Id @GeneratedValue private Integer userId;
	
	@Column private String name;
	@Column private char gender;
	@Column private String cpf;
	
	public User()
	{
		super();
	}

	/**
	 * Constructor with some defined informations.
	 *
	 * @param userId User identificator
	 * @param name Name of user
	 * @param gender User gender
	 * @param cpf CPF of User
	 */
	public User(Integer userId, String name, char gender, String cpf)
	{
		this.userId = userId;
		this.name = name;
		this.gender = gender;
		this.cpf = cpf;
	}
	
	public Integer getUserId ()
	{
		return userId;
	}
	
	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public char getGender()
	{
		return gender;
	}
	
	public void setGender(char gender)
	{
		this.gender = gender;
	}
	
	public String getCpf()
	{
		return cpf;
	}
	
	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}
}
