import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Comparator;



public class Main {
    public static void main(String[] args){
        System.out.println("Welcome to the Java Stream Sales Analyzer");

        String fileName = "sales.csv";
        List<Sale> sales = loadSalesFromFile(fileName);

        if (sales.isEmpty()) {
            System.out.println("No sales were found.");
            return;
        }
        displayAllSales(sales);
        displaySalesOver500(sales);
        countSalesOver1000(sales);
        calculateTotalSales(sales);
        calculateAverageSale(sales);
        displayHighestSale(sales);
        displayLowestSale(sales);
        displaySalesByRegion(sales, "North");
        displayEmployeeNames(sales);
    }

    //list method to get sales from csv
    public static List<Sale> loadSalesFromFile(String filename){
        List<Sale> sales = new ArrayList<>();

        try{
            File file = new File(filename);
            Scanner fileReader = new Scanner(file);

            //skip header
            if(fileReader.hasNextLine())
                fileReader.nextLine();

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");

                //build each sale
                if (parts.length == 4) {
                    int saleId = Integer.parseInt(parts[0]);
                    String employeeName = parts[1];
                    String region = parts[2];
                    double amount = Double.parseDouble(parts[3]);

                    Sale sale = new Sale(saleId, employeeName, region, amount);
                    sales.add(sale);
                }

            }
            fileReader.close();
        } catch (FileNotFoundException e){
            System.out.println("File error: "+e.getMessage());
        } catch (NumberFormatException e){
            System.out.println("A number in the CSV file could not be parsed.");
        }

        return sales;
    }

    public static void displayAllSales(List<Sale> sales){
        System.out.println("All sales:");
        sales.stream().forEach(System.out::println);

        System.out.println();
    }
    public static void displaySalesOver500(List<Sale> sales){
        System.out.println("Sales over $500");

        sales.stream()
                .filter(sale -> sale.getAmount() > 500)
                .forEach(System.out::println);

        System.out.println();
    }
    public static void countSalesOver1000(List<Sale> sales){
        System.out.println("Count of large sales.");

        long count = sales.stream()
                .filter(sale -> sale.getAmount() > 1000)
                .count();

        System.out.println("Number of sales over $1000 is "+count);
        System.out.println();
    }
    public static void calculateTotalSales(List<Sale> sales){
        System.out.println("Count of sales.");

        double total = sales.stream()
                .mapToDouble(Sale::getAmount)
                .sum();

        System.out.println("Number of sales is "+String.format("%.2f", total));
        System.out.println();
    }
    public static void calculateAverageSale(List<Sale> sales){
        double average = sales.stream()
                .mapToDouble(Sale::getAmount)
                .average()
                .orElse(0);
        System.out.println("Average sale is $"+String.format("%.2f", average));
        System.out.println();

    }
    public static void displayHighestSale(List<Sale> sales){
        Optional<Sale> highestSale = sales.stream()
                .max(Comparator.comparing(Sale::getAmount));

        System.out.println("Highest sale is "+highestSale.get());
        System.out.println();
    }
    public static void displayLowestSale(List<Sale> sales){
        Optional<Sale> lowestSale = sales.stream()
                .min(Comparator.comparing(Sale::getAmount));

        System.out.println("Lowest sale is "+lowestSale.get());
        System.out.println();
    }
    public static void displaySalesByRegion(List<Sale> sales, String selectedRegion){
        System.out.println("Sales from "+selectedRegion+":");
        sales.stream()
                .filter(sale->sale.getRegion().equalsIgnoreCase(selectedRegion))
                .forEach(System.out::println);

        System.out.println();
    }
    public static void displayEmployeeNames(List<Sale> sales){
        System.out.println("Employee Names:");
        sales.stream()
                .map(Sale::getEmployeeName)
                        .forEach(System.out::println);

        System.out.println();
    }
}
