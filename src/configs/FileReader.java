package configs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import enums.LanguageKeywords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    private Scanner scanner;
    private Map<String, ArrayList<String>> aplicationConfig;

    public FileReader(String fileName) throws FileNotFoundException {
        this.scanner = new Scanner(new File(fileName));
        this.aplicationConfig = new HashMap<String, ArrayList<String>>();
    }

    public Map<String, ArrayList<String>> Read() {

        try {
            while (this.scanner.hasNextLine()) {
                String nextLine = this.scanner.nextLine();
                String identifier = nextLine.replaceAll("\t", " ").split(" ")[0];
                if (this.aplicationConfig.get(LanguageKeywords.STATES.toString()) != null) {
                    if (this.aplicationConfig.get(LanguageKeywords.STATES.toString()).contains(identifier)) {
                        identifier = nextLine.split("\t")[0];
                        this.aplicationConfig.put(identifier,
                                new ArrayList<String>(new ArrayList<String>(
                                        Arrays.asList(nextLine.split("\t")).subList(1, nextLine.split("\t").length))));
                        continue;
                    }
                }
                this.stateIdentifier(nextLine.replaceAll("\t", " "), identifier);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return this.aplicationConfig;

    }

    private void stateIdentifier(String line, String identifier) {
        LanguageKeywords keywords;
        keywords = LanguageKeywords.fromString(identifier);
        switch (keywords) {
            case STATES:
                this.aplicationConfig.put(LanguageKeywords.STATES.toString(),
                        new ArrayList<String>(Arrays.asList(parseLine(line))));
                break;
            case INPUT_ALPHABET:

                this.aplicationConfig.put(LanguageKeywords.INPUT_ALPHABET.toString(),
                        new ArrayList<String>(Arrays.asList(parseLine(line))));
                break;
            case TAPE_ALPHABET:
                this.aplicationConfig.put(LanguageKeywords.TAPE_ALPHABET.toString(),
                        new ArrayList<String>(Arrays.asList(parseLine(line))));
                break;

            case TRANSACTION_FUNCTION:
                this.aplicationConfig.put(LanguageKeywords.TRANSACTION_FUNCTION.toString(),
                        new ArrayList<String>(
                                new ArrayList<String>(Arrays.asList(line.split(" "))).subList(1,
                                        line.split(" ").length)));
                break;
            case INITIAL_STATE:
                this.aplicationConfig.put(LanguageKeywords.INITIAL_STATE.toString(),
                        new ArrayList<String>(Arrays.asList(
                                line.split("=")[1].trim())));
                break;
            default:
                break;

        }
    }

    private String[] parseLine(String line) {
        String[] result = line.split("=")[1].replace("{", "").replace("}", "").split(",");
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i].trim();
        }
        return result;
    }

}
