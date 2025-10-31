public class Card {
    private String color;
   
    public Card(String color) {
        this.color = color; // instance variable: card's color
    }

    public String getColor() {
        return color; // accessor methods for other classes
    }

    public boolean isValid(Card currentCard) {
        return this.color.equals(currentCard.getColor()); // this is overwritten in subclasses to be specific to Number and Special Cards
    }

    public void play() {
        System.out.println(getColor()); // prints the card's identity, overidden in subclasses for specificity
    }

    public String toString() {
        return getColor(); //defines how object is printed
    }
}
