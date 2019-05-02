package com.eteg.backend.rentalhistoric;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.eteg.backend.rental.Rental;

/** 
 * Represents a Rental Historic of movies
 *
 * @author Renan Mattos
 */
@Entity
public class RentalHistoric
{
	@OneToMany (cascade = CascadeType.ALL)
	private List<Rental> rentals;
	
	public RentalHistoric()
	{
		super();
	}
	
	/**
	 * Constructor with some defined informations.
	 *
	 * @param Rental list of rentals
	 */
	public RentalHistoric(Integer rentalHistoricId, Rental rentals)
	{
		super();
		this.rentals = Collections.singletonList(rentals);
	}

	public List<Rental> getRentalHistoricList()
	{
		return rentals;
	}
}
