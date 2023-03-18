package com.example.findmypet.Utils;

import java.util.Random;

public class RandomString {

    public static String getSaltString(){
        String  SALTCHARS = "ABCDEFGHIJKLMNNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        while (salt.length() < 10){
            int index = (int) (random.nextFloat() * SALTCHARS.length());
            salt.append(index);
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
