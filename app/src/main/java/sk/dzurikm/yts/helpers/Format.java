package sk.dzurikm.yts.helpers;

public class Format {
    public static String firstLetterUppercase(String str){
        if (str == null || str.isEmpty()) {
            return str;
        }

        // Convert the first letter to uppercase and append the rest of the string
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String sanitizeFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return fileName;
        }

        // List of characters not allowed in Android file names
        final String illegalChars = "[/\\\\?%*:|\"<>.]";

        // Replace illegal characters with an empty string
        return fileName.replaceAll(illegalChars, "");
    }
}
