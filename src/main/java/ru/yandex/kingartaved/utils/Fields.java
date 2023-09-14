package ru.yandex.kingartaved.utils;

import java.util.List;
import java.util.Map;

public class Fields {
    public final static List<String> tokens = List.of("+", "-", "/", "*", "(", ")", "[", "]", ".", ",", "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "^");;
    public final static Map<Character, Character> brackets = Map.of(')', '(', ']', '[');
    public final static  Map<String, Integer> priorities = Map.of("+", 2,"-", 2, "*", 3, "/", 3, "^", 4);


}
