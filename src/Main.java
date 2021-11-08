import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String secretCode;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");

        String size_code = scanner.next();

        int codeSize = 0;

        if (String.valueOf(size_code).matches("\\d+")) {

            codeSize = Integer.parseInt(size_code);

        } else {

            System.out.println("Error: " + size_code + " isn't a valid number");

            System.exit(0);

        }

        System.out.println("Input the number of possible symbols in the code:");

        String nbrSymbols = scanner.next();

        int symbols = 0;

        if (String.valueOf(nbrSymbols).matches("\\d+")) {

            symbols = Integer.parseInt(nbrSymbols);

        } else {

            System.out.println("Error: " + nbrSymbols + " isn't a valid number");

            System.exit(0);

        }

        if (symbols > 36) {

            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");

            System.exit(0);

        }

        if (codeSize > symbols || codeSize == 0) {

            System.out.printf("Error: it's not possible to generate a code with a length of %d " +
                    "with %d unique symbols.", codeSize, symbols);

            System.exit(0);

        }

        ArrayList<String> lettersDigits = new ArrayList<>(List.of("0123456789".split("")));

        String[] arrLetters = "abcdefghijklmnopqrstuvwxyz".split("");


        if (symbols <= 10) {

            secretCode = generateCode(codeSize);

            System.out.println("*".repeat(codeSize) + " (0-9).");

        } else {

            int symbolsRange = symbols - 10;

            lettersDigits.addAll(Arrays.asList(arrLetters).subList(0, symbolsRange));

            StringBuilder codeMix = new StringBuilder();

            while (codeMix.length() != codeSize) {

                String letterDigit = lettersDigits.toArray()[(int) (Math.random() * symbols)].toString();

                if (!String.valueOf(codeMix).contains(letterDigit)) {

                    codeMix.append(letterDigit);

                }

            }

            secretCode = codeMix.toString();

            if (symbols == 11) {

                System.out.println("*".repeat(codeSize) + " (0-9, a).");

            } else {

                System.out.printf("*".repeat(codeSize) + " (0-9, a-%s).", lettersDigits.get(lettersDigits.size() - 1));
            }

        }

        System.out.println("\nOkay, let's start a game!");

        int turnCounter = 0;

        while (true) {

            turnCounter++;

            System.out.println("Turn " + turnCounter + ":");

            String number = scanner.next();

            int cows = 0, bulls = 0;

            for (int i = 0; i < number.length(); i++) {

                if (number.equals(secretCode)) {

                    bulls = secretCode.length() - 1;

                }

                if (number.charAt(i) == secretCode.charAt(i)) {

                    bulls++;

                } else if (number.contains(secretCode.charAt(i) + "")) {

                    cows++;
                }

            }

            if (bulls == secretCode.length()) {

                System.out.printf("Grade: %d bulls", bulls);

                System.out.println();

                System.out.println("Congratulations! You guessed the secret code.");

                break;

            } else {

                System.out.printf("Grade: %d bull(s) and %d cow(s)", bulls, cows);

                System.out.println();
            }

        }

    }


    private static String generateCode(int sizeCode) {

        StringBuilder codeStr = new StringBuilder();

        while (codeStr.length() != sizeCode) {

            int digit = (int) (Math.random() * 10);

            if (!String.valueOf(codeStr).contains(String.valueOf(digit))) {

                codeStr.append(digit);

            }

        }

        return codeStr.toString();

    }

}
