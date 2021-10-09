package plastic;

import java.util.List;

public class SampleImmutableDatabase {

    public static List<CardEntry> entries = List.of(
        CardEntry.of("American Express", "341111111111111"),
        CardEntry.of("American Express", "378282246310005"),
        CardEntry.of("American Express", "371449635398431"),
        CardEntry.of("American Express", "378734493671000"),
        CardEntry.of("Diners", "30369309025904"),
        CardEntry.of("Diners", "38520000023237"),
        CardEntry.of("Discover", "6011601160116611"),
        CardEntry.of("Discover", "6011111111111117"),
        CardEntry.of("Discover", "6011000990139424"),
        CardEntry.of("JCB", "3528111333300000"),
        CardEntry.of("JCB", "3337002020360505"),
        CardEntry.of("MasterCard", "5431111111111111"),
        CardEntry.of("MasterCard", "5555555555554444"),
        CardEntry.of("MasterCard", "5105105105105100"),
        CardEntry.of("Visa", "4716111111111111"),
        CardEntry.of("Visa", "4716888888881881"),
        CardEntry.of("Visa", "4929222222222")
    );

}
