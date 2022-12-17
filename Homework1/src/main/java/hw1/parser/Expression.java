package hw1.parser;

import lombok.var;

import java.lang.*;
import java.util.*;
import java.util.regex.*;

public class Expression {
    final Type type;
    final Object object;

    public Expression(String expr) {
        type = Type.defineType(expr);
        object = type.fromString(expr);
    }

    public Type getType() {
        return type;
    }

    public Object extract() {
        return object;
    }

    public enum Type {
        STRING {
            public Object fromString(String expression) {
                return expression.substring(1, expression.length() - 1);
            }
        },

        ARRAY {
            public Object fromString(String expression) {
                String pattern = "(?<=(\\[|,))\\s*((\\d+)|(\\\"[^(\\\")]*\\\")|(\\[[^\\[\\]]*\\]))\\s*(?=(\\]|,))";
                Pattern split_pattern = Pattern.compile(pattern);
                Matcher matcher = split_pattern.matcher(expression);
                ArrayList<Expression> tokens = new ArrayList<>();

                while ((matcher).find()) {
                    Expression item = new Expression(matcher.group().trim());
                    tokens.add(item);
                }

                return tokens;
            }
        },

        NUMBER {
            public Object fromString(String expression) {
                return Double.parseDouble(expression);
            }
        };

        public abstract Object fromString(String expression);

        public static Type defineType(String expr) {
            if (expr.startsWith("\"") || expr.startsWith("\\\"")) {
                return STRING;
            }

            if (expr.startsWith("["))
            {
                return ARRAY;
            }

            return NUMBER;
        }
    }
}

