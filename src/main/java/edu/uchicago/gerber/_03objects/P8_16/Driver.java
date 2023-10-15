package edu.uchicago.gerber._03objects.P8_16;

public class Driver {
    public static void main(String[] args) {
        Mailbox mail = new Mailbox();

        Message m1 = new Message("Professor", "Students");
        m1.append("Hi all,");
        m1.append("We will have a quiz tomorrow");
        mail.addMessage(m1);

        Message m2 = new Message("Michael", "Allison");
        m2.append("Hi Allison,");
        m2.append("Congratulations! We are glad to offer you a position at Popple! ");
        mail.addMessage(m2);

        System.out.println("message 1:");
        System.out.println(mail.getMessage(0));

        System.out.println("message 2:");
        System.out.println(mail.getMessage(1));

        mail.removeMessage(0);

        System.out.println("Removing message 1:");
        System.out.println(mail.getMessage(0));
    }

}
