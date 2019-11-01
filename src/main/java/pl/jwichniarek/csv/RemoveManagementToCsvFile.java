package pl.jwichniarek.csv;

import pl.jwichniarek.menagement.Management;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RemoveManagementToCsvFile {

    public List<Management> remove(String filePath) {
        List<Management> result = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String headerRow = bufferedReader.readLine();
            String[] headers = headerRow.split(";");

            String record;
            while ((record = bufferedReader.readLine()) != null) {
                String[] tokens = record.split(";");

                Management management = createManagement(headers, tokens);
                result.add(management);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Management createManagement(String[] headers, String[] tokens) {
        Management management = new Management();
        for (int i = 0; i < headers.length; i++) {
            if ("id".equals(headers[i])) {
                management.setId(Long.parseLong(tokens[i]));
            }
            if ("position".equals(headers[i])) {
                management.setPosition(tokens[i]);
            }
            if ("name".equals(headers[i])) {
                management.setName(tokens[i]);
            }
            if ("lastName".equals(headers[i])) {
                management.setLastName(tokens[i]);
            }
            if ("age".equals(headers[i])) {
                management.setAge(Integer.parseInt(tokens[i]));
            }
            if ("salary".equals(headers[i])) {
                management.setSalary(Double.parseDouble(tokens[i]));
            }
        }
        return management;
    }
}
