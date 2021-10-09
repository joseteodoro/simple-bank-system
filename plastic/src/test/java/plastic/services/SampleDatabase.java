package plastic.services;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

import plastic.models.Brand;
import plastic.models.CardEntry;

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


    public static List<CardEntry> smallCardSample() {
        return entries;
    }

    private static final long DEFAULT_LARGE_SAMPLE_SIZE = 1000000000;

    public static List<CardEntry> largeCardSample() throws IOException {
        return largeCardSample(DEFAULT_LARGE_SAMPLE_SIZE, new BrandDatabase().listBrandRanges());
    }

    public static List<CardEntry> largeCardSample(long size, List<Brand> availableBrands) throws IOException {
        char [] numberOptions = new char [] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Brand [] brandOptions = availableBrands.toArray(new Brand [availableBrands.size()]);
        Randomize randomize = new Randomize();

        Supplier<CardEntry> randomCardNumberGenerator = () -> {
            Brand brand = randomize.randomOf(brandOptions);
            String cardSufix = randomize.randomString(8, numberOptions);
            return CardEntry.of(brand, brand.getStartRange().substring(0, 8) + cardSufix);
        };
        
        return Stream.generate(randomCardNumberGenerator)
            .limit(size)
            .toList();
    }

    public static List<Brand> largeBrandSample(long size) throws IOException {
        char [] numberOptions = new char [] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Randomize randomize = new Randomize();

        String startSufix = "00000000";
        String endSufix = "99999999";
        Supplier<Brand> randomBrand = () -> {
            String prefix =  randomize.randomString(8, numberOptions);
            String brandName = prefix + UUID.randomUUID().toString();
            
            return Brand.of(brandName, prefix + startSufix, prefix  + endSufix);
        };
        
        return Stream.generate(randomBrand)
            .limit(size)
            .toList();
    }

    public static List<Brand> midBrandSample() {
        return List.of(
            Brand.of("70469146ee93ccbd-6084-465f-927a-796bccc3d0bb", "7046914600000000", "7046914699999999"),
            Brand.of("25321483087649d5-e22f-412f-9b17-32524d32d73e", "2532148300000000", "2532148399999999"),
            Brand.of("06821915af62f00e-7173-4b24-9a2a-42297b7cea04", "0682191500000000", "0682191599999999"),
            Brand.of("92020956aadb9633-6979-449f-8128-d0a5be73ec23", "9202095600000000", "9202095699999999"),
            Brand.of("342607317cb95896-1b0d-464b-9db2-e6c28585faaa", "3426073100000000", "3426073199999999"),
            Brand.of("22401273dc114bb5-4746-4bb1-aa19-6df1b6911ec5", "2240127300000000", "2240127399999999"),
            Brand.of("3678457645cf86bc-962a-4457-ac18-a25b215efe09", "3678457600000000", "3678457699999999"),
            Brand.of("16274626f34c6c7c-f2b3-4950-82cb-2acd3d75d6a1", "1627462600000000", "1627462699999999"),
            Brand.of("30143235652d0c00-0a7a-49ff-a4c7-6efe2701011b", "3014323500000000", "3014323599999999"),
            Brand.of("437769719afdebdc-20f8-447b-8b5c-abe7f26cb815", "4377697100000000", "4377697199999999"),
            Brand.of("71469146abdf88e0-1abe-479d-8e40-447f7a17a143", "7146914600000000", "7146914699999999 "),
            Brand.of("25321483782379e5-e6e6-4b4d-abbb-aa6f276a9ed8", "2532148300000000", "2532148399999999 "),
            Brand.of("061219156ff03844-11ee-4f5c-a2c4-309ccc220de1", "0612191500000000", "0612191599999999 "),
            Brand.of("92020956c03eee1a-ecdb-4dfd-8ada-fcc3e7dda848", "9202095600000000", "9202095699999999 "),
            Brand.of("34160731b1c7f0ed-03b1-4774-9c89-348b12269039", "3416073100000000", "3416073199999999 "),
            Brand.of("22401273eedc8e72-b4b6-43a2-afae-ae512367545f", "2240127300000000", "2240127399999999 "),
            Brand.of("367845767078066e-3adf-42a3-bc4f-b84169392f23", "3678457600000000", "3678457699999999 "),
            Brand.of("16274626acfb6e09-3b2c-4d6a-bd43-dd901778d4a8", "1627462600000000", "1627462699999999 "),
            Brand.of("31143235aa74e820-f8c8-4b8d-b97c-a359c0e2e88a", "3114323500000000", "3114323599999999 "),
            Brand.of("43776971905cfbeb-5372-4c7f-b653-be6a2f3c3ae1", "4377697100000000", "4377697199999999 ")
        );
    }

    public static List<CardEntry> midCardSample() {
        return List.of(
            CardEntry.of("70469146ee93ccbd-6084-465f-927a-796bccc3d0bb", "7046914600000000", "7046914699999999", "7046914649156708"),
            CardEntry.of("25321483087649d5-e22f-412f-9b17-32524d32d73e", "2532148300000000", "2532148399999999", "2532148307923237"),
            CardEntry.of("06821915af62f00e-7173-4b24-9a2a-42297b7cea04", "0682191500000000", "0682191599999999", "0682191547261844"),
            CardEntry.of("92020956aadb9633-6979-449f-8128-d0a5be73ec23", "9202095600000000", "9202095699999999", "9202095674188814"),
            CardEntry.of("342607317cb95896-1b0d-464b-9db2-e6c28585faaa", "3426073100000000", "3426073199999999", "3426073124009551"),
            CardEntry.of("22401273dc114bb5-4746-4bb1-aa19-6df1b6911ec5", "2240127300000000", "2240127399999999", "2240127347983085"),
            CardEntry.of("3678457645cf86bc-962a-4457-ac18-a25b215efe09", "3678457600000000", "3678457699999999", "3678457650450565"),
            CardEntry.of("16274626f34c6c7c-f2b3-4950-82cb-2acd3d75d6a1", "1627462600000000", "1627462699999999", "1627462687381931"),
            CardEntry.of("30143235652d0c00-0a7a-49ff-a4c7-6efe2701011b", "3014323500000000", "3014323599999999", "3014323539708026"),
            CardEntry.of("437769719afdebdc-20f8-447b-8b5c-abe7f26cb815", "4377697100000000", "4377697199999999", "4377697197732489")
        );
    }

}
