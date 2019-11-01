package pl.jwichniarek.menagement;

import java.util.List;

public interface ManagementRepository {

    List<Management> findAll();

    Management save(Management management);

    Management delete(Management management);

    List<Management> findByPosition(String position);

    List<Management> findByName(String name);

    List<Management> findByLastName(String lastName);
}
