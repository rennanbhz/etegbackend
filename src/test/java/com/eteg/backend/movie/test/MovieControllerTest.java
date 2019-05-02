package com.eteg.backend.movie.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.eteg.backend.movie.Movie;
import com.eteg.backend.movie.MovieRepository;

/**
 * Unit test to MovieController class.
 * 
 * @author Renan Mattos
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MovieControllerTest.class)

public class MovieControllerTest 
{
	@Autowired private MockMvc mvc;
	
	@MockBean private MovieRepository movieRepository;

	private static final String MOVIE_NAME = "Star Wars III - The revenge of Sith";
	private static final String MOVIE_GENRE = "Ficction";
	private static final String MOVIE_DIRECTOR = "George Luccas";
	private static final Integer MOVIE_QUANTITY = 5;
	
	private Movie mockMovie = new Movie(MOVIE_NAME, MOVIE_GENRE, MOVIE_DIRECTOR, MOVIE_QUANTITY, null);
	private String mockMovieJson =
		      "{\"movieName\":\"Star Wars III - The revenge of Sith\",\"genre\":\"Ficction\",\"movieDirector\":\"George Luccas\",\"quantity\":\"5\"}";
	
	@Test
	public void createMovie_withValidInformation_returnCreated() throws Exception
	{
		when(movieRepository.save(Mockito.any())).thenReturn(mockMovie);
		
		mvc.perform(
	            post("/movie")
	                .accept(MediaType.APPLICATION_JSON)
	                .content(mockMovieJson)
	                .contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.movieName", is(MOVIE_NAME)));
	}
	
	@Test
	public void deleteMovie_withValidInformation_returnSuccess() throws Exception
	{
		 when(movieRepository.findByName(MOVIE_NAME)).thenReturn(Optional.of(mockMovie));
		    when(movieRepository.save(Mockito.any())).thenReturn(mockMovie);

		    mvc.perform(
		            put("/user/" + MOVIE_NAME)
		                .accept(MediaType.APPLICATION_JSON)
		                .content(mockMovieJson)
		                .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isNoContent());
	}
	
	@Test
	public void searchForAllMovies_withoutFilter_shouldReturnMovieList() throws Exception
	{
		List<Movie> movieList = Collections.singletonList(mockMovie);

	    when(movieRepository.findAll()).thenReturn(movieList);

	    mvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$", hasSize(1)))
	        .andExpect(jsonPath("$[0].movieName", is(mockMovie.getMovieName())));
	}
	
	@Test
	public void searchForAllMovies_filteringByMovieName_shouldReturMovieList() throws Exception {

	    when(movieRepository.findByName(MOVIE_NAME)).thenReturn(Optional.of(mockMovie));

	    mvc.perform(get("/movie/" + MOVIE_NAME).contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.movieName", is(mockMovie.getMovieName())));
	  }
	
	@Test
	public void deleteMovie_withValidInformation_returnOKStatus() throws Exception {

	    mvc.perform(
	            delete("/movie/" + MOVIE_NAME)
	                .accept(MediaType.APPLICATION_JSON)
	                .content(mockMovieJson)
	                .contentType(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk());
	  }
}
