package utils;

/**
 * Created by Karol Pokomeda on 2017-06-11.
 */
public class StringFormatter {
    public static String center(String string, int length) {
        if (length < string.length())
            throw new IllegalArgumentException("String is longer than the designated placeholder");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < (length - string.length()) / 2; i++) result.append(" ");
        result.append(string);
        while (result.length() < length) result.append(" ");
        return result.toString();
    }
}
