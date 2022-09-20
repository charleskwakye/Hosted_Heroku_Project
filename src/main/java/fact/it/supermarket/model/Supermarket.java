//Name - Charles Nana Kwakye
//student number - r0879035

package fact.it.supermarket.model;

import java.util.ArrayList;

public class Supermarket {
    private String name;
    private int numberCustomers;
    private Department department;
    private ArrayList<Department> departmentList = new ArrayList<>();
    private Staff staff;

    public Supermarket(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberCustomers() {
        return numberCustomers;
    }

//    public void setNumberCustomers(int numberCustomers) {
//        this.numberCustomers = numberCustomers;
//    }

    public ArrayList<Department> getDepartmentList() {
        return departmentList;
    }

    public int getNumberOfDepartments(){
        return departmentList.size();
    }
    public void addDepartment(Department department){
        departmentList.add(department);
    }
    public Department searchDepartmentByName(String name){
        this.name = name;
        int index = 0;
        while ( index < departmentList.size()){
            String deName = departmentList.get(index).getName();
            if (name.equals(deName)){
                return departmentList.get(index);
            }
            index++;
        }
        return null;
    }
    public void registerCustomer(Customer customer){
        numberCustomers++;
        customer.setCardNumber(numberCustomers);
    }

    public Staff getGeneralManager(){
        return staff;
    }

    public void setGeneralManager(Staff generalManager){
        this.staff = generalManager;
    }
}
