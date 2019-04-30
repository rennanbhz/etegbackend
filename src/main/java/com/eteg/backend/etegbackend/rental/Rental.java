package com.eteg.backend.etegbackend.rental;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.eteg.backend.etegbackend.user.User;
import com.eteg.backend.movie.Movie;

/** 
 * Represents a Rental of movie
 *
 * @author Renan Mattos
 */
@Entity
public class Rental
{
	@Id @ GeneratedValue private String movieName;
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
	 * @param movieName Name of the movie
	 * @param user Name of the user that rental movie
	 * @param dateOfReturn Date the movie was returned
	 */
	public Rental(String movieName, User user, Date dateOfReturn)
	{
		super();
		this.movieName = movieName;
		this.dateOfReturn = dateOfReturn;
		this.user = Collections.singletonList(user);
	}

	public String getMovieName()
	{
		return movieName;
	}

	public void setMovieName(String movieName)
	{
		this.movieName = movieName;
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
