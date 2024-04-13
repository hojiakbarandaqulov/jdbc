package service;

import dto.ProfileDTO;
import enoms.ProfileRole;
import enoms.ProfileStatus;
import repository.ProfileRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InitService {
     ProfileRepository repository=new ProfileRepository();

    public InitService() {
    }

    public void initAdmin() {

      /*  ProfileDTO byPhone = repository.getByPhone(phone);
        System.out.println(phone);
        if (byPhone!=null){
            return;
        }*/
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName("Ali");
        profileDTO.setSurname("Aliyev");
        profileDTO.setPhone("995092376");
        profileDTO.setPswd("12345");
        profileDTO.setCreatedDate(LocalDateTime.now());
        profileDTO.setStatus(ProfileStatus.ACTIVE);
        profileDTO.setRole(ProfileRole.ADMIN);
        profileDTO.setVisible(true);
        repository.createWithPSS(profileDTO);
    }
}
