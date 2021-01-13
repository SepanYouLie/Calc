package com.mycompany.calc2021;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author i.grigorev
 */
public class Parse 
{
    protected String inStr;
    	
        /*Конструктор класса. Получение введённой строки*/
        public Parse(String str)
	{
            str = str.replaceAll(" ", "");
            this.inStr = str;
        }

        /*Метод, который делает всё. Сначала хотел поделить на большее количество методов, но не было достаточно времени*/
        /*Изначально разбор выражения планировал сделать, через Scanner. Наверное было бы быстрее и меньше кода, но решил немного 
        использовать регулярные выражения.*/
        public String get()
	{
            /*Массив возможных римских чисел.*/
            String[] rim = {"0","I","II","III","IV","V","VI","VII","VIII","IX","X","XI","XII","XIII","XIV","XV","XVI","XVII","XVIII","XIX","XX","XXI","","","XXIV","XXV",""
            ,"XXVII","XXVIII","","XXX","","XXXII","","","XXXV","XXXVI","","","","XL","","XLII","","","XLV","","","XLVIII","IL","L","","","","LIV","","LVI","",""
            ,"","LX","","","LXIII","LXIV","","","","","","LXX","","LXXII","","","","","","","","LXXX","LXXXI","","","","","","","","","XC","","","","","","",""
            ,"","","C"};
            /*Массив арабских цифр.*/
            String[] arab = {"0","1","2","3","4","5","6","7","8","9","10"};
            /*Паттерн для поиска оператора в выражении*/
            String regEx = "\\+|\\-|\\*|\\/";
            String inStr,outStr; //переменные для работы со входящей строкой и для формирование строки с результатом
            String sign="",note=""; // В sign будет храниться оператор, в note при необходимости пишется предупреждение о дробном результате
            String[] args = new String[2]; //В этом массиве будут храниться аргументы 1 и 2
		
            int i=0, arg1 = 0,arg2 = 0,result=0; 
            
            int type=4; //переменная, для контролирования процесса: 4 - неверные аргументы, 3 - числа из разных систем, 6 - римские, 0 - арабские
            inStr=this.inStr;
            
            /*Здесь получаем аргументы в строковом виде*/            
            Pattern pattern = Pattern.compile(regEx);
            for (String tempstr : pattern.split(inStr)) 
                {
                    args[i] = tempstr;
                    i++;
                }
            
            /*Получаем и записываем опрератор, если он есть из заданных. Если нет, то сразу формируем финальное сообщение и игнорируем всё остальное*/
            Matcher matcher;
            matcher = pattern.matcher(inStr);
            if(!matcher.find())
                {outStr = "Оператор неизвестен или отсутствует";}
            else
            {  
                sign = inStr.substring(matcher.start(),matcher.end());
                
                /*Проверяем аргументы на принадлежность к типам цифр(римские, арабские), сопоставляем им переменную int для дальнейших вычислений,
                и формируем переменную type, по которой сможем определять дальнейшие дествия*/
                for (int x=1;x<11;x++)
                    {
                        if(args[0].equals(rim[x])){arg1 = x; type++;} else if(args[0].equals(arab[x])){arg1 = x; type = type-2;}
                        if(args[1].equals(rim[x])){arg2 = x; type++;} else if(args[1].equals(arab[x])){arg2 = x; type = type-2;}
                    }
            
                if ((type == 4)||(type == 2)||(type == 5)){outStr = "Неверные аргументы";}
                else if ((type == 3)){outStr = "Аргументы из разных систем";}
                else
                
                {
                    /*Здесь производим вычисление выражения. Делаем комментарий, на случай дробного результата деления*/
                    switch (sign)
                    {
                        case "+" -> result = arg1+arg2;
                        case "-" -> result = arg1-arg2;
                        case "*" -> result = arg1*arg2;
                        case "/" -> {
                            if(arg1%arg2 == 0){result = arg1/arg2;}
                            else {result = arg1/arg2; note="Дробные результаты округляются в сторону меньшего целого числа. ";}
                        }
                         default -> {
                        }
                    }
                    
                    /*Здесь приводим ответ к "римскому" виду, если на входе были римские цифры*/
                    if (type == 6)
                    {
                        if(result<0)
                        {
                            result = -result;
                            outStr = note+"Ответ: -"+rim[result]; //добавляем минус, если ответ отрицательный и берём из словаря-массива подходящее римское число
                        } 
                        else
                            outStr = note+"Ответ: "+rim[result];
                    }
                    else outStr = note+"Ответ: "+result;
                }
            }
            return outStr; //Возвращаем строку с ответом.
	}
}
