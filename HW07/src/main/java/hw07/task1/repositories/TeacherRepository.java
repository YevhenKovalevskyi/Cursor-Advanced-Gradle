package hw07.task1.repositories;

import hw07.task1.entities.Teacher;
import org.springframework.data.repository.CrudRepository;

/**
 * @author YevhenKovalevskyi
 */
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {


}
