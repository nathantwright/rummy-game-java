package com.sparta.ntw.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deck{
    private ArrayList<Card> deckCards;

    // CONSTRUCTORS
    public Deck(ArrayList<Card> deckCards){
        this.deckCards = new ArrayList<Card>(deckCards.size());
        for (Card card : deckCards){
            this.addCard(card);
        }
    }
    public Deck(Card[] cards) {
        this.deckCards = new ArrayList<Card>(cards.length);
        this.deckCards.addAll(Arrays.asList(cards));
    }
    public Deck(Deck deck) {
        this.deckCards = new ArrayList<Card>(deck.getDeckCards().size());
        for (Card card : deck.getDeckCards()){
            this.addCard(card);
        }
    }
    public Deck(){
        this.deckCards = new ArrayList<Card>();
    }


    // SIMPLE METHODS
    public ArrayList<Card> getDeckCards() {
        return deckCards;
    }
    public Deck addCard(Card card){
        deckCards.add(card);
        return this;
    }
    public Card takeCard() throws IndexOutOfBoundsException{
        if (!deckCards.isEmpty()) {
            Card returnCard = deckCards.get(deckCards.size() - 1);
            deckCards.remove(deckCards.size() - 1);
            return returnCard;
        }
        throw new IndexOutOfBoundsException("Cannot take from empty deck");
    }
    public Card takeCard(Card cardToTake) throws IndexOutOfBoundsException{
        if (deckCards.contains(cardToTake)) {
            deckCards.remove(cardToTake);
            return cardToTake;
        } else {
            throw new IndexOutOfBoundsException("Card '" + cardToTake.toString() + "' not found in this deck");
        }
    }
    public Card takeCard(String cardToTake) throws IndexOutOfBoundsException{
        for (Card c : deckCards){
            if (c.toString().equalsIgnoreCase(cardToTake)){
                deckCards.remove(c);
                return c;
            }
        }
        throw new IndexOutOfBoundsException("Card '" + cardToTake + "' not found in this deck");
    }
    public Card[] seeCards() throws IndexOutOfBoundsException{
        return seeCards(deckCards.size());
    }
    public Card[] seeCards(int numToSee) throws IndexOutOfBoundsException{
        if (deckCards.size() >= numToSee){
            Card[] seenCards = new Card[numToSee];
            for (int i = 0; i < numToSee; i++){
                seenCards[i] = deckCards.get((deckCards.size()-1)-i);
            }
            return seenCards;
        }
        throw new IndexOutOfBoundsException(deckCards.size() + " cards left in deck, so can't see " + numToSee);
    }

    // Prints deck content nicely
    public void deckPrint(){
        System.out.println("v bottom card v");
        for (Card c : deckCards){
            System.out.println(c);
        }
        System.out.println("^ top card ^");
    }

    // Another way to print
    public void handPrint(){
        String printString = "";
        for (Card c : deckCards) {
            printString += c + ", ";
        }
        System.out.println(printString.substring(0, printString.length()-2));
    }

    // Shuffles deck
    public void shuffle(){
        ArrayList<Card> newList = new ArrayList<Card>();
        for (int c = deckCards.size(); c > 0; c--){
            int pos = (int) (c * Math.random());
            newList.add(deckCards.get(pos));
            deckCards.remove(pos);
        }
        deckCards = newList;
    }

    // Deals all cards as evenly as possible between the hands provided
    public void dealAll(List<Deck> hands){
        int initialSize = deckCards.size();
        for (int i = initialSize-1; i >= 0; i--){
            hands.get(((initialSize-1)-i) % hands.size()).addCard(takeCard());
        }
    }

    // Deals each hand provided the given number of cards
    public void dealNumber(List<Deck> hands, int numPerHand) throws IndexOutOfBoundsException{
        if (numPerHand * hands.size() > deckCards.size()){
            throw new IndexOutOfBoundsException("Not enough cards in deck to deal " + numPerHand + " cards each to " + hands.size() + " hands");
        } else {
            int initialSize = deckCards.size();
            int totalDealt = hands.size() * numPerHand;
            for (int i = initialSize - 1; i > (initialSize - 1) - totalDealt; i--) {
                hands.get(((initialSize-1)-i) % hands.size()).addCard(takeCard());
            }
        }
    }
}

// If there is a draw Deck and a discard Deck in your game, I would recommend shuffling the discard Deck and swapping
// their references when either:
// 1) When the draw Deck is empty, or
// 2) When the draw Deck does not contain enough cards for the action a player wants to take