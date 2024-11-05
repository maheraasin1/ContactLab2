import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ContactApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactsBST contactsTree = new ContactsBST();  

        
        String fileName;
        boolean fileLoaded = false;
        do {
            System.out.print("Enter file name: ");
            fileName = scanner.nextLine();
            fileLoaded = readContactsFromFile(fileName, contactsTree);
            if (!fileLoaded) {
                System.out.println("File not found or empty. Please try again.");
            }
        } while (!fileLoaded);

        // Menu loop
        while (true) {
            System.out.println("Contact List");
            System.out.println("Select one of the following operations:");
            System.out.println("1. Add a contact");
            System.out.println("2. Remove a contact");
            System.out.println("3. Display contacts in alphabetic order");
            System.out.println("4. Search a contact");
            System.out.println("5. Exit");

            System.out.print("Enter your selection here: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Enter new contact name: ");
                    String newName = scanner.nextLine().trim();
                    System.out.print("Enter new contact number: ");
                    String newPhone = scanner.nextLine().trim();
                    contactsTree.insert(new Contact(newName, newPhone));
                    System.out.println("Contact added.");
                    break;
                case 2:
                    System.out.print("Enter contact name to remove: ");
                    String removeName = scanner.nextLine().trim();
                    contactsTree.remove(removeName);
                    System.out.println("Contact removed (if it existed).");
                    break;
                case 3:
                    System.out.println("Contact List:");
                    contactsTree.printInOrder();
                    break;
                case 4:
                    System.out.print("Enter contact name to search: ");
                    String searchName = scanner.nextLine().trim();
                    Contact foundContact = contactsTree.search(searchName);
                    if (foundContact != null) {
                        System.out.println("Found: " + foundContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    
    private static boolean readContactsFromFile(String fileName, ContactsBST contactsTree) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean fileNotEmpty = false;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                if (data.length >= 3) {
                    String name = data[0] + " " + data[1];
                    String phoneNumber = data[2];
                    contactsTree.insert(new Contact(name, phoneNumber));
                    fileNotEmpty = true;
                }
            }
            return fileNotEmpty;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }
    }
}
