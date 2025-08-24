package com.company.helper;

import com.company.entity.Developer;

import java.time.LocalDate;
import java.time.Period;

public class DeveloperAgeGenrator {

    public static int genratedeveloperAge(Developer developer) {
        LocalDate dob = developer.getDob();
        int age = Period.between(dob, LocalDate.now()).getYears();
        return age;
    }
}
