package com.poly.common;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumber {

    public int randomNumber() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int value = rand.nextInt(100000, 999999);
        return value;
    }

    public static void main(String[] args) {
        RandomNumber rand = new RandomNumber();
        System.out.println(rand.randomNumber());
    }
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
}
