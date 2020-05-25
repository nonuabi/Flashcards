package Flashcards;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Flash_Cards {
    static Scanner scanner = new Scanner(System.in);

    public static String definition(Map<String, String> result, int i) {
        System.out.printf("The definition of the card #%d:\n", i);
        while (true) {

            String definition = scanner.nextLine();

            if (result.containsValue(definition)) {
                System.out.println("The definition " + "\"" + definition + "\"" + " already exists. Try again:");
            } else {
                return definition;
            }
        }
    }

    public static String card(Map<String, String> result, int i) {
        System.out.printf("The card #%d:\n", i);
        while (true) {

            String card = scanner.nextLine();

            if (result.containsKey(card)) {
                System.out.println("The card " + "\"" + card + "\"" + " already exists. Try again:");
            } else {
                return card;
            }
        }
    }

    public static void print(Map<String, String> result) {
        for (var name : result.keySet()) {
            System.out.println("Print the definition of " +"\"" + name + "\":");
            String answer = scanner.nextLine();
            if (answer.equals(result.get(name))) {
                System.out.println("Correct answer.");
            } else if (result.containsValue(answer)) {
                String card = null;
                for (Map.Entry<String, String> entry : result.entrySet()) {
                    if (answer.equals(entry.getValue())) {
                        card = entry.getKey();
                    }
                }
                //Wrong answer. The correct one is "uncle", you've just written the definition of "a part of the body where the foot and the leg meet".
                System.out.println("Wrong answer. The correct one is " + "\"" + result.get(name) + "\"" + ", you've just written the definition of " + "\"" + card + "\".");
            } else {
                System.out.println("Wrong answer. The correct one is " + "\"" + result.get(name) + "\".");
            }
        }
    }
    public static void action (Map<String, String> result, int num) {
        int i = 1;
        while (num != 0) {
            result.put(card(result, i), definition(result, i));
            i++;
            num--;
        }
    }
    public static void main(String[] args) {
        System.out.println("Input the number of cards : ");
        int num = scanner.nextInt();
        scanner.nextLine();
        Map<String, String> card_def = new LinkedHashMap<>();
        action(card_def,num);
        print(card_def);
    }
}

