package ru.yandex.kingartaved;

import ru.yandex.kingartaved.converter.Converterable;
import ru.yandex.kingartaved.converter.impl.ReversePolandNotationConverter;
import ru.yandex.kingartaved.preparator.Preparatorable;
import ru.yandex.kingartaved.preparator.impl.ExpressionPreparator;
import ru.yandex.kingartaved.service.Serviceable;
import ru.yandex.kingartaved.service.impl.ExpressionService;
import ru.yandex.kingartaved.validator.Validatorable;
import ru.yandex.kingartaved.validator.impl.MathExpressionValidator;

import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //примеры выражений и ОПН выражения:
        System.out.println("-3*1*2^+5/2 =     [0, 1, -, 3, *, 1, *, 2, ^, *, 5, 2, /, +]  = -9.5");
        System.out.println("-(-1-(1+2)) =     [0, 1, -, 0, 1, -, 1, *, 1, 2, +, -, *]     = 4.0");
        System.out.println("1-(1+2)-3+4-5*7 = [1, 1, 2, +, -, 3, -, 4, +, 5, 7, *, -]");
        System.out.println("-3+(1-(1+2)) =    [0, 1, -, 3, *, 1, 1, 2, +, -, +]");
        System.out.println("1*(2-(3-4)) =     [1, 2, 3, 4, -, -, *]");
        System.out.println("3+1*2^+5 =        [3, 1, 2, ^, *, +, 5, +]");
        System.out.println("( -1+ 5/2+3^) =   [0, 1, -, 5, 2, /, +, 3, ^, +]");
        System.out.println("-1+ 5/2+3^ =      [0, 1, -, 5, 2, /, +, 3, ^, +]");

        //присваиваем пользовательский ввод переменной inputExpression.
        String inputExpression = prompt();

        Preparatorable preparatorable = new ExpressionPreparator(inputExpression);
        Validatorable mathExpressionValidator = new MathExpressionValidator(preparatorable);
        if (mathExpressionValidator.isExpressionValid()){
            System.out.println("Валидация пройдена");
        }
        List<String> resultArrayAfterPreparation = preparatorable.unaryMinusHandler();
        System.out.println("После препаратора:\n" + resultArrayAfterPreparation);

        //Загоняем выражение, прошедшее проверку и преобразование в ОПН-конвертер.
        Converterable reversePolandNotationConverter = new ReversePolandNotationConverter(preparatorable);
        List<String> resultArrayAfterConversation = reversePolandNotationConverter.convertToPostfix();
        System.out.println("ОПН:\n" + resultArrayAfterConversation);

        //Загоняем ОПН-выражение в калькулятор, который высчитывает ОПН.
        Serviceable expressionService = new ExpressionService(reversePolandNotationConverter);
        Deque<Double> finalResultArray = expressionService.resultDequeAfterCalculation();

        //Выводим результат работы калькулятора.
        System.out.println("Результат:\n" + finalResultArray);


    }


    //    private static void start(){
//        while (true){
//            prompt();
//        }
//    }
    private static String prompt() {
        System.out.print("Введите выражение: ");
        return sc.nextLine();
    }
}

