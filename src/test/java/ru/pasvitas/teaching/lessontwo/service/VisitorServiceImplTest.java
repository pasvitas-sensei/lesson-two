package ru.pasvitas.teaching.lessontwo.service;

import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.pasvitas.teaching.lessontwo.model.Visitor;
import ru.pasvitas.teaching.lessontwo.repository.VisitorRepository;
import ru.pasvitas.teaching.lessontwo.utils.NowService;

import static org.junit.jupiter.api.Assertions.*;

class VisitorServiceImplTest {

    private final Date date = new Date();

    private final VisitorRepository visitorRepository = Mockito.mock(VisitorRepository.class);
    private final NowService nowService = Mockito.mock(NowService.class);

    private final VisitorServiceImpl visitorService = new VisitorServiceImpl(visitorRepository, nowService);

    @BeforeEach
    void setUp() {
        Mockito.when(nowService.getCurrentDate()).thenReturn(date);
    }

    @DisplayName("Test register new visitor")
    @Test
    void registerVisitor() {
        Visitor inputVisitor = new Visitor(null, "Name", "Surname", 0, null);
        Visitor expectedVisitor = new Visitor(1L, "Name", "Surname", 0, null);
        Mockito.when(visitorRepository
                .getVisitorByNameAndSurname("Name", "Surname"))
                .thenReturn(Optional.empty());
        Visitor expectedToSave = new Visitor(null, "Name", "Surname", 0, null);
        Mockito.when(visitorRepository
                .save(expectedToSave))
                .thenReturn(expectedVisitor);
        assertEquals(expectedVisitor, visitorService.registerVisitor(inputVisitor));
    }

    @DisplayName("Test register a existing visitor")
    @Test
    void registerVisitorExisting() {
        Visitor inputVisitor = new Visitor(null, "Name", "Surname", 0, null);
        Visitor existingVisitor = new Visitor(1L, "Name", "Surname", 1, date);
        Mockito.when(visitorRepository
                        .getVisitorByNameAndSurname("Name", "Surname"))
                .thenReturn(Optional.of(existingVisitor));
        Visitor expectedToSave = new Visitor(1L, "Name", "Surname", 2, date);
        Visitor expectedVisitor = new Visitor(1L, "Name", "Surname", 2, date);
        Mockito.when(visitorRepository
                        .save(expectedToSave))
                .thenReturn(expectedVisitor);
        assertEquals(expectedVisitor, visitorService.registerVisitor(inputVisitor));
    }

    @DisplayName("Delete visitor")
    @Test
    void deleteVisitorById() {
        visitorService.deleteVisitorById(1L);
        Mockito.verify(visitorRepository, Mockito.times(1)).deleteById(1L);
    }
}