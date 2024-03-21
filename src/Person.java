import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Объект персоны для сохранения.
 */
public class Person {
    private String family;
    private String name;
    private String middleName;
    private Integer phoneNumber;
    private Date birthDay;
    private String gender;
    public Person ( String family, String name, String middleName, Integer phoneNumber,
                    Date birthDay, String gender ) {
        this.family = family;
        this.name = name;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    /**
     * getter-метод для фамилии
     * @return фамилия
     */
    public String getFamily() {
        return this.family;
    }

    /**
     * Вывод данных персон.
     * @return Отформатированная строка
     */
    public String toString() {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return "<" + family + "> <" + name + "> <" + middleName + "> <" + formatter.format(birthDay) +
                "> <" + phoneNumber + "> <" + gender + ">";
    }
}
