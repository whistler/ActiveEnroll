package com.mss.tuess.util;

import com.mss.tuess.entity.User;

public class CurrentUser {

    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User u) {
        user = u;
    }

    public static String getSidebarPath() {
        return "/com/mss/tuess/views/" + user.getClass().getSimpleName() + "Sidebar.fxml";
    }
}
