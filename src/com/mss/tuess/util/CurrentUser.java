package com.mss.tuess.util;

import com.mss.tuess.entity.User;

/**
 * current user class. Gets current user (student, instructor and administrator) to display sidebar on the left.
 */
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
