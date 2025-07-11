package com.sparta.ntw.cards;

public class FancyPlayingCard extends Card {
    public static enum Suits { //∆ ß Ω ◊ (§)
        HEARTS,
        DIAMONDS,
        CLUBS,
        SPADES
    }
    public static enum Values {
        ACE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        JOKER
    }

    private Suits suit;
    private Values value;

    public FancyPlayingCard(Suits suit, Values value) throws IllegalArgumentException{
        if (value != Values.JOKER){
            this.suit = suit;
            this.value = value;
        } else {
            throw new IllegalArgumentException("JOKER cannot have a suit");
        }
    }

    public FancyPlayingCard(Values value) throws IllegalArgumentException{
        if (value == Values.JOKER){
            this.value = value;
        } else {
            throw new IllegalArgumentException("Only JOKER can have no suit");
        }
    }

    public Suits getSuit() {
        return suit;
    }

    public Values getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (value == Values.JOKER){
            return value.toString();
        }
        return value + " of " + suit;
    }

    public static FancyPlayingCard[] fullDeck(){
        return fullDeck(0);
    }

    public static FancyPlayingCard[] fullDeck(int jokerCount){
        FancyPlayingCard[] deck = new FancyPlayingCard[52+jokerCount];
        int nextEmpty = 0;
        for (Suits s : Suits.values()){
            for (Values v : Values.values()){
                try{
                    FancyPlayingCard newCard = new FancyPlayingCard(s, v);
                    deck[nextEmpty] = newCard;
                    nextEmpty++;
                } catch (IllegalArgumentException e) {
                    continue;
                }
            }
        }
        for (int c = 0; c < jokerCount; c++){
            try{
                FancyPlayingCard newCard = new FancyPlayingCard(Values.JOKER);
                deck[nextEmpty] = newCard;
                nextEmpty++;
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
        return deck;
    }
}
