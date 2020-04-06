import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class ContactsManager {
    public static void main(String[] args) {
        ContactsFile cf = new ContactsFile("data", "contacts.txt");
//        HashMap<String, String> contacts = new HashMap<>();
        Scanner input = new Scanner(System.in);
        List<Person> contacts = new ArrayList<>();


        try {
            cf.createDirectory();
            cf.createFile();
        } catch (IOException io) {
            io.printStackTrace();
        }

        try {
            if (cf.readFile() != null) {
                for (String contact : cf.readFile()) {
                    String[] c = contact.split(",");
                    contacts.add(new Person(c[0], c[1]));
                }
            }
        } catch (IOException io) {
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

            switch (option) {
                case 1:
                    try {
                        System.out.println("Name                 | Phone");
                        System.out.println("---------------------|----------------");
                        for (String contact : cf.readFile()) {
                            String[] c = contact.split(",");
                            System.out.printf("%-20s | %s%n", c[0], c[1]);
                        }
                    } catch (IOException io) {
                        io.printStackTrace();
                    }

                    break;
                case 2:

                    System.out.print("Enter the first name: ");
                    String fName = input.next();

                    System.out.print("Enter the last first name: ");
                    String lName = input.next();

                    String phone = "";
                    boolean validNumber = false;
                    do {
                        System.out.print("Enter a 7 or 10 digit phone number: ");
                        phone = input.next();
                        if (phone.length() == 7 || phone.length() == 10 ) {
                            validNumber = true;
                        }
                        else{
                            System.out.println("Incorrect number of digits. Please enter valid number.");
                        }
                    } while (!validNumber);


                    String name = fName + " " + lName;
                    String number = phone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");


                    contacts.add(new Person(name, number));

                    try {
                        cf.clearDoc();
                        cf.writeToFile(contacts);
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                    break;


                case 3:
                    System.out.println("Search by name: ");
                    String searchName = input.next();
//                    for (int i = 0; i < contacts.size(); i++) {
//                        if (searchName.contains(contacts.get(i).getName()))
//                            System.out.println(contacts.get(i).getName() + " " + contacts.get(i).getNumber());

                    try {
                        Scanner scanner = new Scanner(Paths.get("data", "contacts.txt"));
                        System.out.println("Name                 | Phone");
                        System.out.println("---------------------|----------------");
                        while (scanner.hasNext()) {
                            String line = scanner.nextLine().toString();
                            if (line.contains(searchName.toLowerCase())) {
                                String[] c = line.split(",");
                                System.out.printf("%-20s | %s%n", c[0], c[1]);

                            }
                        }
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
//                    }


                    break;
                case 4:
                    try {
                        System.out.println("Id    | Name                 | Phone");
                        System.out.println("------|----------------------|----------------");
                        int counter = 1;
                        for (String contact : cf.readFile()) {
                            String[] c = contact.split(",");
                            System.out.printf("%-5d | %-20s | %s%n", counter, c[0], c[1]);
                            counter++;
                        }
                    } catch (IOException io) {
                        io.printStackTrace();
                    }

                    System.out.println("Enter the number of the contact you would like to delete?");
                    int userContact = input.nextInt();
                    contacts.remove(contacts.get(userContact - 1));

                    try {
                        cf.clearDoc();
                        cf.writeToFile(contacts);
                    } catch (IOException io) {
                        io.printStackTrace();
                    }


                    break;
                case 5:
                    break;
                default:
                    break;
            }

            System.out.println();

            System.out.print("Would you like to enter another option? [Y/N] ");
            again = input.next();
        } while (again.equalsIgnoreCase("y"));
    }
}