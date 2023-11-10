import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initializing scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Creating an instance of the Club
        Club club = new Club();

        // Menu for selecting options
        int choice = 0;
        while (choice != 7) {
            System.out.println("Welcome to the Social Club Management Application.");
            System.out.println("Select an option:");
            System.out.println("1. Register a member.");
            System.out.println("2. Remove a member.");
            System.out.println("3. Register a purchase for a member.");
            System.out.println("4. Register a authorized member.");
            System.out.println("5. Increase funds for a member.");
            System.out.println("6. Pay a bill.");
            System.out.println("7. Exit");

            // Reading the user's choice
            choice = scanner.nextInt();

            // Performing actions based on user's choice
            switch (choice) {
                case 1:
                    System.out.println("Enter member ID:");
                    int id = scanner.nextInt();
                    System.out.println("Enter member name:");
                    String name = scanner.next();
                    System.out.println("Enter subscription type:");
                    String subscriptionType = scanner.next();

                    if(!subscriptionType.equals("VIP") && !subscriptionType.equals("Regular")) {
                        System.out.println("Invalid subscription type. (VIP) - (Regular)");
                        break;
                    }

                    Member newMember = new Member(id, name, subscriptionType);
                    club.registerMember(newMember);
                    break;
                case 2:
                    System.out.println("Enter member ID to remove:");
                    int removeId = scanner.nextInt();
                    boolean removedMember = club.removeMember(removeId);

                    if(removedMember) {
                        System.out.println("Member removed successfully.");
                    }

                    break;
                case 3:
                    System.out.println("Enter member ID:");
                    int billId = scanner.nextInt();
                    System.out.println("Enter bill concept:");
                    String billConcept = scanner.next();
                    System.out.println("Enter bill value:");
                    double billValue = scanner.nextDouble();
                    club.registerBill(billId, billConcept, billValue);
                    break;
                case 4:
                    System.out.println("Enter member ID:");
                    int addId = scanner.nextInt();
                    System.out.println("Enter authorized person's name to add:");
                    String authorizedPersonName = scanner.next();
                    club.addAuthorizedPerson(addId, authorizedPersonName);
                    break;
                case 5:
                    System.out.println("Enter member ID:");
                    int increaseFundsId = scanner.nextInt();
                    System.out.println("Enter funds to add:");
                    double fundsToAdd = scanner.nextDouble();
                    club.increaseFounds(increaseFundsId, fundsToAdd);
                    break;
                case 6:
                    System.out.println("Enter member ID to remove:");
                    int removeBillId = scanner.nextInt();
                    System.out.println("Enter bill concept:");
                    String removeBillConcept = scanner.next();
                    club.removeBill(removeBillId, removeBillConcept);
                    break;
                case 7:
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }
}

