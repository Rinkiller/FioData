import java.time.LocalDate;
import java.util.Scanner;

class HandlerFioDates{
    
    // Функция принимает строковые данные от пользователя и парсит их по пробелам на выходе массив строк
    public  String[] inputFioDate() throws DateIncorrectException{
        System.out.println("Прошу вас ввести в одну строку данные пользователя");
        System.out.println("Фамилия, имя, отчество, дата рождения(формат 01.01.2001) и номер телефона ");
        System.out.println("Данные отделяются друг от друга пробелом");
        System.out.print("Введите данные пользователя:");
        Scanner scaner = new Scanner(System.in,"cp866");
        String[] str = scaner.nextLine().split(" "); 
        if(str.length != 6){ 
            String message = null;
            if(str.length < 6)
                message = "Колличество параметров меньше необходимого количества(6)";
            else 
                message = "Колличество параметров больше необходимого количества(6)";
            throw new DateIncorrectException(message);}
        return str;
    }

    // Функция определяет является ли строка именем(Фамилией или Отчеством) не взирая на раскладку(поддерживаемый язык РУС/EN)
    public static boolean isName(String str){
        boolean flag = true;
        if(str == null) return false;
        int len = str.length();
        if(len < 2) return false;
        if((str.codePointAt(0) >= 65 && str.codePointAt(0) <= 90)){ // английские имена раскладка EN
            for(int i = 1; i < len; i++)
                if(!(str.codePointAt(i) >=  97 && str.codePointAt(i) <= 122 )) flag = false;
        } else 
            if(str.codePointAt(0) >= 1040 && str.codePointAt(0) <= 1071){ // русские имена  раскладка РУС
                for(int i = 1; i < len; i++)
                    if(!(str.codePointAt(i) >=  1072 && str.codePointAt(i) <= 1103 )) flag = false;
            } else flag = false;
        return flag;
    }

    // Функция определяет является ли строка - числом
    public  boolean isDigit(String str){
        try {
            Long.parseLong(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    // Функция определяет является ли строка телефонным номером с префиксом +7 или 8
    public  boolean isTeltphoneNumber(String str){
        if(str == null) return false;
        if(str.length() > 12 || str.length() < 11) return false;
        else
            if(str.length() == 12 && str.substring(0,2).equals("+7")) return isDigit(str.substring(1, 12));
            else    
                if(str.length() == 11 && str.charAt(0) == '8') return isDigit(str);
            else
                return false;
    }

    // Функция определяет удовлетворяют ли элементы массива[3] условия дата месяц год корректность по отношению к текущей дате
    public boolean isCurentDate(String[] arrayOfString){
        boolean flag = true;
        LocalDate date = LocalDate.now();
        if(!(Integer.parseInt(arrayOfString[0]) > 0 && Integer.parseInt(arrayOfString[0]) < 32)) flag = false;
        if(!(Integer.parseInt(arrayOfString[1]) > 0 && Integer.parseInt(arrayOfString[1]) < 13)) flag = false;
        if(!(Integer.parseInt(arrayOfString[2]) <= date.getYear())) flag = false;
        if(Integer.parseInt(arrayOfString[2]) == date.getYear())
            if(Integer.parseInt(arrayOfString[1]) > date.getMonthValue()) return false;
            else   
                if(Integer.parseInt(arrayOfString[1]) == date.getMonthValue())   
                    if(Integer.parseInt(arrayOfString[0]) > date.getDayOfMonth()) return false;
        return flag;
    }


    // Функция определяет является ли строка днем рождения вида dd.mm.yyyy
    public boolean isDateOfBirth(String str){
        if(str == null) return false;
        String[] arrayOfString = str.split("\\.");
        if(arrayOfString.length != 3) return false;
        else
            if(arrayOfString[0].length() == 2 && arrayOfString[1].length() == 2 && arrayOfString[2].length() == 4){
                for(String checkStr : arrayOfString)
                    if(!isDigit(checkStr)) return false;
                return isCurentDate(arrayOfString);
            } else return false;
        
    }

    public int[] getDateOfBirth(String str){
        int[] arrOfDate = new int[3];
        String[] arrayOfString = str.split("\\.");
        for(int i = 0; i < 3; i++)
            arrOfDate[i] = Integer.parseInt(arrayOfString[i]);
        return arrOfDate;
    }

    // Функция определяет является ли строка символом определителем пола (f / m)
    public boolean isGender(String str){
        if(str.length() != 1) return false;
        if(str.equals("f") || str.equals("m")) return true;
        else
            return false;
    }

    // Функция - обработчик проверяет  массив строк на корректность и создает класс FioDate с данными пользователя (порядок следования данных неважен) 
    public FioDate handler(String[] arrayOfString) throws DateIncorrectException{
        String[] fio = new String[3];
        LocalDate dateOfBirth = null;
        String numberTelephone = "";
        char gender = ' ';
        int fioFlag = 0; // 3 значения Имя Фамилия Отчество
        boolean genderFlag = false;
        boolean telephoneFlag = false;
        boolean dateOfBirthFlag = false;
        //boolean error = false; // флаг ошибок
        for(String str : arrayOfString){
            if(isName(str)){ // Names
                if(fioFlag < 3){ // имен(фамилий, отчеств более 3 )   
                    fio[fioFlag] = str;                               
                    fioFlag++;
                }// else error = true;
            }

            if(isDateOfBirth(str)){ // DayOfBirth
                int[] arrOfDate = getDateOfBirth(str);
                dateOfBirth =  LocalDate.of(arrOfDate[2], arrOfDate[1], arrOfDate[0]);
                dateOfBirthFlag = true;
            }

            if(isTeltphoneNumber(str)){ // TeltphoneNumber
                telephoneFlag = true;
                numberTelephone = str;
            }

            if(isGender(str)){ // Gender
                genderFlag = true;
                gender = str.charAt(0);
            }
        }
        if(fioFlag == 3 && dateOfBirthFlag && telephoneFlag && genderFlag) return new FioDate(fio[0], fio[1], fio[2], dateOfBirth, numberTelephone, gender);
        else {
            String messageOfError = "Внимание! Введенные данные некоректны! ";
            if(fioFlag < 3) messageOfError = messageOfError + " Некоректные данные Ф.И.О. ";
            if(!dateOfBirthFlag) messageOfError = messageOfError + " Некоректные дня рождения ";
            if(!telephoneFlag) messageOfError = messageOfError + " Некоректные телефонного номера ";
            if(!genderFlag) messageOfError = messageOfError + " Некоректные пола f/m ";
            throw new DateIncorrectException(messageOfError); 
        }
    }

}
