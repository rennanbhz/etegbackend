package com.eteg.backend.movie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	 */
	public Movie(String movieName, String genre, String movieDirector, Integer quantity)
	{
		super();
		this.movieName = movieName;
		this.genre = genre;
		this.movieDirector = movieDirector;
		this.quantity = quantity;
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
}
