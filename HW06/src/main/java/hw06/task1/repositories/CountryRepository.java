package hw06.task1.repositories;

import hw06.task1.entities.Country;

import org.springframework.data.repository.CrudRepository;

/**
 * @author YevhenKovalevskyi
 */
public interface CountryRepository extends CrudRepository<Country, Integer> {

}
