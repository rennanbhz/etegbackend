package com.eteg.backend.user;

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

import com.eteg.backend.movie.Movie;
import com.eteg.backend.movie.MovieRepository;
import com.eteg.backend.util.ValidatorUtils;

/**
 * Class responsible for exposing User Rest operations.
 *
 * @author Renan Mattos
 */
@RestController
public class UserController
{
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private UserRepository userRepository;

	/**
	 * Creates a user in database.
	 * 
	 * @param user Information to be assigned to a new user
	 * @return User with id filled in
	 */
	@PostMapping("/user")
	public ResponseEntity<Object> createUser(@RequestBody User user)
	{
		User createdUser = userRepository.save(user);

		try
		{
			if (ValidatorUtils.isValidCPF(createdUser.getCpf())
				& ValidatorUtils.isValidName(createdUser.getName())
				& ValidatorUtils.isOverAge(createdUser.getBirthDate()));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(createdUser);
	}

	/**
	 * Gets all registered users in the database.
	 * 
	 * @return List of registered users
	 */
	@GetMapping("/user")
	public List<User> searchAllUsers()
	{
		List<User> userList = new ArrayList<>();

		userRepository.findAll().forEach(user -> userList.add(user));

		return userList;
	}

	/**
	 * Gets the user information by its identifier
	 * 
	 * @param userId User identificator
	 * @return user by the its identificator
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<User> searchUserById(@PathVariable Integer userId)
	{
		Optional<User> user = userRepository.findById(userId);

		if (!user.isPresent())
		{
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(user.get());
	}

	/**
	 * Search for User list using movie name as filter
	 * 
	 * @param movieName user identificator
	 * @return List of users
	 */
	@GetMapping("/user/movie/{movieName}")
	public List<User> searchForMovieNameFilter(@PathVariable String movieName)
	{
		Optional<Movie> movie = movieRepository.findByName(movieName);

		return movie.get().getUserList();
	}

	/**
	 * Updates a user's information.
	 *
	 * @param user   Information to update
	 * @param userId Identifier of the user to be updated
	 * @return Status Ok
	 */
	@PutMapping("/user/{userId}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Integer userId)
	{
		Optional<User> userLoad = userRepository.findById(userId);

		if (!userLoad.isPresent())
		{
			return ResponseEntity.notFound().build();
		}

		user.setUserId(userId);

		userRepository.save(user);

		return ResponseEntity.noContent().build();
	}

	/**
	 * Delete user by Id
	 * 
	 * @param userId User identificator
	 */
	@DeleteMapping("/movie/{movieName}")
	public void deleteUser(@PathVariable Integer userId)
	{
		userRepository.deleteById(userId);
	}
}
