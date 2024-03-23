package ru.pasvitas.teaching.lessontwo.service;

import java.util.List;
import java.util.Optional;
import ru.pasvitas.teaching.lessontwo.model.Visitor;

//Интерфейс для сервиса
public interface VisitorService {

    Visitor registerVisitor(Visitor visitor);

    List<Visitor> getAllVisitors();

    Optional<Visitor> getVisitorById(Long id);

    void deleteVisitorById(Long id);
}
