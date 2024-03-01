package ru.pasvitas.teaching.lessontwo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor //Генерируем конструктор с параметрами
@NoArgsConstructor //Генерируем конструктор без параметров
@Getter //Генерируем геттеры
@Setter //Генерируем сеттеры
@ToString //Отдельный метод для toString
@Entity(name = "visitors") //Объявляем класс как сущность для работы с ним в БД и его имя
@Table(name = "visitors") //Помечаем, как называется таблица в БД
public class Visitor {

    @Id //Первичный ключ всему голова
    @Column(name = "id") //Название поля/столбца
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Автогененерация значений по возрастанию - 1-2-3 и тп
    //Делаем Long в упаковке на случай, если сохраняем новую запись и у неё не будет идентификатра
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "time_visited")
    private int timeVisited;
}
