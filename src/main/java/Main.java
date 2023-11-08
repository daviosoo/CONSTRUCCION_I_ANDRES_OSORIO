import java.util.ArrayList;
import java.util.Scanner;

// Class representing the Club
class Club {
    private ArrayList<Member> members; // List of all the members in the club

    // Constructor
    public Club() {
        members = new ArrayList<>();
    }

    // Method to affiliate a member to the club
    public void registerMember(Member member) {
        // Check if a member with the same ID already exists
        for (Member m : members) {
            if (m.getId() == member.getId()) {
                System.out.println("A member with the same ID already exists.");
                return;
            }
        }

        if (members.size() < 35) { // Maximum 35 members allowed
            members.add(member);
            System.out.println("Member registered.");
        } else {
            System.out.println("Club has reached maximum capacity.");
        }
    }

    // Method to remove a member from the club
    public boolean removeMember(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                if (member.getSubscriptionType().equals("VIP")) {
                    System.out.println("VIP members cannot be removed.");
                    return false;
                } else if (!member.getUnpaidBills().isEmpty()) {
                    System.out.println("Member has pending invoices, cannot be removed.");
                    return false;
                } else if (member.getAuthorizedPersonsList().size() > 1) {
                    System.out.println("Member has more than one authorized person, cannot be removed.");
                    return false;
                } else {
                    members.remove(member);
                    return true;
                }
            }
        }
        System.out.println("No member found with the given ID.");
        return false;
    }

    public void increaseFounds(int id, double founds){
        Member member = null;

        for (Member m : members) {
            if (m.getId() == id) {
                member = m;
            }
        }

        if (member == null) {
            System.out.println("No member found with ID");
            return;
        }

        member.increaseFunds(founds);
    }

    public void addAuthorizedPerson(int id, String name){
        Member member = null;

        for (Member m : members) {
            if (m.getId() == id) {
                member = m;
            }
        }

        if (member == null) {
            System.out.println("No member found with ID");
            return;
        }

        member.addAuthorizedPerson(name);
    }

    public void registerBill(int id, String billConcept, double billValue){
        Member member = null;

        for (Member m : members) {
            if (m.getId() == id) {
                member = m;
            }
        }

        if (member == null) {
            System.out.println("No member found with ID");
            return;
        }

        member.registerBill(billConcept, billValue);
    }

    public void removeBill(int id, String billConcept){
        Member member = null;

        for (Member m : members) {
            if (m.getId() == id) {
                member = m;
            }
        }

        if (member == null) {
            System.out.println("No member found with ID");
            return;
        }

        member.removeBill(billConcept);
    }
}

// Class representing the Member
class Member {
    private int id;
    private String name;
    private double availableFunds;
    private String subscriptionType;
    private ArrayList<Bill> unpaidBills;
    private ArrayList<String> authorizedPersonsList;

    // Constructor
    public Member(int id, String name, String subscriptionType) {
        this.id = id;
        this.name = name;
        this.subscriptionType = subscriptionType;
        this.unpaidBills = new ArrayList<>();
        this.authorizedPersonsList = new ArrayList<>();

        if(subscriptionType.equals("VIP")) {
            this.availableFunds = 50000;
        }

        if(subscriptionType.equals("Regular")) {
            this.availableFunds = 100000;
        }
    }

    // Getters and setters for the private variables

    public int getId() {
        return id;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public ArrayList<Bill> getUnpaidBills() {
        return unpaidBills;
    }

    public ArrayList<String> getAuthorizedPersonsList() {
        return authorizedPersonsList;
    }

    public void increaseFunds(double value) {

            if (this.subscriptionType.equals("VIP") && value > 5000000) {
                System.out.println("Exceeded maximum allowed increase funds.");
                return;
            }

            if (this.subscriptionType.equals("Regular") && value > 1000000) {
                System.out.println("Exceeded maximum allowed increase funds.");
                return;
            }

            this.availableFunds += value;
            System.out.println("Founds increased to: " + this.availableFunds );
    }

    public void addAuthorizedPerson(String name) {
        if (this.authorizedPersonsList.size() < 10) { // Check if the limit of 10 authorized persons has been reached
            for (String person : authorizedPersonsList) {
                if (person.equals(name)) { // Check if the person already exists in the list
                    System.out.println("Person already exists in the authorized persons list.");
                    return;
                }
            }
            // If the person does not exist and the limit has not been reached, add the person to the list
            authorizedPersonsList.add(name);
            System.out.println("Authorized person added successfully.");
        } else {
            System.out.println("Maximum limit of authorized persons reached.");
        }
    }

    public void registerBill(String billConcept, double billValue) {
        for (Bill bill : unpaidBills) {
            if (bill.getBillConcept().equals(billConcept)) {
                System.out.println("It is already a bill to this concept.");
                return;
            }
        }

        Bill newBill = new Bill(billConcept, billValue, this.name);
        unpaidBills.add(newBill);
        System.out.println("Bill registered successfully.");
    }

    // Method to remove a bill
    public void removeBill(String billConcept) {
        for (Bill bill : unpaidBills) {
            if (bill.getBillConcept().equals(billConcept)) {
                unpaidBills.remove(bill);
                System.out.println("Bill removed successfully.");
                return;
            }
        }
        System.out.println("No such bill found in the unpaid bills list.");
    }

}

// Class representing a Bill
class Bill {
    private String billConcept;
    private double billValue;
    private String memberName;

    // Constructor
    public Bill(String billConcept, double billValue, String memberName) {
        this.billConcept = billConcept;
        this.billValue = billValue;
        this.memberName = memberName;
    }

    // Getters for the private variables
    public String getBillConcept() {
        return billConcept;
    }

    public double getBillValue() {
        return billValue;
    }

    public String getMemberName() {
        return memberName;
    }
}

public class Main {
    public static void main(String[] args) {
        // Initializing scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Creating an instance of the Club
        Club club = new Club();

        // Menu for selecting options
        int choice = 0;
        while (choice != 6) {
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
                    club.removeMember(removeId);
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

