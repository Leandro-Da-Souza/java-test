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
                        if (luhnAlgorithm(validityCheck(personNummer))) {
                            System.out.println("Personnumret är validerad och giltig");
                        } else {
                            System.out.println("Personnumret är ogiltigt");
                        }
                    }
                    break;
                case 'B':
                    // Validera samordningsnummer här, returnera true or false
                    System.out.println("skriv in samordningsnummer");
                    String samNummer = scanner.next();
                    if (validityCheck(samNummer) == null) {
                        System.out.println("vänligen ange ett samordningsnummer på 10 eller 12 siffror");
                    } else {
                        if (checkSamordning(validityCheck(samNummer))) {
                            System.out.println("samordningsnumret är giltigt");
                            break;
                        } else {
                            System.out.println("samordnings numret är ogiltigt");
                            break;
                        }
                    }
                    break;
                case 'C':
                    // Validera org nummer här, returnera true or false
                    System.out.println("skriv in organisationsnummer");
                    String orgNummer = scanner.next();

                    if (validityCheck(orgNummer) == null) {
                        System.out.println("vänligen ange ett samordningsnummer på 10 eller 12 siffror");
                    } else {
                        if (checkOrganisationsnummer(validityCheck(orgNummer))) {
                            System.out.println("organisationsnumret är giltigt");
                            break;
                        } else {
                            System.out.println("organisationsnumret är ogiltigt");
                            break;
                        }
                    }
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

        for (int i = 0; i < input.length(); i++) {
            inputArray[i] = Integer.parseInt(input.substring(i, i + 1));
        }

        return inputArray;
    }

    private static boolean luhnAlgorithm(int[] numbers) {

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

        int calculatedControlNumber = (10 - (total % 10)) % 10;

        if (calculatedControlNumber == controlNumber) {

            return true;
        } else {
            return false;
        }
    }

    private static boolean checkSamordning(int[] input) {
        // System.out.println(Arrays.toString(input));
        int x = input[4];

        if (x < 6 || x > 9) {
            return false;
        }

        if (luhnAlgorithm(input)) {
            return true;
        } else {
            return false;
        }

    }

    private static boolean checkOrganisationsnummer(int[] input) {
        int x = input[3];
        if (x < 2) {
            return false;
        }

        if (luhnAlgorithm(input)) {
            return true;
        } else {
            return false;
        }

    }

}
