package com.eteg.backend.rentalhistoric;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.eteg.backend.rental.Rental;
import com.eteg.backend.rental.RentalRepository;

/**
 * Class responsible for exposing historic of rentals Rest operations.
 *
 * @author Renan Mattos
 */
@RestController
public class RentalHistoricController
{
	@Autowired
	private RentalRepository rentalRepository;

	/**
	 * Gets all registered movies in the database.
	 * 
	 * @return List of registered movies
	 */
	@GetMapping("/rentalHistoric")
	public List<Rental> searchAllRents()
	{
		List<Rental> rentalList = new ArrayList<>();

		rentalRepository.findAll().forEach(rental -> rentalList.add(rental));

		return rentalList;
	}

	/**
	 * Gets the rental historic information by its identifier
	 * 
	 * @param rentalId Rental identificator
	 * @return rents by the Id
	 */
	@GetMapping("/rental/{rentalId}")
	public ResponseEntity<Rental> searchMovieByName(@PathVariable Integer rentalId)
	{
		Optional<Rental> rent = rentalRepository.findById(rentalId);

		if (!rent.isPresent())
		{
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(rent.get());
	}
}
