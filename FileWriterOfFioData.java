import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriterOfFioData {
    /**
     * @param fio
     * @throws IOException
     */
    public  void FileWriterOfFioData(FioDate fio) throws IOException{
        if(Files.exists(Path.of(fio.getFerstName()))) {
            // фаил существует
            BufferedWriter writer = new BufferedWriter(new FileWriter(fio.getFerstName(), true));
            StringBuilder strBuilder = new StringBuilder("<");
            strBuilder.append(fio.getFerstName());
            strBuilder.append("><");
            strBuilder.append(fio.getName());
            strBuilder.append("><");
            strBuilder.append(fio.getLastName());
            strBuilder.append("><");
            strBuilder.append(fio.getDateOfBirth());
            strBuilder.append("><");
            strBuilder.append(fio.getNumberTelephone());
            strBuilder.append("><");
            strBuilder.append(fio.getGender());
            strBuilder.append(">\n");
            writer.append(strBuilder.toString());
    
    writer.close();
            System.out.println("Новые данные успешно дописаны в фаил с именем:" + fio.getFerstName()); 
        }
        else {// файла нет
            BufferedWriter writer = new BufferedWriter(new FileWriter(fio.getFerstName()));
            StringBuilder strBuilder = new StringBuilder("<");
            strBuilder.append(fio.getFerstName());
            strBuilder.append("><");
            strBuilder.append(fio.getName());
            strBuilder.append("><");
            strBuilder.append(fio.getLastName());
            strBuilder.append("><");
            strBuilder.append(fio.getDateOfBirth());
            strBuilder.append("><");
            strBuilder.append(fio.getNumberTelephone());
            strBuilder.append("><");
            strBuilder.append(fio.getGender());
            strBuilder.append(">\n");
            writer.write(strBuilder.toString());
            writer.close();
            System.out.println("Фаил c именем " + fio.getFerstName() + " успешно создан");
        }
        
    }
}
