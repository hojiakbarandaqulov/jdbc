package service;

import Controller.CardController;
import dto.ProfileDTO;
import repository.ProfileRepository;

public class AuthService {
    private ProfileRepository profileRepository = new ProfileRepository();
    private CardController mainController = new CardController();

    public void login(String phone, String password) {
        ProfileDTO profile = profileRepository.getProfileByPhone(phone, password);
        if (profile.getPhone() == null) {
            System.out.println("Profile phone is not found");
        }
        if (profile.getPswd() == null) {
            System.out.println("Profile password is not found");
        }
        mainController.start();
    }

    public void registration(ProfileDTO profileDTO) {
        ProfileDTO profile = profileRepository.getByRegistration(profileDTO.getPhone());
        if (profileDTO.getName() == null) {
            System.out.println("Profile name is not found");
        }
        if (profileDTO.getSurname() == null) {
            System.out.println("Profile surname is not found");
        }
        if (profileDTO.getPhone() == null) {
            System.out.println("Profile phone is not found");
        }
        if (profileDTO.getPswd() == null) {
            System.out.println("Profile pswd is not found");
        }
    }
}
