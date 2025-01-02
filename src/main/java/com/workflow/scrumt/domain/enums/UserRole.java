package com.workflow.scrumt.domain.enums;

import java.util.Arrays;

public enum UserRole {
    OWNER,
    SCRUM_MASTER,
    PRODUCT_OWNER,
    DEVELOPER,
    UNDEFINED;

    public static boolean isValid(UserRole role) {
        return role != null && Arrays.asList(UserRole.values()).contains(role);
    }
}
