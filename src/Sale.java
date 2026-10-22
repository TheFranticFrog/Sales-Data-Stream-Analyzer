public class Sale {
    //class vars
    private int saleId;
    private String employeeName;
    private String region;
    private double amount;

    //constructor
    public Sale(int saleId, String employeeName, String region, double amount){
        this.saleId = saleId;
        this.employeeName = employeeName;
        this.region = region;
        this.amount = amount;
    }
    //accessor
    public int getSaleId(){
        return saleId;
    }

    public String getEmployeeName(){
        return employeeName;
    }

    public String getRegion(){
        return region;
    }

    public double getAmount(){
        return amount;
    }



    //override toString
    @Override
    public String toString(){
        return saleId + " | " +
                employeeName + " | " +
                region + " | $" +
                String.format("%.2f", amount);
    }
}
