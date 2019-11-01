package pl.jwichniarek.menagement;

import java.util.List;
import java.util.Scanner;

public class ManagementViews {
    private Scanner scanner;

    public ManagementViews(Scanner scanner) {
        this.scanner = scanner;
    }

    public int startMenu() {
        System.out.println("Witaj w aplikacji zajmujacej sie kadrami ");
        System.out.println("Wcisnij '1' aby sprawdzic liste kadr sprawdzic liste kadr ");
        System.out.println("Wcisnij '2' aby dodawac lub usuwac ");
        System.out.println("Wcisnij '3' aby wyszukac ");
        System.out.println("Wcisnij '0' aby zakonczyc program ");
        return getDecisionAfterEnter();
    }

    public int managementList(List<Management> managementList) {
        System.out.println("Lista kadr ");
        managementList.forEach(management -> System.out.println(management));
        System.out.println("Wcisnij '0 aby cofnac '");
        return getDecision();
    }

    public int managementMenu(List<Management> managementList) {
        System.out.println("1: Dodaj nowa osobe ");
        System.out.println("2: Usun plik osob ");
        return getDecision();
    }

    public int managementSearch(List<Management> managementListSearch) {
        System.out.println("1: Wyszukaj po stanowisku");
        System.out.println("2: Wyszukaj po imieniu");
        System.out.println("3: Wyszukaj po nazwisku");
        return getDecision();
    }

    public String getPosition() {
        String position = scanner.next();
        return position.trim();
    }

    public String getName() {
        String name = scanner.next();
        return name.trim();
    }

    public String getLastName() {
        String lastName = scanner.next();
        return lastName.trim();
    }

    private int getDecision() {
        return scanner.nextInt();
    }

    private int getDecisionAfterEnter() {
        return readIntAndClearLine();
    }

    private int readIntAndClearLine() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}

