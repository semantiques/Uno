public class SpecialCard extends Card {
   private String action; // unique instance variable

   public SpecialCard(String color, String action) {
      super(color); // inheritance!
      this.action = action;}

   public String getAction() {
      return action;} // accesor method

   public boolean isValid(Card currentCard) {
      return this.getColor().equals(currentCard.getColor()) ||
             (currentCard instanceof SpecialCard &&
              this.action.equals(((SpecialCard) currentCard).getAction()));} /* checks if
              the card getting played is valid against the current card, checks both color
              and action */

   public void play() { // formats the card getting played into text
      System.out.println(getColor() + " " + getAction());}
}