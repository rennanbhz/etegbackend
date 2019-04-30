package com.eteg.backend.etegbackend.rentalhistoric;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** 
 * Represents a Rental Historic of movies
 *
 * @author Renan Mattos
 */
@Entity
public class RentalHistoric
{
	
	@Id @GeneratedValue private String movieName;
	@Column private Date rentalDate;
	
	public RentalHistoric()
	{
		super();
	}
	
	/**
	 * Constructor with some defined informations.
	 *
	 * @param movieName name of movie
	 * @param rentalDate Date the movie was Rented
	 */
	public RentalHistoric(String movieName, Date rentalDate)
	{
		super();
		this.movieName = movieName;
		this.rentalDate = rentalDate;
	}

	public String getMovieName()
	{
		return movieName;
	}

	public void setMovieName(String movieName)
	{
		this.movieName = movieName;
	}

	public Date getRentalDate()
	{
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate)
	{
		this.rentalDate = rentalDate;
	}
}
