public class Main {
    public static void main(String[] args) {
        String csvFile = "D:\\SPRINGBOOT\\ReadCsvAssignment\\src\\sample.csv"; // Change this to your CSV file path
        System.out.println(ReadCsv.readCsvAndSumCharges(csvFile,"Status","Charges"));
    }
}