package com.eteg.backend.rental;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.eteg.backend.movie.Movie;
import com.eteg.backend.user.User;

/** 
 * Represents a movie rental
 *
 * @author Renan Mattos
 */
@Entity
public class Rental
{
	@Id @ GeneratedValue private Integer rentalId;
	
	@Column private Date dateOfRent;
	@Column private Date dateOfReturn;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Movie> movie;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<User> user;
	
	public Rental()
	{
		super();
	}

	/**
	 * Constructor with some defined informations.
	 *
	 * @param rentalId Id of the rental
	 * @param movie Movie tha was rented
	 * @param user Name of the user that rental movie
	 * @param dateOfRent Date the movie was rented
	 * @param dateOfReturn Date the movie was returned
	 */
	public Rental(Integer rentalId, Movie movie, User user, Date dateOfRent, Date dateOfReturn)
	{
		super();
		this.rentalId = rentalId;
		this.dateOfRent = dateOfRent;
		this.dateOfReturn = dateOfReturn;
		this.user = Collections.singletonList(user);
		this.movie = Collections.singletonList(movie);
	}

	public Integer getRentalId()
	{
		return rentalId;
	}

	public void setRentalId(Integer rentalId)
	{
		this.rentalId = rentalId;
	}

	public Date getDateOfReturn()
	{
		return dateOfReturn;
	}

	public void setDateOfReturn(Date dateOfReturn)
	{
		this.dateOfReturn = dateOfReturn;
	}
	
	public List<User> getUserList()
	{
		return user;
	}
	
	public List<Movie> getMovieList()
	{
		return movie;
	}
}
