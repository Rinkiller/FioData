

import java.io.IOException;
import java.util.Scanner;


public class inputFIODate {

    
    public static void main(String[] args) { 
        HandlerFioDates  handler = new HandlerFioDates();
        boolean end = false;
        Scanner scaner = new Scanner(System.in,"cp866");
        while(! end){
            System.out.println();
            System.out.println("                Вас приветствует Обработчик!!!");
            System.out.println(" Он позволяет сохранять данные о пользователях  в отдельных файлах");
            System.out.println("1 - ввести данные пользователя");
            System.out.println("2 - выйти из обработчика");
            System.out.print("Ваш выбор: ");
            String str = scaner.nextLine();
            if(str.equals("1")){
                try {
                    FioDate fioDate = handler.handler(handler.inputFioDate());
                    FileWriterOfFioData writerOfFioData = new FileWriterOfFioData();
                    try {
                        writerOfFioData.FileWriterOfFioData(fioDate);   
                    } catch (IOException e) {
                        System.out.println("Ошибка работы с файлом!!!" + e.getLocalizedMessage());
                    }
                    
                } catch (DateIncorrectException e) {
                    System.out.println();
                    System.out.println(e.getLocalizedMessage());
                }
            }
            else
            if(str.equals("2")){
                end = true;}
            else{
                System.out.println();
                System.out.println("Пожалуйста введите 1 или 2");
            }
        }
        scaner.close();
        System.out.println("Спасибо за работу");
    }
}
