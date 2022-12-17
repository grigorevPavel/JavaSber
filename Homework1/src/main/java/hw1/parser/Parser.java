package hw1.parser;

import java.lang.*;
import java.util.*;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Parser {
    final Path path;
    final HashMap<String, Expression> data;

    public Parser(String file) throws Exception {
        path = Path.of(file);
        data = new HashMap<String, Expression>();
        parse();
    }

    public HashMap<String, Expression> getJson() {
        return data;
    }

    ArrayList<String> tokenize(String data) {
        String regexp = "[^\\\\]\".*[^\\\\]\"\\s*:\\s*((\\[.*\\])|([^\\\\]\".*[^\\\\]?\")|([+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)))";
        Pattern split_pattern = Pattern.compile(regexp);
        Matcher matcher = split_pattern.matcher(data);

        ArrayList tokens = new ArrayList<String>();

        while (matcher.find()) {
            tokens.add(matcher.group().trim());
        }

        return tokens;
    }

    public void parse() throws Exception {
        String info = Files.readString(path);
        ArrayList<String> tokens = tokenize(info);

        for (String entry: tokens) {
            String[] pair = entry.split(":", 2);
            String key = pair[0].trim();
            key = key.substring(1, key.length() - 1);
            this.data.put(key, new Expression(pair[1].trim()));
        }
    }
}
