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
}