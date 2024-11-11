package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
     String name = "Aleftina";
     int age = 30;
     double weightKg = 55.5;
     boolean chidren = true;
     char gender = 'ж';
     long l = 345676744L;
     float f = 234564.123555F;
     byte b = 16;
     LOG.debug("name : {}, age : {}, weightKg : {}, chidren : {}, gender : {}, l : {}, f : {}, b : {} ",
             name, age, weightKg, chidren, gender, l, f, b);
    }
}