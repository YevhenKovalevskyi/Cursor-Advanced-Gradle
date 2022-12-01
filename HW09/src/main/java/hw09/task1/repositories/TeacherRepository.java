package hw09.task1.repositories;

import hw09.task1.entities.Teacher;

import org.springframework.data.repository.CrudRepository;

/**
 * @author YevhenKovalevskyi
 */
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

}
