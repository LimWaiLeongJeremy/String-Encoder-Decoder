import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// This is a CLI applicataion all interaction will be done in the CLI.
public class App {

    // **** Soluton 1 ****
    static String[] charaters = { "A", "B", "C", "D", "E",
        "F", "G", "H", "I", "J",
        "K", "L", "M", "N", "O",
        "P", "Q", "R", "S", "T",
        "U", "V", "W", "X", "Y",
        "Z", "0", "1", "2", "3",
        "4", "5", "6", "7", "8",
        "9", "(", ")", "*", "+",
        ",", "-", ".", "/" };
    static Map<Integer, String> referenceTable = new HashMap<>();
    static Map<Integer, String> offsetTable = new HashMap<>();
    static int baseOffsetValue = 13;

    public static void main(String[] args) {
        Boolean sessionEnd = false;
        initTable();

        try (Scanner selectionScanner = new Scanner(System.in)) {
            while (!sessionEnd) {
                System.out.print(
                        "Please select the function you wish to use, (e) for encoder and (d) for Decoder: ");
                String selection = selectionScanner.nextLine();

                // after function have been selected, app will ask for user text to
                // encode/decode before ending the session.
                if (selection.equals("e") || selection.equals("E")) {
                    System.out.println("You have chosen 'Encoding'");
                    try (Scanner plainTextScanner = new Scanner(System.in)) {
                        System.out.print("Please key in your text: ");
                        String plainText = plainTextScanner.nextLine();
                        System.out.println("Your encoded text: " + encoder(plainText));
                    }
                    sessionEnd = true;
                } else if (selection.equals("d") || selection.equals("D")) {
                    System.out.println("You have chosen 'Decoding'");
                    try (Scanner encodedTextScanner = new Scanner(System.in)) {
                        System.out.print("Please key in your text: ");
                        String encodedText = encodedTextScanner.nextLine();
                        System.err.println("Your decoded text: " + decoder(encodedText));
                    }
                    sessionEnd = true;
                } else {
                    System.out.println("You have input an invalid selection, please try again.");
                    continue;
                }
            }
        }
    }

    private static void initTable() {
        for (int i = 0; i < charaters.length; i++) {
            referenceTable.put(i, charaters[i]);
            offsetTable.put(i + baseOffsetValue, charaters[i]);
        }

    }

    private static String encoder(String plainText) {
        List<String> encodedText = new ArrayList<>();

        for (int i = 0; i < plainText.length(); i++) {
            char charater = plainText.charAt(i);
            String charAsString = String.valueOf(charater);
            for (Map.Entry<Integer, String> entry : referenceTable.entrySet()) {
                int key = entry.getKey();
                String value = entry.getValue();
                int offsetValue = key + baseOffsetValue;
                if (offsetValue < 0) {
                    offsetValue = referenceTable.size() - baseOffsetValue;
                }
                if (charAsString.contains(value)) {
                    encodedText.add(referenceTable.get(offsetValue));
                    break;
                } else if (key >= 43) {
                    encodedText.add(charAsString);
                }

            }

        }
        // Build the string and print it out in console.
        StringBuilder result = new StringBuilder();
        for (String encoded : encodedText) {
            result.append(encoded);
        }
        return String.valueOf(result);
    }

    private static String decoder(String encodedText) {
        List<String> decodedText = new ArrayList<>();
        // int decoderOffset = baseOffsetValue;

        for (int i = 0; i < encodedText.length(); i++) {
            char charater = encodedText.charAt(i);
            String charAsString = String.valueOf(charater);
            for (Map.Entry<Integer, String> entry : offsetTable.entrySet()) {
                int key = entry.getKey();
                String value = entry.getValue();
                int offsetValue = key - baseOffsetValue;
                if (offsetValue > 43) {
                    offsetValue = 0 + baseOffsetValue;
                }
                if (charAsString.contains(value)) {
                    decodedText.add(offsetTable.get(offsetValue));
                    break;
                } else if (key >= 43 + baseOffsetValue) {
                    decodedText.add(charAsString);
                }

            }

        }
        // Build the string and print it out in console.
        StringBuilder result = new StringBuilder();
        for (String encoded : decodedText) {
            result.append(encoded);
        }
        return String.valueOf(result);
    }



    // // **** Solution 2 ****
    // public static void main(String[] args) {
    //     Boolean sessionEnd = false;

    //     // Letting user select encode or decode text.
    //     // While session have not end, app will keep asking for function to run.
    //     try (Scanner selectionScanner = new Scanner(System.in)) {
    //         while (!sessionEnd) {
    //             System.out.print(
    //                     "Please select the function you wish to use, (e) for encoder and (d) for Decoder: ");
    //             String selection = selectionScanner.nextLine();

    //             // after function have been selected, app will ask for user text to
    //             // encode/decode before ending the session.
    //             if (selection.equals("e") | selection.equals("E")) {
    //                 System.out.println("You have chosen 'Encoding'");
    //                 try (Scanner plainTextScanner = new Scanner(System.in)) {
    //                     System.out.print("Please key in your text: ");
    //                     String plainText = plainTextScanner.nextLine();
    //                     encoder(plainText);
    //                 }
    //                 sessionEnd = true;
    //             } else if (selection.equals("d") | selection.equals("D")) {
    //                 System.out.println("You have chosen 'Decoding'");
    //                 try (Scanner encodedTextScanner = new Scanner(System.in)) {
    //                     System.out.print("Please key in your text: ");
    //                     String encodedText = encodedTextScanner.nextLine();
    //                     decoder(encodedText);
    //                 }
    //                 sessionEnd = true;
    //             } else {
    //                 System.out.println("You have input an invalid selection, please try again.");
    //                 continue;
    //             }
    //         }

    //     }

    // }

    // private static void encoder(String plainText) {
    //     List<Integer> encodedInt = new ArrayList<>();

    //     // Offset value B
    //     // Check for the charater's ASCII number and adjust accdingly.
    //     for (int i = 0; i < plainText.length(); i++) {
    //         if ((int) plainText.charAt(i) >= 41 && (int) plainText.charAt(i) <= 47 |
    //                 (int) plainText.charAt(i) >= 49 && (int) plainText.charAt(i) <= 57 |
    //                         (int) plainText.charAt(i) >= 66
    //                 && (int) plainText.charAt(i) <= 90) {
    //             encodedInt.add((int) plainText.charAt(i) - 1);
    //         } else if ((int) plainText.charAt(i) == 40) {
    //             encodedInt.add((int) plainText.charAt(i) + 17);
    //         } else if ((int) plainText.charAt(i) == 48) {
    //             encodedInt.add((int) plainText.charAt(i) + 42);
    //         } else if ((int) plainText.charAt(i) == 65) {
    //             encodedInt.add((int) plainText.charAt(i) - 18);
    //         } else {
    //             encodedInt.add((int) plainText.charAt(i));
    //         }
    //     }

    //     // Build the string and print it out in console.
    //     StringBuilder result = new StringBuilder();
    //     for (int encoded : encodedInt) {
    //         result.append((char) encoded);
    //     }
    //     System.out.println("Encoded text: " + result);

    // }

    // private static void decoder(String encodedText) {
    //     List<Integer> decodedInt = new ArrayList<>();

    //     // Offset value B
    //     // Check for the charater's ASCII number and adjust accdingly.
    //     for (int i = 0; i < encodedText.length(); i++) {
    //         if ((int) encodedText.charAt(i) >= 40 && (int) encodedText.charAt(i) <= 46 |
    //                 (int) encodedText.charAt(i) >= 48 && (int) encodedText.charAt(i) <= 56 |
    //                         (int) encodedText.charAt(i) >= 65
    //                 && (int) encodedText.charAt(i) <= 89) {
    //             decodedInt.add((int) encodedText.charAt(i) + 1);
    //         } else if ((int) encodedText.charAt(i) == 57) {
    //             decodedInt.add((int) encodedText.charAt(i) - 17);
    //         } else if ((int) encodedText.charAt(i) == 90) {
    //             decodedInt.add((int) encodedText.charAt(i) - 42);
    //         } else if ((int) encodedText.charAt(i) == 47) {
    //             decodedInt.add((int) encodedText.charAt(i) + 18);
    //         } else {
    //             decodedInt.add((int) encodedText.charAt(i));
    //         }
    //     }

    //     // Build the string and print it out in console.
    //     StringBuilder result = new StringBuilder();
    //     for (int encoded : decodedInt) {
    //         result.append((char) encoded);
    //     }
    //     System.out.println("Decoded text: " + result);

    // }

}
