package ru.pasvitas.teaching.lessontwo.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pasvitas.teaching.lessontwo.model.Visitor;
import ru.pasvitas.teaching.lessontwo.service.VisitorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/visitors") //На весь контроллер - один путь-префикс
public class VisitorController {

    private final VisitorService visitorService;

    //Реагирует на POST /visitors
    @PostMapping
    ResponseEntity<Visitor> registerVisitor(@RequestBody Visitor visitor) {
        return ResponseEntity.ok(visitorService.registerVisitor(visitor));
    }

    //Реагирует на GET /visitors
    @GetMapping
    ResponseEntity<List<Visitor>> getVisitors() {
        return ResponseEntity.ok(visitorService.getAllVisitors());
    }

    //Реагирует на GET /visitors/id, где id - идентификатор
    @GetMapping("/{id}") //{id} - название переменной для пути
    ResponseEntity<Visitor> getVisitor(@PathVariable("id") Long id) { //Из пути переменная пойдет прямиком в {id}
        //Лмябды - если посетитель найдется - возвращаем OK с телом его, если нет - notFound
        return visitorService.getVisitorById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        //Без лямбды эт выглядело бы так:
        /*
        Optional<Visitor> visitor = visitorService.getVisitorById(id);
        if (visitor.isPresent()) {
            return ResponseEntity.ok(visitor.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
         */
    }

}
