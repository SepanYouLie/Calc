package com.mycompany.calc2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author i.grigorev
 */

/*Класс для ввода выражения и вызова метода get класса Parse.*/
public class Calc 
{
    public static void main(String[] args) throws IOException 
    {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите выражение:");     
        Parse parse = new Parse(reader.readLine());
	System.out.println(parse.get());     
    }
}
