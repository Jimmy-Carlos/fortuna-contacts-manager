import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ContactsManager {
    public static void main(String[] args) {
        ContactsFile cf = new ContactsFile("data", "contacts.txt");
        HashMap<String, String> contacts = new HashMap<>();
        Scanner input = new Scanner(System.in);

        try {
            cf.createDirectory();
            cf.createFile();
        } catch(IOException io) {
            io.printStackTrace();
        }

        String again = "";

        do {
            System.out.println("1. View contacts.");
            System.out.println("2. Add a new contact.");
            System.out.println("3. Search a contact by name.");
            System.out.println("4. Delete an existing contact.");
            System.out.println("5. Exit.");
            System.out.print("Enter an option (1, 2, 3, 4 or 5): ");

            int option = input.nextInt();

            switch(option) {
                case 1:
                    try {
                        System.out.println("Name       | Phone");
                        System.out.println("-----------|-----------");
                        for(String contact : cf.readFile()) {
                            String[] c = contact.split(",");

                            System.out.printf("%-10s | %s%n", c[0], c[1]);
                        }
                    } catch(IOException io) {
                        io.printStackTrace();
                    }

                    break;
                case 2:
                    System.out.print("Enter the name: ");
                    String name = input.next();

                    System.out.print("Enter the phone number: ");
                    String phone = input.next();

                    contacts.put(name, phone);

                    System.out.println(contacts);
                    try {
                        cf.writeToFile(name, phone);
                    } catch(IOException io) {
                        io.printStackTrace();
                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }

            System.out.println();

            System.out.print("Would you like to enter another option? [Y/N] ");
            again = input.next();
        } while(again.equalsIgnoreCase("y"));
    }
}