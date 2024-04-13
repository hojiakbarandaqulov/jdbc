package dasturlash.uz.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Book {
    private Integer id;
    private String title;
    private String author;
    private Integer category;
    private Integer available_day;
    private LocalDateTime publish_date;

}
