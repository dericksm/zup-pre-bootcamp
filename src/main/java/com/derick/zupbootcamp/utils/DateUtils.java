package com.derick.zupbootcamp.utils;

import java.time.Period;
import java.time.ZonedDateTime;

public class DateUtils {

    public static boolean hasAgeRequirement(ZonedDateTime birthDate) {
        Period p = Period.between(birthDate.toLocalDate(), ZonedDateTime.now().toLocalDate());
        if (p.getYears() < 18) {
            return false;
        } else {
            return true;
        }
    }
}
