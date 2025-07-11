package com.sparta.ntw.cards;

public class PlayingCard extends Card {
    public static enum Suits {
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

    public PlayingCard(Suits suit, Values value) throws IllegalArgumentException{
        if (value != Values.JOKER){
            this.suit = suit;
            this.value = value;
        } else {
            throw new IllegalArgumentException("JOKER cannot have a suit");
        }
    }

    public PlayingCard(Values value) throws IllegalArgumentException{
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

    public static PlayingCard[] fullDeck(){
        return fullDeck(0);
    }

    public static PlayingCard[] fullDeck(int jokerCount){
        PlayingCard[] deck = new PlayingCard[52+jokerCount];
        int nextEmpty = 0;
        for (Suits s : Suits.values()){
            for (Values v : Values.values()){
                try{
                    PlayingCard newCard = new PlayingCard(s, v);
                    deck[nextEmpty] = newCard;
                    nextEmpty++;
                } catch (IllegalArgumentException e) {
                    continue;
                }
            }
        }
        for (int c = 0; c < jokerCount; c++){
            try{
                PlayingCard newCard = new PlayingCard(Values.JOKER);
                deck[nextEmpty] = newCard;
                nextEmpty++;
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
        return deck;
    }

    public String fancyString(){
        String fancyString = "";
        switch (value){
            // Joker = §
            case TWO -> fancyString += "2";
            case THREE -> fancyString += "3";
            case FOUR -> fancyString += "4";
            case FIVE -> fancyString += "5";
            case SIX -> fancyString += "6";
            case SEVEN -> fancyString += "7";
            case EIGHT -> fancyString += "8";
            case NINE -> fancyString += "9";
            case TEN -> fancyString += "10";
            case JACK -> fancyString += "J";
            case QUEEN -> fancyString += "Q";
            case KING -> fancyString += "K";
            case ACE -> fancyString += "A";
            case JOKER -> fancyString += "§";
        }
        if (value != Values.JOKER) {
            switch (suit) {
                case HEARTS:
                    fancyString += "Ω";
                    break;
                case DIAMONDS:
                    fancyString += "◊";
                    break;
                case CLUBS:
                    fancyString += "ß";
                    break;
                case SPADES:
                    fancyString += "∆";
                    break;
            }
        }
        return fancyString;
    }
}
