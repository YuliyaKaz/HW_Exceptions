import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.DataFormatException;

public class Organizer {
    //Список лиц
    List<Person> listPerson;
    //Список фамилий для сохранения в файл
    Set<String> setPerson;

    public Organizer() {
        listPerson = new ArrayList<Person>();
        setPerson = new HashSet<String>();
    }

    /**
     * Парсер
     */
    public boolean parse(String line) throws StringIndexOutOfBoundsException,
             ArrayIndexOutOfBoundsException, DataFormatException, IllegalArgumentException {
        String family = "";
        String name = "";
        String middleName = "";
        Integer phoneNumber = 0;
        Date birthDay;
        String gender = "";

        if (line.equals("q")) {
            if (!setPerson.isEmpty()) {
                try {
                    save();
                } catch (IOException e) {
                    e.getMessage();
                }
            }
            return false;
        }
        String[] parseLine = line.split(" ");
        if (parseLine.length != 6) {
            throw new ArrayIndexOutOfBoundsException("Количество данных не совпадает");
        }
        if (parseLine[0].matches(".*\\d.*")){
            throw  new StringIndexOutOfBoundsException("Недопустимый символ в поле Фамилия");
        } else {
            family = parseLine[0];
        }
        if (parseLine[1].matches(".*\\d.*")){
            throw  new StringIndexOutOfBoundsException("Недопустимый символ в поле Имя");
        } else {
            name = parseLine[1];
        }
        if (parseLine[2].matches(".*\\d.*")){
            throw  new StringIndexOutOfBoundsException("Недопустимый символ в поле Отчество");
        } else {
            middleName = parseLine[2];
        }

        if (parseLine[3].matches("([0-9]{2}).([0-9]{2}).([0-9]{4})") == false) {
            throw new DataFormatException("Недопустимый формат даты");
        } else {
            try {
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                birthDay = df.parse(parseLine[3]);
            } catch (java.text.ParseException e) {
                throw new DataFormatException("Недопустимый формат даты");
            }
        }

        phoneNumber = Integer.parseInt(parseLine[4]);

        if (parseLine[5].equals("m") || parseLine[5].equals("f")) {
            gender = parseLine[5];
        } else {
            throw new StringIndexOutOfBoundsException("Недопустимое значение ");
        }
        
        Person person = new Person(family, name, middleName, phoneNumber, birthDay, gender );
        listPerson.add(person);
        setPerson.add(family);
        return true;
    }

    /**
     * Сохранение данных в файл.
     * @throws IOException Ошибка сохранения.
     */
    private void save() throws IOException {
        if (setPerson.isEmpty()) {
            return;
        }
        for (String familyAsFilename : setPerson) {
            try(FileWriter writer = new FileWriter(familyAsFilename + ".txt", false))
            {
                for (Person person : listPerson) {
                    if (!person.getFamily().equals(familyAsFilename)) {
                        continue;
                    }
                    // запись всей строки
                    writer.write(person.toString());
                    writer.append('\n');
                }
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
