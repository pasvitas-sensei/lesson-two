package ru.pasvitas.teaching.lessontwo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pasvitas.teaching.lessontwo.model.Visitor;
import ru.pasvitas.teaching.lessontwo.model.VisitorHistory;
import ru.pasvitas.teaching.lessontwo.repository.VisitorRepository;
import ru.pasvitas.teaching.lessontwo.utils.NowService;

@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorsRepository;

    private final NowService nowService;

    @Override
    public Visitor registerVisitor(Visitor visitor) {
        //Получаем посетителя по имени и фамилии
        //Optional - оболочка над каким то данными. Если они есть - get() вернет не null, isPresent() - true. +доп методы
        //Если внутри данных нет и лежит null - get() вернет null, isPresent() - false
        Optional<Visitor> visitorOptional = visitorsRepository.getVisitorByNameAndSurname(visitor.getName(), visitor.getSurname());
        if (visitorOptional.isPresent()) {
            //Если посетитель есть в базе - добавляем счетчик посещений
            //Warning. Тут мы буквально переопределяем параметр на объект из Optional. Делать крайне не рекомендуется, тут лишь для примера
            visitor = visitorOptional.get();
            visitor.setTimeVisited(visitor.getTimeVisited() + 1);
            visitor.setLastVisited(nowService.getCurrentDate());
            visitor.getVisitorHistory().add(new VisitorHistory(null, visitor, new Date()));
        }
        else {
            //Если посетителя нет - значит надо записать, на всякий зануляем id, чтобы пользователь не подставлял свой на этапе запроса
            visitor.setId(null);
        }
        //Сохраняем в БД
        return visitorsRepository.saveAndFlush(visitor);
    }

    @Override
    public List<Visitor> getAllVisitors() {
        //Получение списка всех посетителей
        return visitorsRepository.getVisitorsBy();
    }

    @Override
    public Optional<Visitor> getVisitorById(Long id) {
        //Репозиторий имеет и отдельные методы для поиска - например, поиск по ИД
        return visitorsRepository.findById(id);
    }

    @Override
    public void deleteVisitorById(Long id) {
        visitorsRepository.deleteById(1L);
    }
}
