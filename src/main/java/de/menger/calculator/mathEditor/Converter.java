package de.menger.calculator.mathEditor;

import java.util.HashMap;
import java.util.Map;

public class Converter {

    private static final HashMap<String, String> symbols = new HashMap<>(Map.ofEntries(
            Map.entry("*", "×"),
            Map.entry("/", "÷")
    ));

    public static String convertUnicodeToAsci(String symbol) {
        for (Map.Entry<String, String> entry : symbols.entrySet()) {
            if (entry.getValue().equals(symbol)) {
                return entry.getKey();
            }
        }
        return symbol;
    }

    public static String convertAsciToUnicode(String symbol) {
        return symbols.getOrDefault(symbol, symbol);
    }
}
