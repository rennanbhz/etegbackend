package com.eteg.backend.movie;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.eteg.backend.etegbackend.user.User;

/** 
 * Represents a Movie
 *
 * @author Renan Mattos
 */
@Entity
public class Movie
{
	@Id @GeneratedValue private String movieName;
	@Column private String genre;
	@Column private String movieDirector;
	@Column private Integer quantity;
	
	@ManyToMany (cascade = CascadeType.ALL)
	private List<User> user;
	
	public Movie()
	{
		super();
	}

	/**
	 * Constructor with some defined informations.
	 *
	 * @param movieName name of movie
	 * @param genre genre of the movie 
	 * @param movieDirector Director of the movie 
	 * @param quantity Quantity of movies 
	 * @param user User that rent a movie 
	 */
	public Movie(String movieName, String genre, String movieDirector, Integer quantity, User user)
	{
		super();
		this.movieName = movieName;
		this.genre = genre;
		this.movieDirector = movieDirector;
		this.quantity = quantity;
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

	public String getGenre()
	{
		return genre;
	}

	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public String getMovieDirector()
	{
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector)
	{
		this.movieDirector = movieDirector;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}
	
	public List<User> getUserList()
	{
		return user;
	}
	
	public void setUserList(List<User> user)
	{
		this.user = user;
	}
}
