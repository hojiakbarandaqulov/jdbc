package dasturlash.uz.service;

import dasturlash.uz.Container.ComponentContainer;
import dasturlash.uz.Controller.AdminController;
import dasturlash.uz.Controller.StaffController;
import dasturlash.uz.Controller.StudentController;
import dasturlash.uz.dto.Profile;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.util.MD5Util;

import java.time.LocalDateTime;

public class AuthService {

    public void login(String login, String password) {
        ProfileRepository profileRepository = new ProfileRepository();
        Profile profile = profileRepository.getByLogin(login);
        if (profile == null) {
            System.out.println("Login or Password wrong. ");
            return;
        }
        String md5Hash = MD5Util.encode(password);
        if (!md5Hash.equals(profile.getPassword())) {
            System.out.println("Login or Password wrong. ");
            return;
        }
        if (!profile.getStatus().equals(ProfileStatus.ACTIVE)) {
            System.out.println("Wrong status mazgi.");
            return;
        }
        System.out.println("** Welcome to library to project **");
        if (profile.getRole().equals(ProfileRole.STUDENT)) {
            ComponentContainer.studentController.start();
        } else if (profile.getRole().equals(ProfileRole.ADMIN)) {
            ComponentContainer.adminController.start();
        } else if (profile.getRole().equals(ProfileRole.STAFF)) {
            ComponentContainer.staffController.start();
        }
    }

    public void registration(Profile profile) {
        if (!isValid(profile)) {
            return;
        }
        ProfileRepository profileRepository = new ProfileRepository();
        Profile existProfile = profileRepository.getByLogin(profile.getLogin());
        if (existProfile != null) {
            System.out.println("Login exist. Please choose other login. Mazgi");
            return;
        }
        profile.setCreatedDate(LocalDateTime.now());
        profile.setRole(ProfileRole.STUDENT);
        profile.setStatus(ProfileStatus.ACTIVE);
        profile.setPassword(MD5Util.encode(profile.getPassword()));
        int effectedRows = profileRepository.create(profile);
        if (effectedRows == 1) {
            System.out.println("Registration completed");
        }
    }

    public boolean isValid(Profile profile) {
        String s = "A";
        if (profile.getName() == null || profile.getName().isBlank() || profile.getName().length() < 2) {
            System.out.println("Name is wrong. ");
            return false;
        }
        if (profile.getSurname() == null || profile.getSurname().isBlank() || profile.getSurname().length() < 2) {
            System.out.println("Surname is wrong. ");
            return false;
        }
        if (profile.getPassword() == null || profile.getPassword().isBlank() || profile.getPassword().length() < 5) {
            System.out.println("Password is wrong. ");
            return false;
        }
        if (profile.getLogin() == null || profile.getLogin().isBlank() || profile.getLogin().length() < 3) {
            System.out.println("Login is wrong. ");
            return false;
        }
        if (profile.getPhone() == null || profile.getPhone().isBlank()
                || profile.getPhone().length() != 12
                || profile.getPhone().startsWith("998")
                || !isOnlyNumber(profile.getPhone())) {
            System.out.println("Phone is wrong. ");
            return false;
        }
        return true;
    }

    public boolean isOnlyNumber(String input) {
        char[] arr = input.toCharArray();
        for (char c : arr) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
