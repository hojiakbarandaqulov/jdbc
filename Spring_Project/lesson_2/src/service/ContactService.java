package service;

import dto.ContactDTO;
import repository.ContactRepository;

import java.util.List;
import java.util.Scanner;

public class ContactService {
    private ContactRepository contactRepository;

    public ContactService() {
        contactRepository = new ContactRepository();
    }

    public void save(ContactDTO dto) {
        ContactDTO exists = contactRepository.getByPhone(dto.getPhone());
        if (exists != null) {
            System.out.println("Phone already exists");
            return;
        }
        //save
        boolean result = contactRepository.saveContact(dto);
        if (result) {
            System.out.println("dto.Contact added");
        } else {
            System.out.println("Something wend wrong. Mazgi");
        }
    }

    public void contactList() {
        // ContactRepository contactRepository = new ContactRepository();
        List<ContactDTO> contactDTOList = contactRepository.getList();
        for (ContactDTO contactDTO : contactDTOList) {
            System.out.println(contactDTO.getId() + " " + contactDTO.getName() + " " + contactDTO.getSurname() + " " + contactDTO.getPhone());
        }
    }

    public void deleteContact(String phone) {
        ContactDTO contactDTO = contactRepository.getByPhone(phone);
        if (contactDTO == null) {
            System.out.println("dto.Contact not exists. Mazgi");
            return;
        }
        int effectedRows = contactRepository.delete(phone);  //delete
        if (effectedRows == 1) {
            System.out.println("dto.Contact successfully deleted.");
        }
    }
    public void search(String query) {
        List<ContactDTO> contactDTOList = contactRepository.search(query);
        for (ContactDTO contactDTO : contactDTOList) {
            System.out.println(contactDTO.getId() + " " + contactDTO.getName()
                    + " " + contactDTO.getSurname() + " " + contactDTO.getPhone());
        }
    }
}
