//Name - Charles Nana Kwakye
//student number - r0879035

package fact.it.supermarket.model;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Staff extends Person {
    private LocalDate startDate = LocalDate.now();
    private boolean student;

    public Staff(String firstName, String surName) {
        super(firstName, surName);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    public String toString(){
        if(student){
            return "Staff member " + getSurName().toUpperCase() + " " + getFirstName() + " (working student) is employed since " + startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    } else return  "Staff member " + getSurName().toUpperCase() + " " + getFirstName() + " is employed since " + startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
}}
