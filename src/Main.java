import SocialCreditMS.Controller.MainController;
import SocialCreditMS.Model.Citizen;
import SocialCreditMS.Model.User;
import SocialCreditMS.Model.UserAccess;
import SocialCreditMS.View.MainView;
import SocialCreditMS.db.CitizenRepository;
import SocialCreditMS.db.UserRepository;

import static SocialCreditMS.Util.CommonUI.printLogo;

public class Main {

    public static void initDb() {
        UserRepository userRepo = new UserRepository();

        var adminUsers = userRepo.getBy(u -> u.getLogin().equals("admin"));
        if(adminUsers.isEmpty()) {
            User adminUser = User.create("Admin", "admin", "admin", UserAccess.Admin);
            userRepo.save(adminUser);
        }

        var emperorUsers = userRepo.getBy(u -> u.getLogin().equals("emp"));
        if(emperorUsers.isEmpty()) {
            User emperorUser = User.create("Emperor", "emp", "emp", UserAccess.Emperor);
            userRepo.save(emperorUser);
        }

        var basicUsers = userRepo.getBy(u -> u.getLogin().equals("basic"));
        if(basicUsers.isEmpty()) {
            User basicUser = User.create("Basic", "basic", "basic", UserAccess.Basic);
            userRepo.save(basicUser);
        }

        CitizenRepository citizenRepo = new CitizenRepository();
        var citizens = citizenRepo.getBy(u -> u.getName().equals("basic"));
        if(citizens.isEmpty()) {
            var citizen = Citizen.create("basic");
            citizenRepo.save(citizen);
        }
    }

    public static void main(String[] args) {
        initDb();
        printLogo();
        MainView.loginPage();
        // MainController.menu();
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