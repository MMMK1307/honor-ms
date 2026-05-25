import AltStd.Altio.Print;
import AltStd.Colors;
import SocialCreditMS.Model.User;
import SocialCreditMS.db.UserRepository;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Print.nl(Colors.Blue, "Azul");
        Print.s(Colors.Red, "Vermelho", "Vermelho", "Vemelho\n");

        UserRepository userRepo = new UserRepository();
        User user = User.create("jonas", "Jonas Roberto", "senhasenha");
        userRepo.save(user);

        User user2 = userRepo.getById(user.getId());
        Print.s(user2.getId().toString());
    }

/*
    User

    Work
    Cidadão - Citizen

    Delação -
    Ordem de Prisão - ArrestOrder

    Servico - Service
    ServiceBoard

    Rules
    Punishments

    EmperorRequests
    EmperorMessage
 */
}