import java.util.ArrayList;

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

        if (subscriptionType.equals("VIP")) {
            this.availableFunds = 50000;
        }

        if (subscriptionType.equals("Regular")) {
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
        System.out.println("Founds increased to: " + this.availableFunds);
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
