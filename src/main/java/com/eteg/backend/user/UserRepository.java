package com.eteg.backend.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Class responsible for exposing the User operations regarding the means of persistence.
 *
 * @author Renan Mattos
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
	
}
