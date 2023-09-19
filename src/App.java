import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    // This is a CLI applicataion all interaction will be done in the CLI.
    public static void main(String[] args) {
        Boolean sessionEnd = false;

        // Letting user select encode or decode text.
        // While session have not end, app will keep asking for function to run.
        while (!sessionEnd) { 
            Scanner selectionScanner = new Scanner(System.in);
            System.out.print(
                    "Please select the function you wish to use, (e) for encoder and (d) for Decoder: ");
            String selection = selectionScanner.nextLine();

            // after function have been selected, app will ask for user text to encode/decode before ending the session.
            if (selection.equals("e") | selection.equals("E")) {
                System.out.println("You have chosen 'Encoding'");
                Scanner plainTextScanner = new Scanner(System.in);
                System.out.print("Please key in your text: ");
                String plainText = plainTextScanner.nextLine();
                encoder(plainText);
                sessionEnd = true;
            } else if (selection.equals("d") | selection.equals("D")) {
                System.out.println("You have chosen 'Decoding'");
                Scanner encodedTextScanner = new Scanner(System.in);
                System.out.print("Please key in your text: ");
                String encodedText = encodedTextScanner.nextLine();
                decoder(encodedText);
                sessionEnd = true;
            } else {
                System.out.println("You have input an invalid selection, please try again.");
                continue;
            }

        }

    }

    private static void encoder(String plainText) {
        List<Integer> encodedInt = new ArrayList<>();

        // Check for the charater's ASCII number and adjust accdingly.
        for (int i = 0; i < plainText.length(); i++) {
            if ((int) plainText.charAt(i) >= 41 && (int) plainText.charAt(i) <= 47 |
                (int) plainText.charAt(i) >= 49 && (int) plainText.charAt(i) <= 57 |
                (int) plainText.charAt(i) >= 66 && (int) plainText.charAt(i) <= 90) {
                encodedInt.add((int) plainText.charAt(i) - 1);
            } else if ((int) plainText.charAt(i) == 40) {
                encodedInt.add((int) plainText.charAt(i) + 17);
            } else if ((int) plainText.charAt(i) == 48) {
                encodedInt.add((int) plainText.charAt(i) + 42);
            } else if ((int) plainText.charAt(i) == 65) {
                encodedInt.add((int) plainText.charAt(i) - 18);
            } else {
                encodedInt.add((int) plainText.charAt(i));
            }
        }

        // Build the string and print it out in console.
        StringBuilder result = new StringBuilder();
        for (int encoded : encodedInt) {
            result.append((char) encoded);
        }
        System.out.println("Encoded text: " + result);

    }

    private static void decoder(String encodedText) {
        List<Integer> decodedInt = new ArrayList<>();

        // Check for the charater's ASCII number and adjust accdingly.
        for (int i = 0; i < encodedText.length(); i++) {
            if ((int) encodedText.charAt(i) >= 40 && (int) encodedText.charAt(i) <= 46 |
                (int) encodedText.charAt(i) >= 48 && (int) encodedText.charAt(i) <= 56 |
                (int) encodedText.charAt(i) >= 65 && (int) encodedText.charAt(i) <= 89 ) { 
                decodedInt.add((int) encodedText.charAt(i) + 1);
            } else if ((int) encodedText.charAt(i) == 57) {
                decodedInt.add((int) encodedText.charAt(i) - 17);
            } else if ((int) encodedText.charAt(i) == 90) {
                decodedInt.add((int) encodedText.charAt(i) - 42);
            } else if ((int) encodedText.charAt(i) == 47) {
                decodedInt.add((int) encodedText.charAt(i) + 18);
            } else {
                decodedInt.add((int) encodedText.charAt(i));
            }
        }

        // Build the string and print it out in console.
        StringBuilder result = new StringBuilder();
        for (int encoded : decodedInt) {
            result.append((char) encoded);
        }
        System.out.println("Decoded text: " + result);

    }

}
