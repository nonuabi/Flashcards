package Flashcards;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Flash_Cards {
    static Scanner scanner = new Scanner(System.in);
    static String original_file = "capitals.txt";
    static String pathtofile = "D:\\filejava\\capitals.txt";
    //card
    public static String card(Map<String, String> result) {
        System.out.println("The card :");
        while (true) {
            String card = scanner.nextLine();

            if (result.containsKey(card)) {
                System.out.println("The card " + "\"" + card + "\"" + " already exists.");
                return "null";
            } else {
                return card;
            }
        }
    }

    //defination
    public static String definition(Map<String, String> result) {
        System.out.println("The definition of the card:");
        while (true) {

            String definition = scanner.nextLine();

            if (result.containsValue(definition)) {
                System.out.println("The definition " + "\"" + definition + "\"" + " already exists.");
                return "null";
            } else {
                return definition;
            }
        }
    }

    //print
    public static void print(Map<String, String> result) {

    }

    //remove card form the map
    public static void remove (Map<String, String> result) {
        System.out.println("The card:");
        String card = scanner.nextLine();
        if (result.containsKey(card)) {
            result.remove(card);
            System.out.println("The card has been removed.");
        } else {
            System.out.println("Can't remove " + "\"" + card + "\"" + ": there is no such card.");
        }
    }

    //import file into the map
    public static void import_file(Map<String, String> result) {
        try {
            System.out.println("File name:");
            String import_file_name = scanner.nextLine();
            String pathToImportFile = "D:\\filejava\\" + import_file_name;
            int count_card = 0;
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(pathToImportFile));
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length >= 2) {
                    count_card++;
                    String key = parts[0];
                    String value = parts[1];
                    result.put(key, value);
                }
            }
            System.out.println(count_card + " cards have been loaded.");
        } catch (FileNotFoundException fe) {
            System.out.println("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //export map into the file
    public static void export_map(Map<String, String> result) {
        System.out.println("File name:");
        String file_Name = scanner.nextLine();
        int count_cards = 0;
        if (file_Name.equals(original_file)) {
            File file = new File(pathtofile);
            BufferedWriter bf = null;
            try {
                bf = new BufferedWriter(new FileWriter(file));

                for (Map.Entry<String, String> entry : result.entrySet()) {
                    bf.write(entry.getKey() + ":" + entry.getValue());
                    bf.newLine();
                }
                for (Map.Entry<String, String> entry1 : result.entrySet()) {
                    count_cards++;
                }
                System.out.println(count_cards + " cards have been saved.");
                bf.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File not found.");
        }
    }

    //asking random cards definitions
    public static void ask_card(Map<String, String> result) {
        System.out.println("How many times to ask?");
        int num = scanner.nextInt();
        scanner.nextLine();
        Object[] crunchifyKeys = result.keySet().toArray();
        while (num != 0) {
            Object key = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
            System.out.println("Print the definition of " + "\"" + key + "\":");
            String definition = scanner.nextLine();
            if (definition.equals(result.get(key))) {
                System.out.println("Correct answer.");
            } else if (result.containsValue(definition)) {
                String card = null;
                for (Map.Entry<String, String> entry : result.entrySet()) {
                    if (definition.equals(entry.getValue())) {
                        card = entry.getKey();
                    }
                }
                System.out.println("Wrong answer. The correct one is " + "\"" + result.get(key) + "\"" + ", you've just written the definition of " + "\"" + card + "\".");
            } else {
                System.out.println("Wrong answer. The correct one is " + "\"" + result.get(key) + "\".");
            }
            num--;
        }
    }

    //action
    public static void action (Map<String, String> result) {
        while (true) {
            System.out.println();
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String command = scanner.next();
            scanner.nextLine();
            switch (command) {
                case "add":
                    String temp_card = card(result);
                    if (temp_card.equals("null")) {
                        continue;
                    }
                    String temp_def = definition(result);
                    if (temp_def.equals("null")) {
                        continue;
                    }
                    result.put(temp_card, temp_def);
                    System.out.println("The pair (\"" + temp_card + "\":\"" + temp_def + "\") has been added.");
                    break;
                case "remove":
                    remove(result);
                    break;
                case "import":
                    import_file(result);
                    break;
                case "export":
                    export_map(result);
                    break;
                case "ask":
                    ask_card(result);
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    System.exit(0);
                    break;
            }
        }
    }

    //main function
    public static void main(String[] args) {
        Map<String, String> card_def = new LinkedHashMap<>();
        action(card_def);
        print(card_def);
    }
}
