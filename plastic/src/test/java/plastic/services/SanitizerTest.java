package plastic.services;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SanitizerTest {

    @Test
    public void shouldSanitizePrefixProperly() {
        assertEquals(Long.valueOf(10000000l), Sanitizer.sanitizePrefix("1"));
        assertEquals(Long.valueOf(0l), Sanitizer.sanitizePrefix("0"));
        assertEquals(Long.valueOf(0l), Sanitizer.sanitizePrefix("000000000000000000"));
        assertEquals(Long.valueOf(10010001l), Sanitizer.sanitizePrefix("10010001001"));
        assertEquals(Long.valueOf(99999999l), Sanitizer.sanitizePrefix("9999999999999999"));
    }

    @Test
    public void shouldSanitizeNumberProperly() {
        assertEquals(Long.valueOf(1000000000000000l), Sanitizer.sanitizeNumber("1"));
        assertEquals(Long.valueOf(0l), Sanitizer.sanitizeNumber("0"));
        assertEquals(Long.valueOf(0l), Sanitizer.sanitizeNumber("000000000000000000"));
        assertEquals(Long.valueOf(1001000100100000l), Sanitizer.sanitizeNumber("10010001001"));
        assertEquals(Long.valueOf(9999999999999999l), Sanitizer.sanitizeNumber("9999999999999999"));
    }
    
}
