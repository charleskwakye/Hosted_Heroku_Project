//Name - Charles Nana Kwakye
//student number - r0879035
package fact.it.supermarket.model;

public class Person {
    private String firstName;
    private String surName;

    public Person() {
    }

    public Person(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String toString(){
        return surName.toUpperCase() + ' ' + firstName;
    }

}
