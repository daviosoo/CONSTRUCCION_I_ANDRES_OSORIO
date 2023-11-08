import java.util.ArrayList;

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

    public void increaseFounds(int id, double founds) {
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

    public void addAuthorizedPerson(int id, String name) {
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

    public void registerBill(int id, String billConcept, double billValue) {
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

    public void removeBill(int id, String billConcept) {
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
