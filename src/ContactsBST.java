import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ContactsBST {
    private Node root; // Root of the BST

    // Inner class to represent a node in the BST
    private class Node {
        Contact contact;
        Node left, right;

        Node(Contact contact) {
            this.contact = contact;
        }
    }

    // Insert a new contact into the BST
    public void insert(Contact contact) {
        root = insertRec(root, contact);
    }

    private Node insertRec(Node node, Contact contact) {
        if (node == null) {
            return new Node(contact);
        }
        if (contact.compareTo(node.contact) < 0) {
            node.left = insertRec(node.left, contact);
        } else if (contact.compareTo(node.contact) > 0) {
            node.right = insertRec(node.right, contact);
        }
        return node;
    }

    // Search for a contact by name
    public Contact search(String name) {
        return searchRec(root, name);
    }

    private Contact searchRec(Node node, String name) {
        if (node == null) return null;
        if (name.equalsIgnoreCase(node.contact.getName())) return node.contact;
        if (name.compareToIgnoreCase(node.contact.getName()) < 0) return searchRec(node.left, name);
        return searchRec(node.right, name);
    }

    // Remove a contact by name
    public void remove(String name) {
        root = removeRec(root, name);
    }

    private Node removeRec(Node node, String name) {
        if (node == null) return null;

        if (name.compareToIgnoreCase(node.contact.getName()) < 0) {
            node.left = removeRec(node.left, name);
        } else if (name.compareToIgnoreCase(node.contact.getName()) > 0) {
            node.right = removeRec(node.right, name);
        } else {
            // Node to be deleted found
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node minNode = findMin(node.right);
            node.contact = minNode.contact;
            node.right = removeRec(node.right, minNode.contact.getName());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // Print contacts in alphabetical order
    public void printInOrder() {
        printInOrderRec(root);
    }

    private void printInOrderRec(Node node) {
        if (node != null) {
            printInOrderRec(node.left);
            System.out.println(node.contact);
            printInOrderRec(node.right);
        }
    }

    // Helper method to read contacts from file and insert into ContactsBST
    public static boolean readContactsFromFile(String fileName, ContactsBST contactsTree) {
        boolean fileNotEmpty = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                if (data.length >= 3) {
                    String name = data[0] + " " + data[1];
                    String phoneNumber = data[2];
                    contactsTree.insert(new Contact(name, phoneNumber));
                    fileNotEmpty = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return fileNotEmpty;
    }
}


