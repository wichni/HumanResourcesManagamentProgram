package pl.jwichniarek.csv;

import pl.sda.converter.SDAFileReader;
import pl.sda.converter.exceptions.FileConverterException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReadManagementCsvFile implements SDAFileReader {
    public ReadManagementCsvFile() {
    }

    public List<Map<String, Object>> read(String filePath) {
        ArrayList result = new ArrayList();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

            try {
                String firstLine = bufferedReader.readLine();
                String[] headers = firstLine.split(";");

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] tokens = line.split(";");
                    Map<String, Object> record = new LinkedHashMap();

                    for (int i = 0; i < headers.length; ++i) {
                        record.put(headers[i], tokens[i]);
                    }

                    result.add(record);
                }
            } catch (Throwable var11) {
                try {
                    bufferedReader.close();
                } catch (Throwable var10) {
                    var11.addSuppressed(var10);
                }

                throw var11;
            }

            bufferedReader.close();
            return result;
        } catch (FileNotFoundException var12) {
            throw new FileConverterException("Nie odnalezion pliku");
        } catch (IOException var13) {
            throw new FileConverterException("Wystąpił błąd w trakcie przetwarzania pliku", var13);
        }
    }
}
