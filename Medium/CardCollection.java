import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Card {
    private String rank;
    private String symbol;

    public Card(String rank, String symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public String getRank() {
        return rank;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return rank + " of " + symbol;
    }
}

public class CardCollection {
    private static Map<String, List<Card>> cardCollection = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCardCollection();

        while (true) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Find Cards by Symbol");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    findCardsBySymbol();
                    break;
                case 2:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void initializeCardCollection() {
        String[] symbols = {"Hearts", "Spades", "Diamonds", "Clubs"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String symbol : symbols) {
            List<Card> cards = new ArrayList<>();
            for (String rank : ranks) {
                cards.add(new Card(rank, symbol));
            }
            cardCollection.put(symbol, cards);
        }
    }

    private static void findCardsBySymbol() {
        System.out.print("Enter symbol (e.g., Hearts, Spades, Diamonds, Clubs): ");
        String symbol = scanner.nextLine();

        if (cardCollection.containsKey(symbol)) {
            List<Card> cards = cardCollection.get(symbol);
            System.out.println("Cards in the " + symbol + " suit:");
            for (Card card : cards) {
                System.out.println(card);
            }
        } else {
            System.out.println("No cards found for the symbol: " + symbol);
        }
    }
}
