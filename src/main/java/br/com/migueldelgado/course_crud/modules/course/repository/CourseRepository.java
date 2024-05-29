package br.com.migueldelgado.course_crud.modules.course.repository;

import br.com.migueldelgado.course_crud.modules.course.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {



}
