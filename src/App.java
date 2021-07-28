import java.util.Arrays;
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

                    if (validityCheck(personNummer) == null) {
                        System.out.println("Vänligen ange personnummer på 10 eller 12 siffror");
                        break;
                    } else {
                        luhnAlgorithm(validityCheck(personNummer));
                    }
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

    private static int[] validityCheck(String input) {

        if (input.contains("-") || input.contains("+") || input.contains("/")) {
            input = input.replaceAll("[^0-9]", "");
        }

        if (input.length() < 10 || input.length() > 12) {
            return null;
        }

        if (input.length() == 12) {
            input = input.substring(2, input.length());
        }

        int[] inputArray = new int[input.length()];

        // String[] splited = input.split(" ");

        for (int i = 0; i < input.length(); i++) {
            inputArray[i] = Integer.parseInt(input.substring(i, i + 1));
        }

        return inputArray;
    }

    private static boolean luhnAlgorithm(int[] numbers) {
        // System.out.println(Arrays.toString(numbers));

        for (int i = numbers.length - 2; i >= 0; i = i - 2) {
            int tempValue = numbers[i];
            tempValue = tempValue * 2;
            if (tempValue > 9) {
                tempValue = tempValue % 10 + 1;
            }
            numbers[i] = tempValue;
        }

        int controlNumber = numbers[numbers.length - 1];

        int[] numbersLastIndexRemoved = new int[numbers.length - 1];

        for (int i = 0, j = 0; i < numbers.length; i++) {
            if (i != 9) {
                numbersLastIndexRemoved[j++] = numbers[i];
            }
        }

        int total = 0;

        for (int i = 0; i < numbersLastIndexRemoved.length; i++) {
            total += numbersLastIndexRemoved[i];
        }

        // System.out.println(Arrays.toString(numbersLastIndexRemoved));

        // System.out.println(Arrays.toString(numbers));
        int calculatedControlNumber = (10 - (total % 10)) % 10;

        System.out.println(calculatedControlNumber);
        System.out.println(controlNumber);

        return true;
    }

}
