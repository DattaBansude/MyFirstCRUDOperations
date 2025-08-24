package com.company.helper;

import com.company.entity.Developer;

import java.time.LocalDate;


public class DeveloperIdGenrator {

    public static String genratedeveloperId(Developer developer) {

        String fName = developer.getFName();
        String lName = developer.getLName();
       // int yob = developer.getYearOfBirth();
        LocalDate yob = developer.getDob();

        char f = fName.charAt(0);

        int y = yob.getYear() % 100;
        String developerId = f + lName + y;
        return developerId;
    }
}
