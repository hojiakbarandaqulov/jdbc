import SmsPeckege.AddSms;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Sms {
    public static List<AddSms>smsList=new LinkedList<>();
    public static void main(String[] args) {
        SmsManager.createTable();
        boolean b=true;
        while (b){
            menu();
            switch (getAction()){
                case 1:
                    SmsManager.addSms(Sms());
                    break;
                case 2:
                    SmsManager.smsList();
                    break;
                case 3:
                    SmsManager.deleteSms(returnDelete());
                    break;
                case 4:
                    SmsManager.searchSms(returnSearch());
                    break;
                case 0:
                    b=false;
                    System.out.println("program finished!!!");
            }
        }
    }
    public static String returnSearch(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search: ");
        String searchName=scanner.next();
        return searchName;
    }
    public static Integer returnDelete(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter delete: ");
        Integer deleteId=scanner.nextInt();
        return deleteId;
    }
    public static Integer getAction(){
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter action: ");
        int action=scanner.nextInt();
        return action;
    }
    public static void menu() {
        System.out.println("*** Sms manager ***");
        System.out.println("*** Menu ***");
        System.out.println("1. Add sms");
        System.out.println("2. Sms List");
        System.out.println("3. Delete Sms");
        System.out.println("4. Search Sms ");
        System.out.println("0. Exit");
    }
    public static AddSms Sms(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter smsId: ");
        Integer smsId=scanner.nextInt();
        System.out.println("Enter smsName: ");
        String smsName=scanner.next();
        System.out.println("Enter smsPhone: ");
        String smsPhone=scanner.next();
        System.out.println("Enter sms: ");
        String sms =scanner.next();
        AddSms addSms = new AddSms();
        addSms.setId(smsId);
        addSms.setSmsName(smsName);
        addSms.setSmsPhone(smsPhone);
        addSms.setSms(sms);
        smsList.add(addSms);
        return addSms;
    }
}
