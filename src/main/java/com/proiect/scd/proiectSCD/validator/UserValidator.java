package com.proiect.scd.proiectSCD.validator;

import com.proiect.scd.proiectSCD.entity.Role;
import com.proiect.scd.proiectSCD.entity.User;

public class UserValidator {
    public static boolean hasUserRole(User user) {
        if (user.getRole() == null) return false;
        return user.getRole() == Role.ADMIN || user.getRole() == Role.DIRECTOR;
    }
}
