package com.eteg.backend.rental;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eteg.backend.movie.Movie;
import com.eteg.backend.user.User;

/**
 * Class responsible for exposing the Rental operations regarding the means of persistence.
 *
 * @author Renan Mattos
 */
@Repository
public interface RentalRepository extends CrudRepository<Rental, Integer>
{

	Rental save(Movie movie, User user);

}
