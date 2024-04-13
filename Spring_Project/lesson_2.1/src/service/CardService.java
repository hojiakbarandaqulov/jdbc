package service;

import dto.CardDTO;
import repository.CardRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CardService {
    private CardRepository cardRepository = new CardRepository();

    public void createCard(Long number,LocalDate expDate) {
        CardDTO card = new CardDTO();
        if (number== 0) {
            System.out.println("Card number is not found");
        }
        if (expDate == null) {
            System.out.println("Card exp_date is not found");
        }
        cardRepository.createCard(number,expDate);
    }
}