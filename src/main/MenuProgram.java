package main;

import checker.Checker;
import linklist.LinkList;
import person.Person;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MenuProgram {
    private static void load(LinkList<Person> list) {
        File obj = new File("ContactList.txt");
        try {
            obj.createNewFile();
            Scanner sc = new Scanner(obj);
            if (sc.hasNextLine()) {
                sc.nextLine();
            }
            while (sc.hasNextLine()) {
                Person p = new Person(sc.nextLine(), sc.nextLine(), sc.nextLine());
                p.setEmail(sc.nextLine());
                list.insertAtLast(p);
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("error occurred.");
        }
    }

    private static void store(LinkList<Person> list) {
        File obj = new File("ContactList.txt");
        obj.delete();
        try {
            obj.createNewFile();
            FileWriter myObj = new FileWriter("ContactList.txt");
            LinkList<Person>.Cursor cur = list.connect();
            cur.setAtBeginning();
            while (cur.hasNext()) {
                myObj.write(cur.data().rawData());
                cur.setAtNext();
            }
            if (cur.isData()) {
                myObj.write(cur.data().rawData());
            }
            myObj.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void pattern() {
        for (int i = 0; i < 1; i++) {
            System.out.println("--------*--------*--------*--------");
        }
    }

    private static String mainMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Prashant Agrawal's Contact List App");
        System.out.println("Press 1 to add a new Contact");
        System.out.println("Press 2 to view all contacts");
        System.out.println("Press 3 to search for a contact");
        System.out.println("Press 4 to delete a Contact");
        System.out.println("Press 5 to exit program");
        System.out.print("Press key: ");
        int loop = 5;
        String[] str = new String[]{"1", "2", "3", "4", "5"};
        while (loop-- > 0) {
            String key = sc.nextLine().strip();
            for (int i = 0; i < 5; i++) {
                if (str[i].equals(key)) {
                    return key;
                }
            }
            System.out.println("Invalid Input!");
            System.out.print("Press key again: ");
        }
        return "5";
    }

    private static Person addNewContactMenu() {
        pattern();
        Scanner sc = new Scanner(System.in);
        System.out.println("You have chosen to add new  contact:");
        System.out.print("Please enter the name of the person.Person\nFirst Name: ");
        boolean flag = false;
        String firstName = "";
        while (!flag) {
            firstName = sc.nextLine().strip();
            flag = Checker.checkName(firstName);
            if (!flag) {
                System.out.println("Invalid First Name!\nUse alphabets only\nTry again");
                System.out.print("First Name: ");
            }
        }
        flag = false;
        String lastName = "";
        System.out.print("Last Name: ");
        while (!flag) {
            lastName = sc.nextLine().strip();
            flag = Checker.checkName(lastName);
            if (!flag) {
                System.out.println("Invalid Last Name!\nUse alphabets only\nTry again");
                System.out.print("Last Name: ");
            }
        }
        flag = false;
        System.out.print("Contact Number: ");
        String contactNumber = "";
        while (!flag) {
            contactNumber = sc.nextLine().strip();
            flag = Checker.checkNumber(contactNumber);
            if (!flag) {
                System.out.println("Invalid Contact Number!\nTry again");
                System.out.print("Contact Number: ");
            }
        }
        Person personObject = new Person(firstName, lastName, contactNumber);
        String ack = "y";
        while (ack.equals("y")) {
            System.out.print("Would you like to add another contact number? (y/n): ");
            ack = sc.nextLine().strip();
            if (ack.equals("y")) {
                System.out.print("Contact Number: ");
                contactNumber = sc.nextLine().strip();
                flag = Checker.checkNumber(contactNumber);
                if (!flag) {
                    System.out.println("Invalid Contact Number!\nTry again");
                } else {
                    personObject.addContact(contactNumber);
                }
            } else if (!ack.equals("n")) {
                System.out.println("Invalid Input!\nTry again");
                ack = "y";
            }
        }
        ack = "y";
        int count = 0;
        while (ack.equals("y") && count++ != 1) {
            System.out.print("Would you like to add email address? (y/n): ");
            ack = sc.nextLine().strip();
            if (ack.equals("y")) {

                System.out.print("Email Address: ");
                String email = "";
                flag = false;
                while (!flag) {
                    email = sc.nextLine().strip();
                    flag = Checker.checkEmail(email);
                    if (!flag) {
                        System.out.println("Invalid Email !\nTry again");
                        System.out.print("Email Address: ");
                    }
                }
                personObject.addEmail(email);
            } else if (!ack.equals("n")) {
                System.out.println("Invalid Input!\nTry again");
                ack = "y";
                count = 0;
            }
        }
        pattern();
        System.out.println("---------New Contact is------------");
        System.out.println(personObject.getDetail());
        while (true) {
            System.out.println("Would you like to save the Contact (y/n): ");
            ack = sc.nextLine().strip();
            if (ack.equals("n")) {
                System.out.println("New Contact discarded.");
                return null;
            } else if (ack.equals("y")) {
                return personObject;
            } else {
                System.out.println("Invalid Input! \nTry again");
            }
        }
    }

    private static void viewAllContacts(LinkList<Person> list) {
        System.out.println("------Here are all your Contacts------");
        LinkList<Person>.Cursor cur = list.connect();
        cur.setAtBeginning();
        while (cur.hasNext()) {
            pattern();
            System.out.println(cur.data().getDetail());
            pattern();
            try {
                cur.setAtNext();
            } catch (Exception e) {
                System.out.println(" Index out of Bound.");
            }
        }
        pattern();
        if (cur.isData()) {
            System.out.println(cur.data().getDetail());
        } else {
            System.out.println("No data found.");
        }
        pattern();
    }

    private static void searchForAContactMenu(LinkList<Person> list) {
        pattern();
        System.out.print("You could search for a contact from their first name: ");
        Scanner sc = new Scanner(System.in);
        String firstName = sc.nextLine().strip();
        String s = "\n--------*--------*--------*--------\n";
        StringBuilder s1 = new StringBuilder();
        int count = 0;
        LinkList<Person>.Cursor cur = list.connect();
        cur.setAtBeginning();
        boolean flag = false;
        while (cur.hasNext()) {
            if (cur.data().getFirstName().equals(firstName)) {
                flag = true;
                break;
            }
            try {
                cur.setAtNext();
            } catch (Exception e) {
                System.out.println("Index out of Bound!");
            }
        }
        if (cur.isData() && !flag) {
            if (cur.data().getFirstName().equals(firstName)) {
                flag = true;
            }
        }
        if (flag) {
            while (cur.hasNext()) {
                if (cur.data().getFirstName().equals(firstName)) {
                    count++;
                    s1.append(s).append(cur.data().getDetail()).append(s);
                }
                try {
                    cur.setAtNext();
                } catch (Exception e) {
                    System.out.println("Index out of Bound!");
                }
            }
            if (cur.isData()) {
                if (cur.data().getFirstName().equals(firstName)) {
                    count++;
                    s1.append(s).append(cur.data().getDetail()).append(s);
                }
            }

        }
        if (count != 0) {
            System.out.print(count + " match found!");
            System.out.println(s1.toString());
        } else {
            System.out.println("NO RESULT FOUND!");
            pattern();
        }
    }

    private static void deleteAContactMenu(LinkList<Person> list) {
        pattern();
        System.out.println("Here are all your contacts:");
        LinkList<Person>.Cursor cur = list.connect();
        cur.setAtBeginning();
        int i = 0;
        while (cur.hasNext()) {
            System.out.println(++i + ". " + cur.data().toString());
            try {
                cur.setAtNext();
            } catch (Exception e) {
                System.out.println("Index out of Bound");
            }
        }
        if (cur.isData()) {
            System.out.println(++i + ". " + cur.data().toString());
            System.out.print("Press the number against the contact to delete it \nPress any other integer if you not want to delete" +
                    " any contact\nEnter number: ");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            if (num <= i && num > 0) {
                Person p = list.delete(num - 1);
                System.out.println(p.toString() + "'s contact deleted from list!");
            }
        } else {
            System.out.println("No data found");
            pattern();
        }


    }

    public static void main(String[] args) {
        String status = "";
        LinkList<Person> list = new LinkList<>();
        load(list);
        while (!status.equals("5")) {
            status = mainMenu();
            switch (status) {
                case "1":
                    Person obj = addNewContactMenu();
                    if (obj != null) {
                        boolean flag = list.sortedInsert(obj);
                        if (flag) {
                            System.out.println("New Contact successfully saved .");
                        } else {
                            System.out.println("This contact entry already exist.");
                        }
                    }
                    break;
                case "2":
                    viewAllContacts(list);
                    break;
                case "3":
                    searchForAContactMenu(list);
                    break;
                case "4":
                    deleteAContactMenu(list);
                    break;
            }


        }
        store(list);
        System.out.println("Program Successfully terminated");
    }
}
