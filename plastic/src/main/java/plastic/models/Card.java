package plastic.models;

public class Card {

    private String cardNumber;

    public Card(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public static Card of(String cardNumber) {
        return new Card(cardNumber);
    }
    
}
