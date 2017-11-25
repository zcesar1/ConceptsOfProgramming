package in.cpl;

import in.cpl.constants.Codes;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Please select one: \"scan\" \"parse\" \"interpret\"");
        String select = scanner.nextLine();
        //File file = new File("projectResources/sclex1.scl");
        File file = new File("projectResources/test_lua.lua");
        String input = null;
        try {
            input = readFile(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lookup tables are here so the scanner can be used to build them out for the parser
        Map<Integer, String> literalLookup = new HashMap<>();
        Map<Integer, String> parameterLookup = new HashMap<>();

        switch (select) { // Ability to only do certain levels of processing
            case "scan":
                scan(input, literalLookup, parameterLookup);
                return;
            case "parse":
                // TODO
                System.err.println("Not implemented yet!");
                return;
            case "interpret":
                // TODO
                System.err.println("Not implemented yet!");
                return;
            default:
                throw new IllegalArgumentException("Expected valid argument! (scan, parse, or interpret)");
        }
    }

    // Build a scanner for the given input, return a list of ParsableTokens from it, and populate the lookup tables
    static List<ParsableToken> scan(
            final String input,
            final Map<Integer, String> literalLookup,
            final Map<Integer, String> parameterLookup) {

        // Make and run a scanner
        LinkedList<Token> tokens = new LinkedList<>();
        Scanner.getScanner(input).forEachRemaining(tokens::add);

        // Display info on all the token scanned
        tokens.forEach(Token::display);

        // Map the Tokens into ParsableTokens (tokens with codes)
        return tokens.stream()
                .map(token -> {
                    // Check to see if we need to use the lookup tables
                    Integer parameterCode = null;
                    Integer literalCode = null;
                    if (TokenType.ID.equals(token.getTokenType())) {
                        parameterCode = parameterLookup.size();
                        parameterLookup.put(parameterCode, token.getLexeme());
                    } else if (TokenType.INTEGER.equals(token.getTokenType())) {
                        literalCode = literalLookup.size();
                        literalLookup.put(literalCode, token.getLexeme());
                    }
                    /*if (TokenType.IDENTIFIER.equals(token.getTokenType())) {
                        parameterCode = parameterLookup.size();
                        parameterLookup.put(parameterCode, token.getLexeme());
                    } else if (TokenType.INTEGER.equals(token.getTokenType())
                            || TokenType.FLOAT.equals(token.getTokenType())
                            || TokenType.STRING.equals(token.getTokenType())) {
                        literalCode = literalLookup.size();
                        literalLookup.put(literalCode, token.getLexeme());
                    } */

                    // Make the new Parsable token
                    return new ParsableToken(
                            Codes.getCodeFromTokenType(token.getTokenType()),
                            parameterCode,
                            literalCode);
                })
                .collect(Collectors.toList());
    }

    // Utility method to read a file with the given path
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }
}
