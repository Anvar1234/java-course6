package ru.yandex.kingartaved.service.impl;


import ru.yandex.kingartaved.converter.Converterable;
import ru.yandex.kingartaved.service.Serviceable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static ru.yandex.kingartaved.utils.Utils.isNumber;

/**
 * Высчитывает ОПН.
 */
public class ExpressionService implements Serviceable {

    private final List<String> collectionAfterConversation;

    public ExpressionService(Converterable converterable) {

        this.collectionAfterConversation = converterable.convertToPostfix();
    }

    /**
     * Публичный результирующий метод для получения результата расчета пользовательского выражения.
     */
    @Override
    public Deque<Double> resultDequeAfterCalculation() {
        return calculatePostfixNotation();
    } // здесь оставил этот прокладочный метод, нежели в MathExpressionValidator типа АррауАфтерВалидейшн, как правильно?


    /**
     * Приватный метод для получения результата расчета пользовательского выражения.
     */
    private Deque<Double> calculatePostfixNotation() { //Обработка постфиксного выражения

//        List<String> validExpressionAfterConversation = collectionAfterConversation;

        Deque<Double> resultStack = new ArrayDeque<>();
        double result;

        for (String element : collectionAfterConversation) {
            //Условие "Если элемент массива число, то перевод в дабл и пушим в стек"
            if (isNumber(element)) resultStack.push(Double.parseDouble(element));
            else {

                switch (element) {
                    case "*" -> {
                        result = resultStack.pop() * resultStack.pop();
                        resultStack.push(result);
                    }
                    case "/" -> {
                        double division = resultStack.pop();
                        result = resultStack.pop() / division;
                        resultStack.push(result);
                    }
                    case "-" -> {
                        result = -resultStack.pop() + resultStack.pop();
                        resultStack.push(result);
                    }
                    case "+" -> {
                        result = resultStack.pop() + resultStack.pop();
                        resultStack.push(result);
                    }
                    case "^" -> {
                        double exponentiation = resultStack.pop();
                        result = exponentiation * exponentiation;
                        resultStack.push(result);
                    }
                    default -> System.out.println("Alarmus, that's not the operator!");
                }
            }
        }
        return resultStack;
    }


}
