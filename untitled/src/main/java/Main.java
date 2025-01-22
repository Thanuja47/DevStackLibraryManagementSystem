import java.util.*;

public class Main {
    private static List<String> libraryItems = new ArrayList<>();
    private static List<String> libraryUsers = new ArrayList<>();
    private static Map<String, String> borrowedItems = new HashMap<>();

    public static void main(String[] args) {
        initializeLibraryDetails();

        Scanner scanner = new Scanner(System.in);

        // Show the list of items, users, and borrowed items
        displayLibraryDetails();

        while (true) {
            System.out.println("\nEnter the main option:");
            System.out.println("1. Need to create new Item");
            System.out.println("2. Need to create new User");
            System.out.println("3. User need to borrow item");
            System.out.println("4. User need to return item");
            System.out.println("5. Exit");

            System.out.print("Choose an option (1-5): ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    createNewItem(scanner);
                    break;
                case 2:
                    createNewUser(scanner);
                    break;
                case 3:
                    borrowItem(scanner);  // Option 3 works as you need
                    break;
                case 4:
                    returnItem(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option! Please choose between 1 and 5.");
            }
        }
    }

    private static void initializeLibraryDetails() {
        libraryItems.add("1. Book1 Author1 Book1");
        libraryItems.add("2. Book2 Author2 Book2");
        libraryItems.add("3. Book3 Authors Book3");
        libraryItems.add("4. Magazine1 AuthorM1 Magazine1");
        libraryItems.add("5. Magazine2 AuthorM2 Magazine2");

        libraryUsers.add("Sanath");
        libraryUsers.add("Thameera");
        libraryUsers.add("Ishari");
        libraryUsers.add("Jihan");
        libraryUsers.add("Kaushalya");

        borrowedItems.put("Book2", "Thameera");
    }

    private static void displayLibraryDetails() {
        System.out.println("Here... Find the list of all library items:");
        for (String item : libraryItems) {
            System.out.println(item);
        }

        System.out.println("\nHere... Find the users of the Library:");
        for (String user : libraryUsers) {
            System.out.println(user);
        }

        System.out.println("\nHere... Find the borrowed items of the Library:");
        if (borrowedItems.isEmpty()) {
            System.out.println("No items are currently borrowed.");
        } else {
            borrowedItems.forEach((item, user) -> System.out.println(item + ": " + user));
        }
    }

    // Option 1: Create a new item
    private static void createNewItem(Scanner scanner) {
        System.out.print("Enter the name of the new item: ");
        String newItem = scanner.nextLine();
        libraryItems.add(newItem);
        System.out.println("New item added: " + newItem);
    }

    // Option 2: Create a new user
    private static void createNewUser(Scanner scanner) {
        System.out.print("Enter the name of the new user: ");
        String newUser = scanner.nextLine();
        libraryUsers.add(newUser);
        System.out.println("New user added: " + newUser);
    }

    // Option 4: Return an item
    private static void returnItem(Scanner scanner) {
        System.out.print("Enter the serial number of the item to return: ");
        String serialNumber = scanner.nextLine();

        // Check if the item was borrowed
        if (borrowedItems.containsKey(serialNumber)) {
            String user = borrowedItems.get(serialNumber);
            borrowedItems.remove(serialNumber);
            libraryItems.add(serialNumber);
            System.out.println("Item \"" + serialNumber + "\" returned by \"" + user + "\" successfully.");
        } else {
            System.out.println("Item not borrowed or doesn't exist.");
        }
    }

    // Option 3: Borrow an item (already working perfectly as you confirmed)
    private static void borrowItem(Scanner scanner) {
        // Ask the user to input their number first
        System.out.println("Please enter the number of the user borrowing the item:");
        for (int i = 0; i < libraryUsers.size(); i++) {
            System.out.println((i + 1) + ". " + libraryUsers.get(i));
        }
        int userIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (userIndex < 1 || userIndex > libraryUsers.size()) {
            System.out.println("Invalid user number. Please try again.");
            return;
        }

        String selectedUser = libraryUsers.get(userIndex - 1);

        // Ask the serial number of the item
        System.out.print("Enter the serial number of the item: ");
        String serialNumber = scanner.nextLine();

        // Check if item exists in library items
        boolean itemExists = libraryItems.stream().anyMatch(item -> item.contains(serialNumber));
        if (itemExists) {
            // Borrow the item
            borrowedItems.put(serialNumber, selectedUser);
            libraryItems.removeIf(item -> item.contains(serialNumber));
            System.out.println("Item \"" + serialNumber + "\" borrowed by \"" + selectedUser + "\" successfully.");
        } else {
            System.out.println("Item not found. Please try again.");
        }
    }
}
