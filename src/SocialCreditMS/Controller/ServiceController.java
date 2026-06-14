package SocialCreditMS.Controller;

import SocialCreditMS.Model.Service;
import SocialCreditMS.View.ServiceView;
import SocialCreditMS.db.ServiceRepository;
import SocialCreditMS.Model.Citizen;
import java.util.ArrayList;
import SocialCreditMS.Util.AppState;

public class ServiceController {

    private static ServiceRepository serviceRepo =
            new ServiceRepository();

    public static Service getSingleByName() {
        return ServiceView.getByName();
    }
    public static void toggleService() {
        Service service = getSingleByName();
        if(!AppState.hasAdminAccess()) {
            System.out.println(
                    "Access denied."
            );
            return;
        }

        if(service == null) {
            System.out.println("Service not found.");
            return;
        }

        service.setActive(!service.isActive());

        boolean success = serviceRepo.save(service);

        if(success) {
            System.out.println("Service updated successfully!");
        } else {
            System.out.println("Failed to update service.");
        }
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
        if(!AppState.hasAdminAccess()) {
            System.out.println(
                    "Access denied."
            );
            return;
        }
        Service service = ServiceView.create();

        boolean success = serviceRepo.save(service);

        if(success) {
            System.out.println("Service created successfully!");
        } else {
            System.out.println("Failed to create service.");
        }

    }
    public static void useService() {
        var citizen =
                CitizenController.getSingleByName();

        if(citizen == null) {
            System.out.println("Citizen not found.");
            return;
        }

        var service =
                getSingleByName();

        if(service == null) {
            System.out.println("Service not found.");
            return;
        }

        if(!service.isActive()) {
            System.out.println(
                    "Service unavailable."
            );
            return;
        }

        System.out.println(
                citizen.getName()
                        + " used "
                        + service.getName()
                        + " successfully!"
        );
    }
    public static void menu() {
        ServiceView.menu();
    }
}