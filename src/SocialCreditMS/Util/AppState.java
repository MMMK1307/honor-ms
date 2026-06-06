package SocialCreditMS.Util;

import SocialCreditMS.Model.UserAccess;

public class AppState {
    String login;
    UserAccess access;

    private static AppState _instance;

    private AppState(String login, UserAccess access) {
        this.login = login;
        this.access = access;
    }

    public static AppState getInstance() {
        if(_instance == null) {
            createState("default_login", UserAccess.Basic);
        }
        return _instance;
    }

    public static void createState(String login, UserAccess access) {
        if(_instance == null) {
            _instance = new AppState(login, access);
        }
    }
}
