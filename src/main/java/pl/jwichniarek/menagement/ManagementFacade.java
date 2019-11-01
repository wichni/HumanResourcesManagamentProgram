package pl.jwichniarek.menagement;

import java.util.List;

public class ManagementFacade {
    private ManagementRepository managementRepository;

    public ManagementFacade(ManagementRepository managementRepository) {
        this.managementRepository = managementRepository;
    }

    public List<Management> findAll() {
        return managementRepository.findAll();
    }

    public Management save(Management management) {
        return managementRepository.save(management);
    }
    public Management delete(Management management){
        return managementRepository.delete(management);
    }

    public List<Management> findByPosition(String position) {
        return managementRepository.findByPosition(position);
    }

    public List<Management> findByName(String name) {
        return managementRepository.findByName(name);
    }

    public List<Management> findByLastName(String lastName) {
        return managementRepository.findByLastName(lastName);
    }
}
