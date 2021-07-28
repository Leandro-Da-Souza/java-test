import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        char option = '\0';

        System.out.println("Välkommen till valideringschecken.");
        System.out.println("Här nedan är alternativ som du kan validera:");
        System.out.println();
        System.out.println("A. Personnummer");
        System.out.println("B. Samordningsnummer");
        System.out.println("C. Organisationsnummer");
        System.out.println("D. Avsluta program");

        do {
            System.out.println();
            System.out.println("Välj ett av alternativen: ");
            char option1 = scanner.next().charAt(0);
            option = Character.toUpperCase(option1);
            System.out.println();

            switch (option) {
                case 'A':
                    // Validera personnummer här, returnera true or false
                    System.out.println("Skriv in personnummer");
                    String personNummer = scanner.next();
                    System.out.println();
                    System.out.println(personNummer.length());
                    System.out.println(validityCheck(personNummer));
                    break;
                case 'B':
                    // Validera samordningsnummer här, returnera true or false
                    System.out.println("skriv in samordningsnummer");
                    String samNummer = scanner.next();
                    System.out.println(samNummer);
                    break;
                case 'C':
                    // Validera org nummer här, returnera true or false
                    System.out.println("skriv in organisationsnummer");
                    String orgNummer = scanner.next();
                    System.out.println(orgNummer);
                    break;
                case 'D':
                    // Stäng program
                    break;
                default:
                    System.out.println("Error: vänligen välj ett av alternativen i listan ovanför");
            }

        } while (option != 'D');

        System.out.println("Tack för att du använde valideringschecken!");
        scanner.close();
    }

    private static boolean validityCheck(String input) {
        int[] inputArray = new int[input.length()];

        // generella checks
        if (input.contains("-") || input.contains("+") || input.contains("/")) {
            input = input.replaceAll("[^0-9]", "");
        }

        if (input.length() < 10 || input.length() > 12) {
            return false;
        }

        if (input.length() == 12) {
            input = input.substring(2, input.length());
        }

        System.out.println(input);

        return true;

    }

}
