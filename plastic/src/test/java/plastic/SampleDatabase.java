package plastic;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class SampleDatabase {

    private static List<CardEntry> entries = List.of(
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


    public static List<CardEntry> getEntries() {
        return entries;
    }

    public static List<String> largeCardSample() throws IOException {
        List<String> lines = new LinkedList<>();
        try (Scanner scanner = new Scanner(SampleDatabase.class.getResourceAsStream("/mock-cards.csv"))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }
        return lines;
    }

}
