package com.application;

import com.demo.EmployeeDao;
import com.model.Employee;

import javax.xml.transform.Source;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        EmployeeDao ed = new EmployeeDao();
        String companyName []={"TCS" , "TECHM","Capgimine","Wipro","DXC","Oracle","Zensar","Bitwice","Pubmatic","Accenture",
        "Xoriant","Synchron","Maxxton","Yardi","Nvidea","Mobisoft","IBM","Infosys","cognizant","QuantumID"};


        //Here I insert 5000 Employee Data at a One time
        //Because we need Data of 5000 Employee , after Inserting Dont use repeat
        //You can handle and make operation as per requiment , so here temparory hide ---------------


//        for(int i=1;i<=5000;i++)
//        {
//            Employee e = new Employee();
//
//            StringBuilder name = new StringBuilder();
//            StringBuilder designation = new StringBuilder();
//
//            for(int j=1;j<=15;j++)
//            {
//                name.append((char)(new Random().nextInt(26)+65));
//                designation.append((char)(new Random().nextInt(26)+65));
//            }
//
//            e.setName(name.toString());
//            e.setDesignation(designation.toString());
//            e.setSalary(new Random().nextDouble()*1000000);
//            e.setCompany(companyName[new Random().nextInt(companyName.length)]);
//            System.out.println(ed.insertEmployee(e));
//        }


        //Delete Record from Employee by using ID --------------------------
        //System.out.println(ed.deleteEmployeeById(1));


        //FindEmployee Details by using Id  -----------------------------------------
        //System.out.println(ed.FindEmployeeById(501));


        //Find All Employee Details -----------------------------------
//        System.out.println(ed.FindAllEmployee()+" ");


        //Update Employee Data ------------------------------------------------
//        Employee e=new Employee();
//        e.setName("Krishna");
//        e.setDesignation("asdfasfsadf");
//        e.setCompany("Google");
//        e.setId(5);
//        System.out.println(ed.updateEmployee(e));



        // "Less Than" (<) operator used here ---------------------------------
        //System.out.println(ed.findEmployeeBySalaryLessThan(50000)+" \t  ");



        //Between salary we are fetching here ( " Between operator ") -----------------
        //System.out.println(ed.findEmployeeBySalaryBetween(10000,12000));



        //Name and Company find out  -----------------------------------
        //System.out.println(ed.FindEmployeeByNameAndCompany("SIRNWTONJZEBAAA","DXC"));



        //Find name by using "like" operator ---------------------------------------
        //System.out.println(ed.FindEmployeeByNameLike("aaa"));



        //Find Employee Any --------------------------------------------------
        //System.out.println(ed.FindEmployeeByAny("%"+"aaa"+"%"));


        //Find all Name from employee --------------------------------------
//        List<String> l=ed.findAllName();
//        for(String s:l)
//        {
//            System.out.println(s);
//        }


        ////Find id , name and salary -----------------------------------------
        Map<String,List> m=ed.findIdAndNameAndSalary();
        for (int i = 0; i < m.get("listofId").size(); i++) {
            System.out.println(m.get("listofId").get(i) + "\t" +
                    m.get("listofName").get(i) + "\t" +
                    m.get("listofSalary").get(i));
        }



        //Find maximum salary from Employee --------------------------------------
//        double result =ed.Findmaxsalary();
//        System.out.println("maximum salary of employee : - "+result);



        //Find SecondMaximum salary  ---------------------------------------
//        double result = ed.Findsecondmaxsalary();
//        System.out.println("The second maximum salary = "+result);

//        for(Employee e:ed.FindHighestSalaryEmployee())
//        {
//            System.out.println(e);
//        }



        //
    }

}
