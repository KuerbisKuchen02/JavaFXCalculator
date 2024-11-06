package de.menger.calculator.mathEditor;

import java.util.HashMap;
import java.util.Map;

public class Converter {

    private static final HashMap<String, String> symbols = new HashMap<>(Map.ofEntries(
            Map.entry("*", "×"),
            Map.entry("/", "÷"),
            Map.entry("root", "√"),
            Map.entry("pi", "π")
    ));

    public static String convertUnicodeToAsci(String symbol) {
        for (Map.Entry<String, String> entry : symbols.entrySet()) {
            symbol = symbol.replace(entry.getValue(), entry.getKey());

        }
        return symbol;
    }

    public static String convertAsciToUnicode(String symbol) {
        for (Map.Entry<String, String> entry : symbols.entrySet()) {
            symbol = symbol.replace(entry.getKey(), entry.getValue());

        }
        return symbol;
    }
}
