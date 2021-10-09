package plastic.models;

public class CardEntry {

    private static final String MOCK_VALUE = "0";

    private Card card;

    private Brand brand;

    public CardEntry(Brand brand, Card card) {
        this.brand = brand;
        this.card = card;
    }

    public Brand getBrand() {
        return brand;
    }

    public Card getCard() {
        return card;
    }

    public static CardEntry of(String brandName, String cardNumber) {
        return new CardEntry(Brand.of(brandName, MOCK_VALUE, MOCK_VALUE), Card.of(cardNumber));
    }

    public static CardEntry of(Brand brand, String cardNumber) {
        return new CardEntry(brand, Card.of(cardNumber));
    }

    public static CardEntry of(String brandname, String start, String end, String cardNumber) {
        return of(Brand.of(brandname, start, end), cardNumber);
    }
    
}
