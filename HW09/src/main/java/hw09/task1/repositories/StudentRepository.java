package hw09.task1.repositories;

import hw09.task1.entities.Student;

import org.springframework.data.repository.CrudRepository;

/**
 * @author YevhenKovalevskyi
 */
public interface StudentRepository extends CrudRepository<Student, Integer> {

}
