public class PhoneBook {

    public  String firstName, lastName,number, note;


    public PhoneBook(){
        this.firstName = "";
        this.lastName  = "";
        this.number    = "";
        this.note      = "";
    }

    public  PhoneBook(String firstName, String lastName, String number, String note){

        this.firstName = firstName;
        this.lastName  = lastName;
        this.number    = number;
        this.note      = note;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public String getNote(){
        return note;
    }
    public void setNote(String note){
        this.note  = note;
    }
}
