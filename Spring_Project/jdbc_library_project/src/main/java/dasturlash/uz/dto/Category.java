package dasturlash.uz.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
@ToString
public class Category {
    private Integer id;
    private String name;
    private LocalDate createdDate;
    private boolean visible;
}
