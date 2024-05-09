
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ReadCsv {
    public static Map<String,Double> readCsvAndSumCharges(String csvFile,String statusColumnName, String chargeColumnName){
        Map<String,Double> statusSum = new HashMap<>();
        String csvSplitBy = ","; // Assuming comma-separated values
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = br.readLine();
            if(line == null || line.isEmpty()){
                return statusSum;
            }
            // Get chargesIndex and statusIndex (key = status column index, value = charges column index)
            Pair<Integer,Integer> statusAndChargeIndex = getStatusAndChargeIndex(line,csvSplitBy,statusColumnName,chargeColumnName);
            while ((line = br.readLine()) != null) {
                if(line.isEmpty())
                    continue;
                String[] data = line.split(csvSplitBy);
                String status = data[statusAndChargeIndex.getKey()];
                String chargeString = data[statusAndChargeIndex.getValue()].trim();
                if(!chargeString.isEmpty()){
                    statusSum.put(status,
                            statusSum.getOrDefault(status,0D) + Double.parseDouble(chargeString));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statusSum;
    }

    public static Pair<Integer, Integer> getStatusAndChargeIndex(String line, String csvSplitBy, String statusColumnName, String chargeColumnName){
        String[] columns = line.split(csvSplitBy);
        int chargeIndex = 0, statusIndex = 0;
        for(int i=0; i < columns.length; i++){
            if(statusIndex > 0 && chargeIndex > 0)
                break;
            if(columns[i].equalsIgnoreCase(statusColumnName))
                statusIndex = i;
            if(columns[i].equalsIgnoreCase(chargeColumnName))
                chargeIndex = i;
        }
        return new Pair<>(statusIndex,chargeIndex);
    }
    
}
