//Name - Charles Nana Kwakye
//student number - r0879035

package fact.it.supermarket.model;

public class Department {
    private String name;
    private String photo;
    private boolean refrigerated;
    private Staff staff;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isRefrigerated() {
        return refrigerated;
    }

    public void setRefrigerated(boolean refrigerated) {
        this.refrigerated = refrigerated;
    }

    public Staff getResponsible() {
        return staff;
    }

    public void setResponsible(Staff responsible) {
        this.staff = responsible;
    }
}
