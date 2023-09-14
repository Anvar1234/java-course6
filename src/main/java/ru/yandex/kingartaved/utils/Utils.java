package ru.yandex.kingartaved.utils;

public class Utils {


    public final static boolean isNumber(String exp) {
        try {
            Integer.parseInt(String.valueOf(exp.charAt(0)));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    public final static String addSpaces(String exp) {

        String result = exp.replaceAll("([\\(\\)\\+\\-\\*\\/\\^])", " $1 ");

        result = result.replaceAll("\\s+", " ").trim();
        return result;
    }
}
