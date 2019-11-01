package pl.jwichniarek.csv;

import lombok.NoArgsConstructor;
import pl.jwichniarek.menagement.Management;
import pl.sda.converter.SDAFileWriter;
import pl.sda.converter.factories.FileWriterFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
public class SaveManagementToCsvFile {
    private String filePath = "C:\\Users\\jakub\\IdeaProjects\\HumanResourcesMenagementProgram" +
            "\\src\\main\\resources\\Management.csv";

    public void write(List<Management> list) {
        FileWriterFactory fileWriterFactory = new FileWriterFactory();
        SDAFileWriter writer = fileWriterFactory.produce(filePath);

        List<Map<String, Object>> dataToSave =
                list.stream()
                .map(management -> objectToMap(management))
                .collect(Collectors.toList());
        writer.write(dataToSave, filePath);

    }

    private Map<String, Object> objectToMap(Management management) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", management.getId());
        result.put("position", management.getPosition());
        result.put("name", management.getName());
        result.put("lastName", management.getLastName());
        result.put("age", management.getAge());
        result.put("salary", management.getSalary());
        return result;
    }
}
