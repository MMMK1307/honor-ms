package SocialCreditMS.Controller;

import SocialCreditMS.Model.Service;
import SocialCreditMS.View.ServiceView;
import SocialCreditMS.db.ServiceRepository;

import java.util.ArrayList;

public class ServiceController {

    private static ServiceRepository serviceRepo =
            new ServiceRepository();

    public static Service getSingleByName() {
        return ServiceView.getByName();
    }

    public static ArrayList<Service> getByName(String name) {
        return serviceRepo.getBy(
                (s) -> s.getName().contains(name)
        );
    }
    public static void listAll() {
        var services = serviceRepo.getAll();

        ServiceView.getBy(services);
    }
    public static void createService() {
        Service service = ServiceView.create();

        boolean success = serviceRepo.save(service);

        if(success) {
            System.out.println("Service created successfully!");
        } else {
            System.out.println("Failed to create service.");
        }
    }
    public static void menu() {
        ServiceView.menu();
    }
}