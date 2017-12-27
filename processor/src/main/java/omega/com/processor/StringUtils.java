package omega.com.processor;

import omega.com.annotations.OmegaExtra;

public class StringUtils {

    /**
     * Returns true if the string is null or 0-length.
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

    public static String formatMethodName(OmegaExtra annotation) {
        return annotation.value().trim().replaceAll(" ", "_");
    }

}
