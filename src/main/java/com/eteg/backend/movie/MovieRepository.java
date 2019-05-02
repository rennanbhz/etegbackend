package com.eteg.backend.movie;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Class responsible for exposing the Movie operations regarding the means of persistence.
 *
 * @author Renan Mattos
 */
@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer>
{
	Optional<Movie> findByName(String movieName);

	void deleteByName(String movieName);
}
