package dasturlash.uz.dto;

import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Profile {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String phone;
    private ProfileStatus status;
    private ProfileRole role;
    private LocalDateTime createdDate;
}
