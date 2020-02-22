package person;

import linklist.LinkList;

public class Person {
    StringBuilder contact;
    private String first_name;
    private String last_name;
    private LinkList<String> contactNumberList;
    private String email;

    public Person(String first_name, String last_name, String number) {
        this.first_name = first_name;
        this.last_name = last_name;
        contactNumberList = new LinkList<>();
        contactNumberList.insertAtLast(number);
    }

    public void addContact(String number) {
        contactNumberList.insertAtLast(number);
    }

    public void addEmail(String email) {
        this.email = email;
    }

    private String ContactNumberListToString() {
        LinkList<String>.Cursor cur = contactNumberList.connect();
        cur.setAtBeginning();
        StringBuilder contacts;
        contact = new StringBuilder();
        if (cur.hasNext()) {
            contacts = new StringBuilder("Contact Number(s): ");
        } else {
            contacts = new StringBuilder("Contact Number: ");
        }
        while (cur.isData() && cur.hasNext()) {
            contacts.append(cur.data()).append(", ");
            contact.append(cur.data()).append(", ");
            try {
                cur.setAtNext();
            } catch (Exception e) {
                System.out.println("next person not found");
            }
        }
        contacts.append(cur.data());
        contact.append(cur.data());
        return contacts.toString();
    }

    @Override
    public String toString() {
        return first_name + " " + last_name;
    }

    public String getDetail() {
        return "First Name: " + first_name + "\nLast Name: " + last_name + "\n" + this.ContactNumberListToString() + "\nEmail address: " + email;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String rawData() {
        return "\n" + first_name + "\n" + last_name + "\n" + contact.toString() + "\n" + email;
    }
}

