package plugins.excavations.util;

import net.md_5.bungee.api.ChatColor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Colorize {

    // Regex pattern for hex colors like &#00FFAA
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");

    /**
     * Translates color codes in a string, including hex (&#RRGGBB) and legacy (&a, &b, etc.).
     *
     * @param text the text to colorize
     * @return colorized string
     */
    public static String colorize(String text) {
        if (text == null) return null;

        // Handle hex colors first
        Matcher matcher = HEX_PATTERN.matcher(text);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String hexColor = matcher.group(1);
            ChatColor color = ChatColor.of("#" + hexColor);
            matcher.appendReplacement(buffer, color.toString());
        }

        matcher.appendTail(buffer);

        // Handle legacy & color codes
        return ChatColor.translateAlternateColorCodes('&', buffer.toString());
    }

    /**
     * Colorizes a list of strings (useful for lore).
     *
     * @param lines the list of lines to colorize
     * @return colorized lines
     */
    public static java.util.List<String> colorize(java.util.List<String> lines) {
        java.util.List<String> colored = new java.util.ArrayList<>();
        for (String line : lines) {
            colored.add(colorize(line));
        }
        return colored;
    }
}
