package omega.com.processor;

import java.util.Arrays;

import omega.com.annotations.OmegaExtra;

public class StringUtils {

    static final String sReservedKeywords[] = { "abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "extends", "false",
            "final", "finally", "float", "for", "goto", "if", "implements",
            "import", "instanceof", "int", "interface", "long", "native",
            "new", "null", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "true",
            "try", "void", "volatile", "while" };

    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

    public static String formatMethodName(OmegaExtra annotation) {
        return annotation.value().trim().replaceAll(" ", "_");
    }

    public static String replaceFirstToLowerCase(String value) {
        if (value == null || value.length() == 0) return "";

        String formatted = value.substring(0, 1);
        return value.replaceFirst(formatted, formatted.toLowerCase());
    }

    public static boolean isSuitableName(String name) {
        if (isEmpty(name)) return false;
        String firstLetter = name.substring(0, 1);

        if (firstLetter.equals("_")) return true;
        if (!Character.isLetter(name.charAt(0))) return false;
        if (Arrays.binarySearch(sReservedKeywords, name) >= 0) return false;

        return true;
    }


}
