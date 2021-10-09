package plastic.services;

public class Sanitizer {

    private static final String MAX_PREFIX_PADDING_ZEROS = "00000000";

    private static final String MAX_NUMBER_PADDING_ZEROS = MAX_PREFIX_PADDING_ZEROS + MAX_PREFIX_PADDING_ZEROS;

    private static final int MAX_LENGHT_PREFIX = 8;

    private static final int MAX_LENGHT_NUMBER = 16;

    private Sanitizer() {}
    
    public static Long sanitizePrefix(String prefix) {
        return Long.parseLong((prefix + MAX_PREFIX_PADDING_ZEROS).substring(0, MAX_LENGHT_PREFIX));
    }

    public static Long sanitizeNumber(String prefix) {
        return Long.parseLong((prefix + MAX_NUMBER_PADDING_ZEROS).substring(0, MAX_LENGHT_NUMBER));
    }

}
