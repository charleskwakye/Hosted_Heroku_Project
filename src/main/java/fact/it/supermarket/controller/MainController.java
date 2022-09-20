//Name - Charles Nana Kwakye
//student number - r0879035
package fact.it.supermarket.controller;

import fact.it.supermarket.model.Customer;
import fact.it.supermarket.model.Department;
import fact.it.supermarket.model.Staff;
import fact.it.supermarket.model.Supermarket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class MainController {
    //You will need these ArrayLists in part 3 of the project assignment.
    private ArrayList<Staff> staffArrayList;
    private ArrayList<Customer> customerArrayList;
    private ArrayList<Supermarket> supermarketArrayList;

    //    Write your code here
    @PostConstruct
    private void fillStaffCustomerSupermarket() {
        staffArrayList = fillStaffMembers();
        customerArrayList = fillCustomers();
        supermarketArrayList = fillSupermarkets();
    }

    @RequestMapping("/1_input")
    public String input(Model model) {
        model.addAttribute("supermarketList", supermarketArrayList);
        return "1_input";
    }

    @RequestMapping("/3_nStaffMember")
    public String nStaffMember() {
        return "/3_nStaffMember";
    }

    @RequestMapping("/7_nSupermarket")
    public String nSupermarket(Model model){
        model.addAttribute("staffList", staffArrayList);
        return "/7_nSupermarket";
    }

    @RequestMapping("/submitting")
    public String submitting(HttpServletRequest request, Model model) {
        String firstName = request.getParameter("firstName");
        String surName = request.getParameter("surName");
        int supermarketName = Integer.parseInt(request.getParameter("supermarketName"));
        int yearOfBirth = Integer.parseInt(request.getParameter("yearOfBirth"));

        Customer customer = new Customer(firstName, surName);
        Supermarket supermarket = supermarketArrayList.get(supermarketName);
        //System.out.println(supermarket);
        supermarket.registerCustomer(customer);
        customer.setYearOfBirth(yearOfBirth);
        customerArrayList.add(customer);
        model.addAttribute("customer", customer);
        return "/2_welcome";
    }

    @RequestMapping("/submittingStaff")
    public String submittingStaff(HttpServletRequest request, Model model) {
        String firstName = request.getParameter("firstName");
        String surName = request.getParameter("surName");
        LocalDate employedDate = LocalDate.parse(request.getParameter("employedDate"));
        boolean workingStudent = (request.getParameter("workingStudent") != null);

        Staff staff = new Staff(firstName, surName);
        staff.setStartDate(employedDate);
        staff.setStudent(workingStudent);
        staffArrayList.add(staff);
        model.addAttribute("staff", staff);
        return "/4_displayStaff";
    }

    @RequestMapping("/submittingNewSupermarket")
    public String submittingNewSupermarket(HttpServletRequest request, Model model) {
        String supermarketName = request.getParameter("supermarketName");
        int managerIndex = Integer.parseInt(request.getParameter("generalManager"));
        if (managerIndex < 0) {
            model.addAttribute("errormessage", "You didn't choose a general manager!");
            return "error";
        }


        Supermarket supermarket = new Supermarket(supermarketName);
        supermarketArrayList.add(supermarket);
        model.addAttribute("managerName", staffArrayList.get(managerIndex));
        supermarket.setGeneralManager(staffArrayList.get(managerIndex));
        model.addAttribute("supermarketList", supermarketArrayList);
        model.addAttribute("supermarketName", supermarketName);
        return "/0_exam";
    }

    @RequestMapping("/submittingNewDepartment")
    public String submittingNewDepartment(HttpServletRequest request, Model model) {
        String departmentName = request.getParameter("departmentName");
        String photo = request.getParameter("photo");
        int responsibleStaff = Integer.parseInt(request.getParameter("responsibleStaff"));
        int supermarketOption = Integer.parseInt(request.getParameter("supermarketOption"));
        boolean refrigerated = (request.getParameter("refrigerated") != null);

        if (supermarketOption < 0) {
            model.addAttribute("errormessage", "You did not select a supermarket!");
            return "error";
        }

        if (responsibleStaff < 0) {
            model.addAttribute("errormessage", "You did no select a a staff!");
            return "error";
        }

        Department department = new Department(departmentName); //Create new department with department name from previous webpage
        department.setPhoto(photo);
        department.setRefrigerated(refrigerated);
        department.setResponsible(staffArrayList.get(responsibleStaff));

        //Get supermarket array list with the gotten parameter, Get the department array list from the supermarket and the new department
        supermarketArrayList.get(supermarketOption).getDepartmentList().add(department);

        model.addAttribute("supermarketOption", supermarketOption);
        model.addAttribute("departmentList", supermarketArrayList.get(supermarketOption).getDepartmentList());
        model.addAttribute("supermarket", supermarketArrayList.get(supermarketOption));
        return "/10_displayDepartments";
    }





    @RequestMapping("/5_staffList")
    public String staffList(Model model) {
        model.addAttribute("staffList", staffArrayList);
        return "5_staffList";
    }

    @RequestMapping("/6_customerList")
    public String customerList(Model model) {
        model.addAttribute("customerList", customerArrayList);
        return "6_customerList";
    }

    @RequestMapping("/8_supermarketList")
    public String supermarketList(Model model) {
        model.addAttribute("supermarketList", supermarketArrayList);
        return "/8_supermarketList";
    }

    @RequestMapping("/9_nDepartment")
    public String nDepartment(Model model) {
        model.addAttribute("supermarketList", supermarketArrayList);
        model.addAttribute("staffList", staffArrayList);
        return "/9_nDepartment";
    }


        @RequestMapping("10_displayDepartments")
        public String displayDepartments(HttpServletRequest request,Model model) {
        int supermarketIndex = Integer.parseInt(request.getParameter("supermarketIndex"));

        Supermarket supermarket = supermarketArrayList.get(supermarketIndex);
        model.addAttribute("supermarket",supermarket);
        return "/10_displayDepartments";
    }
    @RequestMapping("/searchDepartment")
    public String searchResults(HttpServletRequest request,Model model){
        String departmentName = request.getParameter("departmentName");
        for (var supermarket:
             supermarketArrayList) {
            Department department = supermarket.searchDepartmentByName(departmentName);
            if (department == null){
                model.addAttribute("errormessage","There is no department with the name '"+departmentName+"'");
                return "/error";
            }
            model.addAttribute("searchResults",department);
            return "/11_searchResults";

        }
    return " ";
    }




    //You wll need these methods in part 3 of the project assignment
    private ArrayList<Staff> fillStaffMembers() {
        ArrayList<Staff> staffMembers = new ArrayList<>();
        Staff staff1 = new Staff("Johan", "Bertels");
        staff1.setStartDate(LocalDate.of(2002, 5, 1));
        Staff staff2 = new Staff("An", "Van Herck");
        staff2.setStartDate(LocalDate.of(2019, 3, 15));
        staff2.setStudent(true);
        Staff staff3 = new Staff("Bruno", "Coenen");
        staff3.setStartDate(LocalDate.of(1995, 1, 1));
        Staff staff4 = new Staff("Wout", "Dayaert");
        staff4.setStartDate(LocalDate.of(2002, 12, 15));
        Staff staff5 = new Staff("Louis", "Petit");
        staff5.setStartDate(LocalDate.of(2020, 8, 1));
        staff5.setStudent(true);
        Staff staff6 = new Staff("Jean", "Pinot");
        staff6.setStartDate(LocalDate.of(1999, 4, 1));
        Staff staff7 = new Staff("Ahmad", "Bezeri");
        staff7.setStartDate(LocalDate.of(2009, 5, 1));
        Staff staff8 = new Staff("Hans", "Volzky");
        staff8.setStartDate(LocalDate.of(2015, 6, 10));
        staff8.setStudent(true);
        Staff staff9 = new Staff("Joachim", "Henau");
        staff9.setStartDate(LocalDate.of(2007, 9, 18));
        staffMembers.add(staff1);
        staffMembers.add(staff2);
        staffMembers.add(staff3);
        staffMembers.add(staff4);
        staffMembers.add(staff5);
        staffMembers.add(staff6);
        staffMembers.add(staff7);
        staffMembers.add(staff8);
        staffMembers.add(staff9);
        return staffMembers;
    }

    private ArrayList<Customer> fillCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer("Dominik", "Mioens");
        customer1.setYearOfBirth(2001);
        Customer customer2 = new Customer("Zion", "Noops");
        customer2.setYearOfBirth(1996);
        Customer customer3 = new Customer("Maria", "Bonetta");
        customer3.setYearOfBirth(1998);
        Customer customer4 = new Customer("Charles Nana", "Kwakye");
        customer4.setYearOfBirth(2002);
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.get(0).addToShoppingList("Butter");
        customers.get(0).addToShoppingList("Bread");
        customers.get(1).addToShoppingList("Apple");
        customers.get(1).addToShoppingList("Banana");
        customers.get(1).addToShoppingList("Grapes");
        customers.get(1).addToShoppingList("Oranges");
        customers.get(2).addToShoppingList("Fish");
        customers.get(3).addToShoppingList("Macbook");
        return customers;
    }

    private ArrayList<Supermarket> fillSupermarkets() {
        ArrayList<Supermarket> supermarkets = new ArrayList<>();
        Supermarket supermarket1 = new Supermarket("Delhaize");
        Supermarket supermarket2 = new Supermarket("Colruyt");
        Supermarket supermarket3 = new Supermarket("Albert Heyn");
        Department department1 = new Department("Fruit");
        Department department2 = new Department("Bread");
        Department department3 = new Department("Vegetables");
        department1.setPhoto("/img/fruit.jpg");
        department2.setPhoto("/img/bread.jpg");
        department3.setPhoto("/img/vegetables.jpg");
        department1.setResponsible(staffArrayList.get(0));
        department2.setResponsible(staffArrayList.get(1));
        department3.setResponsible(staffArrayList.get(2));
        supermarket1.addDepartment(department1);
        supermarket1.addDepartment(department2);
        supermarket1.addDepartment(department3);
        supermarket2.addDepartment(department1);
        supermarket2.addDepartment(department2);
        supermarket3.addDepartment(department1);
        supermarket3.addDepartment(department3);
        supermarkets.add(supermarket1);
        supermarkets.add(supermarket2);
        supermarkets.add(supermarket3);

        supermarket1.setGeneralManager(staffArrayList.get(0));
        supermarket2.setGeneralManager(staffArrayList.get(1));
        supermarket3.setGeneralManager(staffArrayList.get(2));

        return supermarkets;
    }
}
