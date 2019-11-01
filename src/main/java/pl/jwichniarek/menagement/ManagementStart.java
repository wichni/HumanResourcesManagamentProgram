package pl.jwichniarek.menagement;

import org.apache.http.impl.conn.InMemoryDnsResolver;
import pl.jwichniarek.csv.ReadManagementCsvFile;
import pl.jwichniarek.csv.RemoveManagementToCsvFile;
import pl.jwichniarek.csv.SaveManagementToCsvFile;

import java.util.List;
import java.util.Scanner;

public class ManagementStart {
    private ManagementViews views;
    private ManagementFacade managementFacade;
    private ManagementRepository managementRepository;
    private SaveManagementToCsvFile saveToCsvFile;
    private RemoveManagementToCsvFile removeManagementToCsvFile;

    public ManagementStart() {
        this.views = new ManagementViews(new Scanner(System.in));
        this.managementRepository = new ManagementInMemoryRepository();
        this.managementFacade = new ManagementFacade(new ManagementInMemoryRepository());
        this.saveToCsvFile = new SaveManagementToCsvFile();
        this.removeManagementToCsvFile = new RemoveManagementToCsvFile();
    }

    public void start() {
        boolean flag = true;
        do {
            int decision = views.startMenu();
            switch (decision) {
                case 1:
                    managementViews();
                    break;
                case 2:
                    managementViewsAddAndRemove();
                    break;
                case 3:
                    managementSearch();
                    break;
                case 0:
                    System.exit(0);
                default:
                    flag = false;
                    System.out.println("zly numer");
            }
        } while (flag);
    }

    private void managementViews() {
        boolean flag = true;
        List<Management> managementList = managementFacade.findAll();
        do {
            int decision = views.managementList(managementList);
            switch (decision) {
                case 1:
                    views.managementList(managementList);
                    System.out.println("Wcisnij '0' aby wrocic do menu");
                    break;
                case 0:
                    start();
                default:
                    System.out.println("Zly numer");
                    flag = false;
            }
        } while (flag);
    }

    private void managementViewsAddAndRemove() {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        List<Management> managementList = managementFacade.findAll();
        do {
            int decisionAddRemove = views.managementMenu(managementList);
            switch (decisionAddRemove) {
                case 1:
                    System.out.println("Dodaj osobe ");
                    System.out.println("Wpisz w kolejnosci po spacji 'Stanowisko Imie Nazwisko Wiek Wyplate' ");
                    managementFacade.save(new Management(null, scanner.next(), scanner.next(), scanner.next(),
                            scanner.nextInt(), scanner.nextDouble()));
                    System.out.println("Wcisnij 0 aby wrocic do menu");
                    break;
                case 2:
                    System.out.println("Usun osobe ");
                    ReadManagementCsvFile readManagementCsvFile = new ReadManagementCsvFile();
                    readManagementCsvFile.read("C:\\Users\\jakub\\IdeaProjects\\HumanResourcesMenagementProgram" +
                            "\\src\\main\\resources\\Management.csv");
                    System.out.println("Usun wybrana osobe z listy wpisujac w kolejnosci \n" +
                            "Stanowisko Imie Nazwisko Wiek Wyplata \n" +
                            "po spacji ");
                    managementFacade.delete(new Management(null, scanner.next(), scanner.next(), scanner.next(),
                            scanner.nextInt(), scanner.nextDouble()));
                    System.out.println("Wcisnij 0 aby wrocic do menu");
                    break;
                case 0:
                    start();
                default:
                    System.out.println("Zly numer");
                    flag = false;
            }
        } while (flag);
    }

    private void managementSearch() {
        boolean flag = true;
        List<Management> managementList = managementFacade.findAll();
        do {
            int decisionSearch = views.managementSearch(managementList);
            switch (decisionSearch) {
                case 1:
                    System.out.println("Wyszukaj po stanowisku ");
                    String position = views.getPosition();
                    managementList = managementFacade.findByPosition(position);
                    System.out.println("Wybierz '0' aby wrocic do menu ");
                    break;
                case 2:
                    System.out.println("Wyszukaj po imieniu ");
                    String name = views.getName();
                    managementList = managementFacade.findByName(name);
                    System.out.println("Wybierz '0' aby wrocic do menu ");
                    break;
                case 3:
                    System.out.println("Wyszukaj po nazwisku ");
                    String lastName = views.getLastName();
                    managementList = managementFacade.findByLastName(lastName);
                    System.out.println("Wybierz '0' aby wrocic do menu ");
                    break;
                case 0:
                    start();
                default:
                    flag = false;
                    System.out.println("Zly numer ");
            }
        } while (flag);
    }
}
