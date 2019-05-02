package com.eteg.backend.rental;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eteg.backend.movie.Movie;
import com.eteg.backend.movie.MovieRepository;
import com.eteg.backend.user.User;
import com.eteg.backend.user.UserRepository;

/**
 * Class responsible for exposing Rental Rest operations.
 *
 * @author Renan Mattos
 */
@RestController
public class RentalController
{
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RentalRepository rentalRepository;
	

	/**
	 * Creates a movie in database.
	 * 
	 * @param movie Information to be assigned to a new user
	 * @return Movie with id filled in
	 */
	@PostMapping("/rents")
	public ResponseEntity<Object> rentMovie(@RequestBody Movie movie, User user)
	{
		Rental rentedMovie = rentalRepository.save(movie, user);
		
		return ResponseEntity.ok(rentedMovie);
	}

	/**
	 * Gets the rental information by its identifier
	 * 
	 * @param rentalId Rent identificator
	 * @return rents by the Id
	 */
	@GetMapping("/rents/{rentalId}")
	public ResponseEntity<Rental> searchRentalById(@PathVariable Integer rentalId)
	{
		Optional<Rental> rent = rentalRepository.findById(rentalId);

		if (!rent.isPresent())
		{
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(rent.get());
	}

	/**
	 * Search for Rent list using user Id as filter
	 * 
	 * @param userId user identificator
	 * @return List of movies
	 */
	@GetMapping("/rent/user/{userId}")
	public ResponseEntity<Rental> searchForUserIdfilter(@PathVariable Integer userId)
	{
		Optional<Rental> rent = rentalRepository.findById(userId);

		return ResponseEntity.ok(rent.get());
	}

	/**
	 * Updates a rent information.
	 *
	 * @param rent Information to update
	 * @param rentId Id of the rent to be updated
	 * @return Status Ok
	 */
	@PutMapping("/rent/{rentalId}")
	public ResponseEntity<Object> updateRents(@RequestBody Rental rent, @PathVariable Integer rentalId)
	{
		Optional<Rental> rentalLoad = rentalRepository.findById(rentalId);

		if (!rentalLoad.isPresent())
		{
			return ResponseEntity.notFound().build();
		}

		rent.setRentalId(rentalId);

		rentalRepository.save(rent);

		return ResponseEntity.noContent().build();
	}

	/**
	 * Associates movie with users
	 * 
	 * @param movie  Movie to be associated
	 * @param userId user identificator
	 * @return status OK
	 */
	@PutMapping("/rent/user{userId}")
	public ResponseEntity<Object> associateMovieToUser(@RequestBody User user, @PathVariable String movieName)
	{
		Optional<Movie> movie = movieRepository.findByName(movieName);

		if (!movie.isPresent())
		{
			return ResponseEntity.notFound().build();
		}

		Optional<User> userLoad = userRepository.findById(user.getUserId());

		if (!userLoad.isPresent())
		{
			return ResponseEntity.notFound().build();
		}

		if (movie.get().getUserList() == null)
		{
			user.setMovieList(new ArrayList<>());
		}

		movie.get().getUserList().add(userLoad.get());
		movieRepository.save(movie.get());

		return ResponseEntity.noContent().build();
	}

	/**
	 * Delete rent by id
	 * 
	 * @param rentalId rent identificator
	 */
	@DeleteMapping("/rental/{rentalId}")
	public void deleteMovie(@PathVariable Integer rentalId)
	{
		rentalRepository.deleteById(rentalId);
	}
}
