package de.rs.blackjack.model.card;

/**
 * Created by Rene Sommerfeld on 04.03.2018.
 */
public abstract class Card implements Comparable<Card> {

    protected CardDeck.Suit suit;
    protected CardDeck.Value value;

    public Card(CardDeck.Suit suit, CardDeck.Value value) {
        this.suit = suit;
        this.value = value;
    }

    public CardDeck.Suit getSuit() {
        return suit;
    }

    public CardDeck.Value getValue() {
        return value;
    }

    public abstract int score();

    public boolean matches(Card otherCard) {
        return matchesSuit(otherCard) && matchesValue(otherCard);
    }

    public boolean matchesSuit(Card otherCard) {
        if(!(this instanceof CuttingCard) && !(otherCard instanceof CuttingCard)) {
            return suit == otherCard.getSuit();
        }
        return false;
    }

    public boolean matchesValue(Card otherCard) {
        if(!(this instanceof CuttingCard) && !(otherCard instanceof CuttingCard)) {
            return value == otherCard.getValue();
        }
        return false;
    }

    public boolean matchesColor(Card otherCard) {
        if(!(this instanceof CuttingCard) && !(otherCard instanceof CuttingCard)) {
            return suit.getColor() == otherCard.getSuit().getColor();
        }
        return false;
    }

    @Override
    public int compareTo(Card otherCard) {
        CardDeck.Value otherCardValue = otherCard.getValue();
        if(otherCardValue != null) {
            return value.ordinal() - otherCardValue.ordinal();
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(suit.getCharacter());
        str.append(" ");
        str.append(value.getCharacter());
        return str.toString();
    }
}
