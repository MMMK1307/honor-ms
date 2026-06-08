package SocialCreditMS.Util;

import SocialCreditMS.Model.UserAccess;

public class AppState {
    private String login;
    private UserAccess access;

    private static AppState _instance;

    private AppState(String login, UserAccess access) {
        this.login = login;
        this.access = access;
    }

    public static AppState getInstance() {
        if(_instance == null) {
            _instance = new AppState("default_login", UserAccess.Basic);
        }
        return _instance;
    }

    public static void setState(String login, UserAccess access) {
        var state = getInstance();
        state.login = login;
        state.access = access;
    }

    public static boolean hasAdminAccess() {
        var state = getInstance();
        return state.access == UserAccess.Admin || state.access == UserAccess.Emperor;
    }

    public static boolean hasEmperorAccess() {
        var state = getInstance();
        return state.access == UserAccess.Emperor;
    }
}
