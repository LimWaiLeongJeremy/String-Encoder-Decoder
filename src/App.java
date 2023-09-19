import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Boolean sessionEnd = false;

        while (!sessionEnd) {
            Scanner selectionScanner = new Scanner(System.in);
            System.out.print(
                    "Please select the function you wish to use, (e) for encoder and (d) for Decoder: ");
            String selection = selectionScanner.nextLine();
            if (selection.equals("e") | selection.equals("E")) {
                Plaintext();
                sessionEnd = true;
            } else if (selection.equals("d") | selection.equals("D")) {
                System.out.println("Decoder");
                sessionEnd = true;
            } else {
                System.out.println("You have input an invalid selection, please try again.");
                continue;
            }

        }

    }

    private static void Plaintext() {
        List<Integer> encoded = new ArrayList<>();
        System.out.println("You have choosen 'Encoding'");
        Scanner plainTextScanner = new Scanner(System.in);
        System.out.print(
                "Please key in your text: ");
        String plainText = plainTextScanner.nextLine();
        for (int i = 0; i < plainText.length(); i++) {
            // System.out.println((int)plainText.charAt(i));
            if (plainText.charAt(i) <= 41 && plainText.charAt(i) >= 47 |
                plainText.charAt(i) <= 49 && plainText.charAt(i) >= 57 |
                plainText.charAt(i) <= 66 && plainText.charAt(i) >= 90) {
                encoded.add(plainText.charAt(i) + 1);

            } else if (plainText.charAt(i) == 40) {
                encoded.add(plainText.charAt(i) + 17);
            } else if (plainText.charAt(i) == 48) {
                encoded.add(plainText.charAt(i) - 18);
            } else if (plainText.charAt(i) == 65) {
                encoded.add(plainText.charAt(i) - 18);
            }
        }
        // return originalText;
    }

}
