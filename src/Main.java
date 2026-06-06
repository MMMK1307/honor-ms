import SocialCreditMS.Controller.MainController;
import SocialCreditMS.Model.User;
import SocialCreditMS.Model.UserAccess;
import SocialCreditMS.View.MainView;
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