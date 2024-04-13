package dasturlash.uz.service;

import dasturlash.uz.dto.Profile;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.util.MD5Util;

import java.time.LocalDateTime;

public class InitService {
    public void initAdmin() {
        String login = "admin";
        ProfileRepository profileRepository = new ProfileRepository();
        Profile byLogin = profileRepository.getByLogin("admin");
        if (byLogin != null) {
            return;
        }
        Profile admin = new Profile();
        admin.setName("Admin");
        admin.setSurname("Adminov");
        admin.setLogin(login);
        admin.setPassword(MD5Util.encode("12345"));
        admin.setPhone("995092376");
        admin.setStatus(ProfileStatus.ACTIVE);
        admin.setRole(ProfileRole.ADMIN);
        admin.setCreatedDate(LocalDateTime.now());

        profileRepository.create(admin);
    }

    public void initTestStudent() {
        String login = "testStudent";
        ProfileRepository profileRepository = new ProfileRepository();
        Profile byLogin = profileRepository.getByLogin("testStudent");
        if (byLogin != null) {
            return;
        }
        Profile admin = new Profile();
        admin.setName("StudentJon");
        admin.setSurname("StudentJonov");
        admin.setLogin(login);
        admin.setPassword(MD5Util.encode("12345"));
        admin.setPhone("992431068");
        admin.setStatus(ProfileStatus.ACTIVE);
        admin.setRole(ProfileRole.ADMIN);
        admin.setCreatedDate(LocalDateTime.now());

        profileRepository.create(admin);
    }
}

