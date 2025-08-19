package com.company.helper;

import com.company.entity.Developer;


public class DeveloperIdGenrator {

    public static String genratedeveloperId(Developer developer) {

        String fName = developer.getFName();
        String lName = developer.getLName();
        int yob = developer.getYearOfBirth();

        char f = fName.charAt(0);

        int y = yob % 100;
        String developerId = f + lName + y;
        return developerId;
    }
}
