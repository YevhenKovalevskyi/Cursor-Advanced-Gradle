package hw07.task1.repositories;

import hw07.task1.entities.Group;

import org.springframework.data.repository.CrudRepository;

/**
 * @author YevhenKovalevskyi
 */
public interface GroupRepository extends CrudRepository<Group, Integer> {

}
