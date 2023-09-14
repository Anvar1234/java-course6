package ru.yandex.kingartaved.preparator.impl;

import ru.yandex.kingartaved.preparator.Preparatorable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.yandex.kingartaved.utils.Fields.brackets;
import static ru.yandex.kingartaved.utils.Utils.addSpaces;

public class ExpressionPreparator implements Preparatorable {

    private final String expression;

    public ExpressionPreparator(String expression) {
        this.expression = expression;
    }

    @Override
    public String clearSpaces() {
        return this.expression.replaceAll(" ", "").trim();
    }

    /**
     * Метод проверки в пользовательском выражении наличия унарного минуса и его замены.
     */
    @Override
    public List<String> unaryMinusHandler() {

        //получаем чищенное от пробелов выражение из метода этого класса.
        String cleanExpression = clearSpaces();
        //разделяем валидное выражение на токены и операнды с помощью addSpaces и далее сплитуем в список.
        List<String> validExpression = new ArrayList<>(Arrays.asList(addSpaces(cleanExpression).split(" ")));
        List<String> tempArray = new ArrayList<>();

        for (int i = 0; i < validExpression.size(); i++) {
            //если элемент не минус, то добавляем его в выводную коллекцию.
            if (!validExpression.get(i).equals("-")) {
                tempArray.add(validExpression.get(i));
                //иначе если элемент является первым в коллекции (i==0),
                // то в вывод коллекцию добавляем строки 0 и -.
            } else if (i == 0) {
                tempArray.add("0");
                tempArray.add("-");
                //иначе, если элемент "-" не первый, проверяем есть ли перед ним откр скобка, если
                // да, то в вывод коллекцию добавляем строки 0 и -.
            } else if (brackets.containsValue(validExpression.get(i - 1).charAt(0))) {
                tempArray.add("0");
                tempArray.add("-");
                //если минус - это не первый элемент и перед ним нет откр скобки,
                // то добавляем в выводную коллекцию.
            } else tempArray.add("-");
        }
        return tempArray;
    }
}
