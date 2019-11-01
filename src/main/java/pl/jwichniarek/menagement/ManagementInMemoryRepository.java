package pl.jwichniarek.menagement;

import pl.jwichniarek.csv.ReadManagementCsvFile;
import pl.jwichniarek.csv.RemoveManagementToCsvFile;
import pl.jwichniarek.csv.SaveManagementToCsvFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManagementInMemoryRepository implements ManagementRepository {
    private long nextId;
    private List<Management> managementList = new ArrayList<>();

    public ManagementInMemoryRepository() {
        init();
    }

    @Override
    public List<Management> findAll() {
        return new ArrayList<>(managementList);
    }

    @Override
    public Management save(Management management) {
        nextId++;
        management.setId(nextId);
        managementList.add(management);
        SaveManagementToCsvFile toCsvFile = new SaveManagementToCsvFile();
        toCsvFile.write(managementList);
        return management;
    }

    @Override
    public Management delete(Management management) {
        nextId--;
        management.setId(nextId);
        managementList.remove(management);
        RemoveManagementToCsvFile removeToCsvFile = new RemoveManagementToCsvFile();
        removeToCsvFile.remove("C:\\Users\\jakub\\IdeaProjects\\HumanResourcesMenagementProgram" +
                "\\src\\main\\resources\\Management.csv");
        return management;
    }

    @Override
    public List<Management> findByPosition(String position) {
        return managementList.stream()
                .filter(management -> position.equals(management.getPosition()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Management> findByName(String name) {
        return managementList.stream()
                .filter(management -> name.equals(management.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Management> findByLastName(String lastName) {
        return managementList.stream()
                .filter(management -> lastName.equals(management.getLastName()))
                .collect(Collectors.toList());
    }

    private void init() {
        managementList.add(new Management(1L, "TeamManager", "Piotr", "Miśko", 33, 10000));
        managementList.add(new Management(2L, "Programmer", "Jakub", "Wichniarek", 30, 10000));
        managementList.add(new Management(3L, "JuniorProgrammer", "Kamil", "Kowalski", 27, 60000));
        nextId = Long.valueOf(managementList.size());
    }
}
