import java.util.ArrayList;
import java.util.Collections;

public class Deck {
   private ArrayList<Card> cards; // initializes an ArrayList for card which will contain all Uno cards
   
   public Deck(){
      cards = new ArrayList<Card>();
      String[] colors = {"Red", "Blue", "Green", "Yellow"};
      for (String color: colors) {
         for (int i = 0; i <= 9; i++){
           cards.add(new NumberCard(color, i));} // populates cards with 0-9 for each color
         
      cards.add(new SpecialCard(color, "Skip"));
      cards.add(new SpecialCard(color, "+2"));} // populates cards with one special card for each color
      
      Collections.shuffle(cards);} // shuffles cards -- gave up on using Math.random, so did my own research
      
  public Card drawCard() {
  
   if (cards.isEmpty()) {
        System.out.println("Empty deck!");
        return null; /* ran into a scenario where the deck runs out, did this to avoid IndexOutOfBounds
        error but game would have to be expanded more to circumvent this */
    }
    return cards.remove(0);} // removes the card from the draw pile so it can be added to a hand
   
 }
      