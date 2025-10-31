import java.util.Scanner;

public class UnoRunner {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Deck deck = new Deck(); // refresh deck

        System.out.print("Number of players? "); // asks user how many players in the game
        int numberOfPlayers = input.nextInt();
        Player[] players = new Player[numberOfPlayers]; // creates Player objects for each player

        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(deck); // creates a hand for each player
        }

        Card currentCard = deck.drawCard(); // currentCard is the top card of a pile, this is the first card of the game
        System.out.println("Starting card: ");
        currentCard.play();

        int turn = 0; // turn keeps track of whose turn it is, user is player one but index 0
        boolean gameEnded = false; // game will end when a player has an empty hand

        while (!gameEnded) { // while the game is still running
            Player currentPlayer = players[turn];
            System.out.println("\nPlayer " + (turn + 1) + "'s turn");

            if (turn == 0) {
                currentCard = playerTurn(currentPlayer, currentCard, deck, input); // first player is the user
            } else {
                currentCard = aiTurn(currentPlayer, currentCard, deck, turn); // other turns are basic AI
            }

            if (currentPlayer.handSize() == 0) { // ends when the current player's hand is empty
                if (turn == 0) {
                    System.out.println("\nYOU WIN!!!");
                } else {
                    System.out.println("\nAI Player " + (turn + 1) + " wins!");
                }
                gameEnded = true; // ends !gameEnded while loop
            } else {
                boolean skip = false;
                boolean drawTwo = false;

                if (currentCard instanceof SpecialCard) { // need special procedures for special cards
                    String action = ((SpecialCard) currentCard).getAction();

                    if (action.equals("Skip")) {
                        skip = true;
                        int next = (turn + 1) % numberOfPlayers;
                        System.out.println("Player " + (next + 1) + "'s turn is skipped.");
                        turn = (turn + 1) % numberOfPlayers; // Skip the next player's turn
                    }

                    if (action.equals("+2")) {
                        drawTwo = true;
                        int next = (turn + 1) % numberOfPlayers;
                        players[next].drawCard(deck);
                        players[next].drawCard(deck);
                        System.out.println("Player " + (next + 1) + " draws 2 cards."); // draws two cards
                    }
                }

                if (!skip) {
                    turn = (turn + 1) % numberOfPlayers; // normal or +2 still moves to next player
                }
            }
        }

        input.close(); // close scanner
    }

    public static Card playerTurn(Player player, Card currentCard, Deck deck, Scanner input) {
        player.showHand(); // shows the user their hand
        System.out.print("Pick a card, any card (or enter -1 to draw a card)! ");
        int choice = input.nextInt(); // has user pick from their hand

        if (choice == -1) { // draws a card if input is -1
            player.drawCard(deck);
            System.out.println("You drew a card!");
            return currentCard;
        }

        if (choice >= 0 && choice < player.handSize()) {
            Card selected = player.getCard(choice);
            if (selected.isValid(currentCard)) { // checks if the card isValid
                Card played = player.playCard(choice);
                System.out.println("You played: ");
                played.play();
                return played;
            } else {
                System.out.println("Invalid card! Try again.");
                return playerTurn(player, currentCard, deck, input); // recursion if card is invalid!
            }
        } else {
            System.out.println("Invalid index! Try again.");
            return playerTurn(player, currentCard, deck, input); // recursion if input isn't an index!
        }
    }

    public static Card aiTurn(Player ai, Card currentCard, Deck deck, int aiTurn) {
        for (int i = 0; i < ai.handSize(); i++) {
            Card candidate = ai.getCard(i);
            // AI turns just iterate through the hand until they find a valid card to play
            if (candidate.isValid(currentCard)) {
                Card played = ai.playCard(i);
                System.out.println("AI Player " + (aiTurn + 1) + " played:");
                played.play();
                return played;
            }
        }
        ai.drawCard(deck);
        System.out.println("AI Player " + (aiTurn + 1) + " drew a card.");
        return currentCard;
    }
}
