package by.epam.at.mailtest.util;

import java.util.Random;

public class GenerateData {

    private static final String allowedSymbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private static Random random = new Random();

    public static String createRandomString(int length)
    {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
        {
            sb.append(allowedSymbols.charAt(random.nextInt(allowedSymbols.length())));
        }
        return sb.toString();
    }
}
