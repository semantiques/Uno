public class NumberCard extends Card {
   private int number; // instance variable unique to NumberCard subclass
   
   public NumberCard(String color, int number) {
      super(color); // inheritance!
      this.number = number; }
  
  public int getNumber(){
     return number;} // acessor methods
     
  public boolean isValid(Card currentCard) {
    return this.getColor().equals(currentCard.getColor()) ||
           (currentCard instanceof NumberCard && 
            this.number == ((NumberCard) currentCard).getNumber());} /* checks if the card can be played
            against the current card, checks both color and # */
            
  public void play() {
    System.out.println(getColor() + " " + getNumber());} // formats the card into text for when it's played
}