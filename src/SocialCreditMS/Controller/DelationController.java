package SocialCreditMS.Controller;

import SocialCreditMS.Model.Delation;
import SocialCreditMS.db.DelationRepository;

import java.util.ArrayList;
import java.util.UUID;

public class DelationController {

    private static DelationRepository delationRepo = new DelationRepository();

    public static boolean registrarDenuncia(UUID citizenId, String motivo) {

        Delation delation = Delation.create(citizenId, motivo);

        return delationRepo.save(delation);
    }

    public static Delation getById(UUID id) {
        return delationRepo.getById(id);
    }

    public static ArrayList<Delation> getByCitizenId(UUID citizenId) {
        return delationRepo.getBy(
                (d) -> d.getCitizenId().equals(citizenId)
        );
    }
}