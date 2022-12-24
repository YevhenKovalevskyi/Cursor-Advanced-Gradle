package hw10.task1.repositories;

import hw10.task1.entities.Student;

import org.springframework.data.repository.CrudRepository;

/**
 * @author YevhenKovalevskyi
 */
public interface StudentRepository extends CrudRepository<Student, Integer> {

}
