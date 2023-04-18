import java.time.LocalDate;

public class FioDate {
    private String ferstName;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    private String numberTelephone;
    private char gender;

    public FioDate(String ferstName, String name, String lastName, LocalDate dateOfBirth, String numberTelephone,
            char gender) {
        this.ferstName = ferstName;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.numberTelephone = numberTelephone;
        this.gender = gender;
    }

    public String getFerstName() {
        return ferstName;
    }

    public void setFerstName(String ferstName) {
        this.ferstName = ferstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNumberTelephone() {
        return numberTelephone;
    }

    public void setNumberTelephone(String numberTelephone) {
        this.numberTelephone = numberTelephone;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "FioDate [ferstName=" + ferstName + ", name=" + name + ", lastName=" + lastName + ", dateOfBirth="
                + dateOfBirth + ", numberTelephone=" + numberTelephone + ", gender=" + gender + "]";
    }
    
    
}
