package ru.pasvitas.teaching.lessontwo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.pasvitas.teaching.lessontwo.model.Visitor;

//Репозиторий для работы с БД. Интерфейс расширяет JpaRepository<Тип данных, Тип ключа>
//Реализацию сделает Spring
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    //Получение списка всех посетителей
    //Аналог SQL : SELECT * FROM visitors;
    List<Visitor> getVisitorsBy();

    //Получение посетителя по имени и фамилии
    //Аналог SQL: SELECT * FROM visitors WHERE name = name, surname = surname
    Optional<Visitor> getVisitorByNameAndSurname(String name, String surname);
}
