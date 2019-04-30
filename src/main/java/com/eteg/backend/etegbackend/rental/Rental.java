package com.eteg.backend.etegbackend.rental;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** 
 * Represents a Rental of movie
 *
 * @author Renan Mattos
 */
@Entity
public class Rental
{
	@Id @ GeneratedValue private String movieName;
	@Column private String user;
	@Column private Date dateOfReturn;
	
	public Rental()
	{
		super();
	}

	/**
	 * Constructor with some defined informations.
	 *
	 * @param movieName Name of the movie
	 * @param user Name of the user that rental movie
	 * @param dateOfReturn Date the movie was returned
	 */
	public Rental(String movieName, String user, Date dateOfReturn)
	{
		super();
		this.movieName = movieName;
		this.user = user;
		this.dateOfReturn = dateOfReturn;
	}

	public String getMovieName()
	{
		return movieName;
	}

	public void setMovieName(String movieName)
	{
		this.movieName = movieName;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public Date getDateOfReturn()
	{
		return dateOfReturn;
	}

	public void setDateOfReturn(Date dateOfReturn)
	{
		this.dateOfReturn = dateOfReturn;
	}
}
