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
        return getBy(services);
    }

    public static Service getBy(ArrayList<Service> services) {
        Print.nl("\n-- Services --");
        Print.nl("ID || NAME");

        for(int i = 0; i < services.size(); i++) {
            var service = services.get(i);

            Print.fl(
                    "%3d || %s",
                    i + 1,
                    service.getName()
            );
        }

        int position =
                sc.UntilInt(
                        "\nId/Position: ",
                        "Invalid Id/Position. Try again: ",
                        (i) -> i >= 0 && i <= services.size()
                );

        position--;

        if(position == -1) {
            return null;
        }

        return services.get(position);
    }
}