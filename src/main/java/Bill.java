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
