package br.com.migueldelgado.course_crud.modules.course.controller;

import br.com.migueldelgado.course_crud.modules.course.dto.CourseDTO;
import br.com.migueldelgado.course_crud.modules.course.entity.CourseEntity;
import br.com.migueldelgado.course_crud.modules.course.entity.EnumActive;
import br.com.migueldelgado.course_crud.modules.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/")
    public ResponseEntity<CourseEntity> save(@RequestBody CourseEntity courseEntity){

        return ResponseEntity.ok(courseService.execute(courseEntity));

    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> listAll(){
        return ResponseEntity.ok(courseService.listAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@Valid @RequestBody CourseDTO courseDTO, @PathVariable Long id) {

        courseService.replace(courseDTO, id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        courseService.delete(id);

        return ResponseEntity.ok().body("Curso deletado com sucesso.");
    }

    @PatchMapping(path = "/{id}/{enumActive}")
    public Object patchUpdate(@PathVariable Long id, @PathVariable EnumActive enumActive) {

        courseService.patchUpdate(id, enumActive);

        return ResponseEntity.ok().body("Campo atualizado com sucesso.");
    }

}
