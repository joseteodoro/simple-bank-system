package plastic;

public class CardEntry {

    private static final String MOCK_VALUE = "fake-value";

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
    
}
