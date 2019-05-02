package com.eteg.backend.movie;

import java.util.ArrayList;
import java.util.List;
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

import com.eteg.backend.user.User;
import com.eteg.backend.user.UserRepository;

/**
 * Class responsible for exposing Movie Rest operations.
 *
 * @author Renan Mattos
 */
@RestController
public class MovieController
{
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private UserRepository userRepository;

	/**
	 * Creates a movie in database.
	 * 
	 * @param movie Information to be assigned to a new user
	 * @return Movie with id filled in
	 */
	@PostMapping("/movie")
	public ResponseEntity<Object> createMovie(@RequestBody Movie movie)
	{
		Movie createdMovie = movieRepository.save(movie);

		return ResponseEntity.ok(createdMovie);
	}

	/**
	 * Gets all registered movies in the database.
	 * 
	 * @return List of registered movies
	 */
	@GetMapping("/movie")
	public List<Movie> searchAllMovies()
	{
		List<Movie> movieList = new ArrayList<>();

		movieRepository.findAll().forEach(movie -> movieList.add(movie));

		return movieList;
	}

	/**
	 * Gets the movie information by its identifier
	 * 
	 * @param movieName Movie identificator
	 * @return movie by the name
	 */
	@GetMapping("/movie/{movieName}")
	public ResponseEntity<Movie> searchMovieByName(@PathVariable String movieName)
	{
		Optional<Movie> movie = movieRepository.findByName(movieName);

		if (!movie.isPresent())
		{
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(movie.get());
	}

	/**
	 * Search for Movie list using user Id as filter
	 * 
	 * @param userId user identificator
	 * @return List of movies
	 */
	@GetMapping("/movie/user/{userId}")
	public List<Movie> searchForUserIdfilter(@PathVariable Integer userId)
	{
		Optional<User> user = userRepository.findById(userId);

		return user.get().getMovieList();
	}

	/**
	 * Updates a movie information.
	 *
	 * @param movie     Information to update
	 * @param movieName Name of the move to be updated
	 * @return Status Ok
	 */
	@PutMapping("/movie/{movieName}")
	public ResponseEntity<Object> updateUser(@RequestBody Movie movie, @PathVariable String movieName)
	{
		Optional<Movie> movieLoad = movieRepository.findByName(movieName);

		if (!movieLoad.isPresent())
		{
			return ResponseEntity.notFound().build();
		}

		movie.setMovieName(movieName);

		movieRepository.save(movie);

		return ResponseEntity.noContent().build();
	}

	/**
	 * Associates movie with users
	 * 
	 * @param movie  Movie to be associated
	 * @param userId user identificator
	 * @return status OK
	 */
	@PutMapping("/movie/user{userId}")
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
	 * Delete movie by name
	 * 
	 * @param movieName Movie identificator
	 */
	@DeleteMapping("/movie/{movieName}")
	public void deleteMovie(@PathVariable String movieName)
	{
		movieRepository.deleteByName(movieName);
	}
}
