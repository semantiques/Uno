import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand; // each player has a hand

    public Player(Deck deck) {
        hand = new ArrayList<Card>();
        for (int i = 0; i < 6; i++) {
            hand.add(deck.drawCard()); // deals 5 cards to players
        }
    }

    public int handSize() { // helps with iteration over a hand, like printing a hand for user
        return hand.size();
    }

    public void drawCard(Deck deck) {
        hand.add(deck.drawCard()); // adds a card to the hand from the deck when a player draws a card
    }

    public Card playCard(int index) { // removes a card from the hand when it is played
        return hand.remove(index);
    }

    public Card getCard(int index) { // returns the card at a given index of hand
        return hand.get(index);
    }

    public void showHand() { // prints the player's hand in a user-friendly format
        System.out.println("\n*** YOUR HAND ***");
        for (int i = 0; i < hand.size(); i++) {
            System.out.print(i + ": ");
            hand.get(i).play();
        }
    }
}
