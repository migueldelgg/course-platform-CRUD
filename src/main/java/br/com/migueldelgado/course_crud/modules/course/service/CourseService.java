package br.com.migueldelgado.course_crud.modules.course.service;

import br.com.migueldelgado.course_crud.exceptions.CourseNotFoundException;
import br.com.migueldelgado.course_crud.modules.course.dto.CourseDTO;
import br.com.migueldelgado.course_crud.modules.course.entity.CourseEntity;
import br.com.migueldelgado.course_crud.modules.course.entity.EnumActive;
import br.com.migueldelgado.course_crud.modules.course.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    private CourseDTO courseDTO;

    public CourseEntity findByIdOrThrowBadRequestException(Long id) {
        return courseRepository.findById(id).orElseThrow(()
                -> new CourseNotFoundException()
        );
    }

    public CourseEntity execute(CourseEntity courseEntity) {

        return courseRepository.save(courseEntity);

    }

    public List<CourseDTO> listAll() {

        List<CourseDTO> list = new ArrayList<>();

        for (CourseEntity i : courseRepository.findAll()) {

            var course = courseDTO.builder().name(i.getName()).category(i.getCategory())
                    .active(i.getActive()).build();

            list.add(course);
        }

        return list;
    }

    public void replace(CourseDTO courseDTO, Long id) {

        var savedCourse = findByIdOrThrowBadRequestException(id);

        CourseEntity course = CourseEntity.builder().id(savedCourse.getId())
                .name(courseDTO.getName())
                .category(courseDTO.getCategory())
                .active(courseDTO.getActive())
                .created_at(savedCourse.getCreated_at())
                .updated_at(Date.from(Instant.now())).build();

        execute(course);
    }

    public void delete(Long id) {

        findByIdOrThrowBadRequestException(id);

        courseRepository.deleteById(id);
    }

    public void patchUpdate(Long id, EnumActive enumActive) {

        var savedCourse = findByIdOrThrowBadRequestException(id);

        // Atualizar o estado ativo/inativo do curso
        savedCourse.setActive(enumActive);

        // Salvar as alterações no banco de dados
        execute(savedCourse);
    }

}
