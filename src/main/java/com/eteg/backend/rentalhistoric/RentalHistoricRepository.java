package com.eteg.backend.rentalhistoric;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Class responsible for exposing the Rental Historic operations regarding the means of persistence.
 *
 * @author Renan Mattos
 */
@Repository
public interface RentalHistoricRepository extends CrudRepository<RentalHistoric, Integer>
{

}
