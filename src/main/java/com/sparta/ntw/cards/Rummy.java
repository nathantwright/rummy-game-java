package com.sparta.ntw.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Rummy {
    static PlayingCard.Values[] orderedValues = {
            PlayingCard.Values.JOKER,
            PlayingCard.Values.TWO,
            PlayingCard.Values.THREE,
            PlayingCard.Values.FOUR,
            PlayingCard.Values.FIVE,
            PlayingCard.Values.SIX,
            PlayingCard.Values.SEVEN,
            PlayingCard.Values.EIGHT,
            PlayingCard.Values.NINE,
            PlayingCard.Values.TEN,
            PlayingCard.Values.JACK,
            PlayingCard.Values.QUEEN,
            PlayingCard.Values.KING,
            PlayingCard.Values.ACE,
    };

    static Deck drawDeck;
    static Deck discardDeck;
    static ArrayList<Deck> playerHands;

    public static void main(String[] args) {
        setup();

        gameLoop();
    }

    private static void setup() {
        drawDeck = new Deck(PlayingCard.fullDeck());
        discardDeck = new Deck();

        playerHands = new ArrayList<Deck>();
        Scanner numScanner = new Scanner(System.in);
        boolean validNumber = false;
        int playerNum = 2;
        while (!validNumber){
            try{
                System.out.println("How many players (1-7)?");
                playerNum = Integer.parseInt(numScanner.nextLine());
                if (playerNum >= 1 && playerNum <= 7){
                    validNumber = true;
                } else {
                    System.out.println("Invalid value");
                }
            } catch (NumberFormatException e){
                System.out.println("Number not recognised");
            }
        }
        for (int i = 0; i < playerNum; i++) {
            playerHands.add(new Deck());
        }
        drawDeck.shuffle();
        drawDeck.dealNumber(playerHands, 7);
        discardDeck.addCard(drawDeck.takeCard());
    }

    private static void gameLoop(){
        int currentPlayer = -1;
        boolean gameOver = false;
        while (!gameOver){
            currentPlayer += 1;
            currentPlayer %= playerHands.size();
            int readablePlayerNumber = currentPlayer + 1;
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                    "\nPlease pass to player " + readablePlayerNumber +
                    "\nPlayer " + readablePlayerNumber + ": Press enter to start your turn");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            gameOver = playerTurn(currentPlayer);
        }
    }

    private static boolean playerTurn(int playerIndex){
        Deck playerHand = playerHands.get(playerIndex);
        // Show the player their hand
        System.out.println("Your hand: - - - - - - -");
        playerHand.handPrint();

        // Draw a card
        Scanner choiceScanner = new Scanner(System.in);
        Card discardTop = discardDeck.seeCards(1)[0];
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("\nWould you like to take from the draw pile (draw) or take the " + discardTop.toString() + " from the discard pile (discard)?");
            String playerChoice = choiceScanner.nextLine().toLowerCase();
            if (playerChoice.equals("discard")) {
                playerHand.addCard(discardDeck.takeCard());
                validChoice = true;
            } else if (playerChoice.equals("draw")) {
                playerHand.addCard(drawDeck.takeCard());
                validChoice = true;
            } else {
                System.out.println("Invalid choice");
            }
        }
        System.out.println("!!! ANNOUNCE this decision to the other players. !!!");

        // Switcheroo decks if drawDeck becomes empty
        if (drawDeck.getDeckCards().isEmpty()){
            int size = discardDeck.getDeckCards().size();
            for (int i = 0; i < size; i++) {
                drawDeck.addCard(discardDeck.takeCard());
            }
        }

        // Show player's hand again
        System.out.println("\nYour hand: - - - - - - -");
        playerHand.handPrint();

        // Discard a card
        validChoice = false;
        while (!validChoice) {
            System.out.println("\nWhich card would you like to discard?");
            String playerChoice = choiceScanner.nextLine();
            try{
                discardDeck.addCard(playerHand.takeCard(playerChoice));
                validChoice = true;
            } catch (IndexOutOfBoundsException e){
                System.out.println("Invalid choice");
            }
        }
        System.out.println("!!! ANNOUNCE this decision to the other players. !!!");


        // Show player's hand AGAIN
        System.out.println("\nYour hand: - - - - - - -");
        playerHand.handPrint();

        // Optional playerHand rearrangement?
        playerHand = optionalRearrange(playerHand, choiceScanner);

        // Check for win!
        while (true) {
            System.out.println("\nDo you think you have won (y/n)?");
            String playerChoice = choiceScanner.nextLine();
            if (playerChoice.equalsIgnoreCase("y")){
                System.out.println("""
                        Please ensure your cards are ordered in one of the following ways:
                        a) [Set/Run of 4], [Set/Run of 3]
                        b) [Set/Run of 3], [Set/Run of 4]
                        c) [Set/Run of 3], [Set/Run of 3], THREE of ___
                        d) [Set/Run of 7]""");
                playerHand = optionalRearrange(playerHand, choiceScanner);
                System.out.println("Which order have you used (a/b/c/d)?");
                String handOrder = choiceScanner.nextLine();
                switch (handOrder.toLowerCase()){
                    case "b":
                        Card[] handCardsA = playerHand.seeCards();
                        Card[] setRun1A = new Card[4];
                        Card[] setRun2A = new Card[3];
                        System.arraycopy(handCardsA, 0, setRun1A, 0, 4);
                        System.arraycopy(handCardsA, 4, setRun2A, 0, 3);
                        if ((isSet(setRun1A) || isRun(setRun1A)) &&
                                (isSet(setRun2A) || isRun(setRun2A))){
                            System.out.println("You won!");
                            playerHands.set(playerIndex, playerHand);
                            return true;
                        } else {
                            System.out.println("You haven't won with that configuration of cards!");
                            continue;
                        }
                    case "a", "d":
                        Card[] handCardsB = playerHand.seeCards();
                        Card[] setRun1B = new Card[3];
                        Card[] setRun2B = new Card[4];
                        System.arraycopy(handCardsB, 0, setRun1B, 0, 3);
                        System.arraycopy(handCardsB, 3, setRun2B, 0, 4);
                        if ((isSet(setRun1B) || isRun(setRun1B)) &&
                                (isSet(setRun2B) || isRun(setRun2B))){
                            System.out.println("You won!");
                            playerHands.set(playerIndex, playerHand);
                            return true;
                        } else {
                            System.out.println("You haven't won with that configuration of cards!");
                            continue;
                        }
                    case "c":
                        Card[] handCardsC = playerHand.seeCards();
                        Card[] setRun1C = new Card[3];
                        Card[] setRun2C = new Card[3];
                        System.arraycopy(handCardsC, 1, setRun1C, 0, 3);
                        System.arraycopy(handCardsC, 4, setRun2C, 0, 3);
                        if ((isSet(setRun1C) || isRun(setRun1C)) &&
                                (isSet(setRun2C) || isRun(setRun2C)) &&
                                ((PlayingCard) handCardsC[0]).getValue() == PlayingCard.Values.THREE){
                            System.out.println("You won!");
                            playerHands.set(playerIndex, playerHand);
                            return true;
                        } else {
                            System.out.println("You haven't won with that configuration of cards!");
                            continue;
                        }
                    default:
                        System.out.println("Invalid choice");
                        continue;
                }
            } else if (playerChoice.equalsIgnoreCase("n")){
                playerHands.set(playerIndex, playerHand);
                return false;
            }
            System.out.println("Invalid choice");
        }
    }

    public static boolean isSet(Card[] potentialSet){
        ArrayList<PlayingCard> potSet = new ArrayList<PlayingCard>(potentialSet.length);
        Arrays.stream(potentialSet).map(card -> (PlayingCard) card).forEach(potSet::add);
        return isSet(potSet);
    }
    public static boolean isSet(ArrayList<PlayingCard> potentialSet){
        PlayingCard[] potSet = new PlayingCard[potentialSet.size()];
        for (int i = 0; i < potentialSet.size(); i++) {
            potSet[i] = potentialSet.get(i);
        }
        return isSet(potSet);
    }
    public static boolean isSet(PlayingCard[] potentialSet){
        // If there are less than 3 cards, they can't make a set
        if (potentialSet.length < 3){
            return false;
        }

        // If the cards are all the same value (except for wildcards) then they are a set
        HashSet<PlayingCard.Values> valuesHashSet = new HashSet<PlayingCard.Values>();
        for (PlayingCard card : potentialSet){
            if (card.getValue() != PlayingCard.Values.TWO && card.getValue() != PlayingCard.Values.JOKER){
                valuesHashSet.add(card.getValue());
            }
        }
        return valuesHashSet.size() <= 1;
    }
    public static boolean isRun(Card[] potentialRun){
        ArrayList<PlayingCard> potRun = new ArrayList<PlayingCard>(potentialRun.length);
        Arrays.stream(potentialRun).map(card -> (PlayingCard) card).forEach(potRun::add);
        return isRun(potRun);
    }
    public static boolean isRun(ArrayList<PlayingCard> potentialRun){
        PlayingCard[] potRun = new PlayingCard[potentialRun.size()];
        for (int i = 0; i < potentialRun.size(); i++) {
            potRun[i] = potentialRun.get(i);
        }
        return isRun(potRun);
    }
    public static boolean isRun(PlayingCard[] potentialRun){
        // If there are less than 3 cards, they can't make a run
        if (potentialRun.length < 3){
            return false;
        }

        // If the cards aren't all the same suit (ignoring wildcards), they can't make a run
        HashSet<PlayingCard.Suits> suitsHashSet = new HashSet<PlayingCard.Suits>();
        for (PlayingCard card : potentialRun){
            if (card.getValue() != PlayingCard.Values.TWO && card.getValue() != PlayingCard.Values.JOKER){
                suitsHashSet.add(card.getSuit());
            }
        }
        if (suitsHashSet.size() > 1){
            return false;
        }

        // Final checks of the run are much simpler if it is ordered
        // This is NOT an efficient sorting algorithm
        Integer[] orderedPotRun = new Integer[potentialRun.length];
        int nextIndex = 0;
        for (int i = 0; i < orderedValues.length; i++){
            PlayingCard.Values value = orderedValues[i];
            for (PlayingCard card : potentialRun){
                if (card.getValue() == value){
                    orderedPotRun[nextIndex] = i;
                    nextIndex++;
                }
            }
        }

        int wildCards = 0;
        Integer checkAgainst = null;
        for (Integer i : orderedPotRun){
            PlayingCard.Values cardValue = orderedValues[i];
            if (cardValue == PlayingCard.Values.TWO || cardValue == PlayingCard.Values.JOKER){
                wildCards++;
            } else {
                if (checkAgainst == null){
                    checkAgainst = i;
                } else {
                    if (i > checkAgainst + wildCards + 1){
                        return false;
                    } else {
                        int reduceWildsBy = (i - checkAgainst) - 1;
                        checkAgainst = i;
                        wildCards -= reduceWildsBy;
                    }
                }
            }
        }
        return true;
    }


    private static Deck optionalRearrange(Deck playerHand, Scanner choiceScanner) {
        while (true) {
            System.out.println("\nWould you like to rearrange your cards (y/n)?");
            String playerChoice = choiceScanner.nextLine();
            if (playerChoice.equalsIgnoreCase("y")){
                while (true){
                    System.out.println("""
                            Please input the order you would like your cards in
                            (They are currently in the order: 1-2-3-4-5-6-7)
                            e.g.
                            7-2-1-3-5-4-6""");
                    String cardOrder = choiceScanner.nextLine();
                    try{
                        String[] orderArray = cardOrder.split("-");
                        if (orderArray.length != 7){
                            System.out.println("Order must be exactly seven hyphen-separated integers");
                        } else {
                            Card[] originalOrder = playerHand.seeCards();
                            Deck playerHandCopy = new Deck(playerHand);
                            ArrayList<Card> newOrder = new ArrayList<Card>();
                            for (String i : orderArray){
                                newOrder.add(playerHandCopy.takeCard(originalOrder[7-(Integer.parseInt(i))]));
                            }
                            System.out.println();
                            for (Card c : newOrder){
                                playerHandCopy.addCard(c);
                            }
                            playerHand = playerHandCopy;
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid order");
                    }
                }
                System.out.println("\nYour hand: - - - - - - -");
                playerHand.handPrint();
                break;
            } else if (playerChoice.equalsIgnoreCase("n")){
                break;
            }
            System.out.println("Invalid choice");
        }
        return playerHand;
    }
}
