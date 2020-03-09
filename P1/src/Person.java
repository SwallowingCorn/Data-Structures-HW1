public class Person {

    private String personID;
    private String phoneNum;


    public Person(String personID, String phoneNum) {
        this.personID = personID;
        this.phoneNum = phoneNum;
    }


    //just so displaying their names is easy
    public String getPersonID() {
        return this.personID;
    }


}
