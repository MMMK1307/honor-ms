package SocialCreditMS.View;

import AltStd.Altio.Print;
import AltStd.Altio.Reader;
import SocialCreditMS.Controller.ServiceController;
import SocialCreditMS.Model.Service;

import java.util.ArrayList;

public class ServiceView {
    private static Reader sc = new Reader();

    public static Service getByName() {
        String name = sc.String(" Service Name: ");
        var services = ServiceController.getByName(name);
        if(services.isEmpty()) {
            System.out.println(
                    "No services found."
            );
            return null;
        }
        return getBy(services);
    }

    public static Service getBy(ArrayList<Service> services) {
        Print.nl("\n-- Services --");
        Print.nl("ID || NAME || CREDIT || COST || STATUS ");

        for (int i = 0; i < services.size(); i++) {
            var service = services.get(i);

            Print.fl(
                    "%3d || %s || %d || %.2f || %s",
                    i + 1,
                    service.getName(),
                    service.getRequiredCredit(),
                    service.getCost(),
                    service.isActive() ? "Active" : "Inactive"
            );
        }

        int position =
                sc.UntilInt(
                        "\nId/Position: ",
                        "Invalid Id/Position. Try again: ",
                        (i) -> i >= 0 && i <= services.size()
                );

        position--;

        if (position == -1) {
            return null;
        }

        return services.get(position);
    }

    public static Service create() {
        String name =
                sc.String("Service Name: ");

        int requiredCredit =
                sc.UntilInt(
                        "Required Credit: ",
                        "Invalid Credit. Try again: ",
                        (i) -> i >= 0
                );

        double cost =
                sc.UntilDouble(
                        "Cost: ",
                        "Invalid Cost. Try again: ",
                        (d) -> d >= 0
                );

        int activeOption =
                sc.UntilInt(
                        "Active? [1] Yes [0] No: ",
                        "Invalid Option. Try again: ",
                        (i) -> i == 0 || i == 1
                );

        boolean active = activeOption == 1;

        return Service.create(
                name,
                requiredCredit,
                cost,
                active
        );
    }

    public static void menu() {
        int option = -1;

        while(option != 0) {
            Print.nl("[1] Create Service");
            Print.nl("[2] Search Service");
            Print.nl("[3] List All Services");
            Print.nl("[4] Activate/Deactivate Service");
            Print.nl("[5] Use Service");
            Print.nl("[0] Exit");
            option = sc.UntilInt(
                    "Option: ",
                    "Invalid Option. Try again: ",
                    (i) -> i >= 0 && i <= 5
            );

            switch(option) {
                case 1:
                    ServiceController.createService();
                    break;

                case 2:
                    getByName();
                    break;
                case 3:
                    ServiceController.listAll();
                    break;
                case 4:
                    ServiceController.toggleService();
                    break;

                case 5:
                    ServiceController.useService();
                    break;
            }

        }
    }
}