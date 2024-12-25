package com.demo;

import com.model.Employee;
import com.util.MyDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDao{

    //here inserting Data of 5000 Employee where we used Random class ,
    //Because manually inserting data is not possible -------------------------

    public int insertEmployee(Employee e)
    {
        int check =0;
        String sql = "insert into Employee (name , designation , company , salary ) values (?,?,?,?)";

        try(Connection con = MyDatabase.createConnection();
            PreparedStatement pst = con.prepareStatement(sql);){

            pst.setString(1,e.getName());
            pst.setString(2,e.getDesignation());
            pst.setString(3,e.getCompany());
            pst.setDouble(4,e.getSalary());

            check = pst.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if(check>0)
        {
            System.out.println("Your Data is Inserted Successfully ... !!");
        }
        else{
            System.out.println("Sorry... ! , I can't Do that . ");
        }
        return check;
    }




    //Delete Record from Employee by using ID --------------------------

    public int deleteEmployeeById(int id)
    {
        int check=0;
        String sql = "delete from employee where id = ?";

        try(Connection con = MyDatabase.createConnection();
        PreparedStatement pst= con.prepareStatement(sql);){

            pst.setInt(1,id);

            check = pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(check >0)
        {
            System.out.println("Your Data Deleted Successfully .. !");
        }
        else
        {
            System.out.println("Sorry ...! , I can't Do that .. !");
        }
        return check;
    }




    //FindEmployee Details by using Id  -----------------------------------------

    public Employee FindEmployeeById(int id)
    {
        Employee e=null;
        String sql = "select * from employee where id = ? ";

        try(Connection con = MyDatabase.createConnection();
        PreparedStatement pst = con.prepareStatement(sql);) {

            pst.setInt(1,id);

            ResultSet rs= pst.executeQuery();

            List<Employee> list = MyDatabase.EmployeeRowMapper(rs);

            if(!list.isEmpty())
            {
                e=list.get(0);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return e;
    }



    //Find All Employee Details -----------------------------------

    public List<Employee> FindAllEmployee()
    {
        List<Employee> list = new ArrayList<>();

        String sql = "Select * from Employee";

        try(Connection con = MyDatabase.createConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();){

            list.addAll(MyDatabase.EmployeeRowMapper(rs));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }



    //Update Employee Data ------------------------------------------------

    public int updateEmployee(Employee e)
    {
        int check = 0;

        String sql = "update Employee set name = ? , designation = ? , company = ?  where id = ?";

        try(Connection con=MyDatabase.createConnection();
        PreparedStatement pst = con.prepareStatement(sql);){

            pst.setString(1,e.getName());
            pst.setString(2,e.getDesignation());
            pst.setString(3,e.getCompany());
            pst.setInt(4,e.getId());

            check=pst.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if(check >0)
        {
            System.out.println("your Data is Update SUccessfully ........ !");
        }
        else
        {
            System.out.println("Sorry ... ! , I can't Do that . Please try Again");
        }
        return check;
    }


    // "Less Than" (<) operator used here ---------------------------------
    public List<Employee> findEmployeeBySalaryLessThan(double salary)
    {
        List<Employee> list = new ArrayList<>();

        String sql = "Select * from Employee where salary < ? ";

        try(Connection con = MyDatabase.createConnection();
        PreparedStatement pst = con .prepareStatement(sql);) {

            pst.setDouble(1,salary);

            ResultSet rs = pst.executeQuery();

            list.addAll(MyDatabase.EmployeeRowMapper(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    //Between salary we are fetching here ( " Between operator ")
    public List<Employee> findEmployeeBySalaryBetween(double low , double high)
    {
        List <Employee> list = new ArrayList<>();

        String sql = "select * from Employee where salary between ? and ? ";

        try(Connection con = MyDatabase.createConnection();
        PreparedStatement pst = con.prepareStatement(sql);) {

            pst.setDouble(1,low);
            pst.setDouble(2,high);

            ResultSet rs = pst.executeQuery();

            list.addAll(MyDatabase.EmployeeRowMapper(rs));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }



    //Name and Company find out -------------------------------
    public List<Employee> FindEmployeeByNameAndCompany(String name , String company)
    {
        List<Employee> list = new ArrayList<>();

        String sql = "Select * from Employee where name = ? and company = ? ";

        try(Connection con = MyDatabase.createConnection();
        PreparedStatement pst = con.prepareStatement(sql);)
        {
            pst.setString(1,name);
            pst.setString(2,company);

            ResultSet rs = pst.executeQuery();
            list.addAll(MyDatabase.EmployeeRowMapper(rs));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }



    //Find name by using "like" operator ---------------------------------------
    public List<Employee> FindEmployeeByNameLike(String name)
    {
        List<Employee> list = new ArrayList<>();

        String sql = "Select * from Employee where name like ?  ";

        try(Connection con = MyDatabase.createConnection();
            PreparedStatement pst = con.prepareStatement(sql);)
        {
            pst.setString(1,"%"+name+"%");

            ResultSet rs=pst.executeQuery();

            list.addAll(MyDatabase.EmployeeRowMapper(rs));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }




    //Find Employee By Using "OR" operator -----------------------------------------------
    public List<Employee> FindEmployeeByAny(String value)
    {
        List<Employee> list = new ArrayList<>();

        String sql = "Select * from Employee where id =? or salary = ? or  name like ? or designation like ? " +
                " or company like ? ";

        try(Connection con = MyDatabase.createConnection();
            PreparedStatement pst = con.prepareStatement(sql);)
        {
            pst.setString(1,value);
            pst.setString(2,value);
            pst.setString(3,"%"+value+"%");
            pst.setString(4,"%"+value+"%");
            pst.setString(5,"%"+value+"%");

            ResultSet rs = pst.executeQuery();

            list.addAll(MyDatabase.EmployeeRowMapper(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    //Find all name of employee ------------------------------------------
    public List<String> findAllName()
    {
        List<String> list = new ArrayList<>();
        String sql= "Select name from employee";

        try(Connection con=MyDatabase.createConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs=pst.executeQuery();) {

            while (rs.next())
            {
                list.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }





    //Find id , name and salary -----------------------------------------
    public Map<String,List> findIdAndNameAndSalary()
    {
        Map<String,List> m = new HashMap<>();

        String sql = "select id , name , salary from employee";

        try(Connection con=MyDatabase.createConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();)
        {
            List <Integer> list1=new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            List<Double> list3 = new ArrayList<>();

            while(rs.next())
            {
                list1.add(rs.getInt("id"));
                list2.add(rs.getString("name"));
                list3.add(rs.getDouble("salary"));
            }

            m.put("listofId", list1);
            m.put("listofName", list2);
            m.put("listofSalary", list3);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return m;
    }





    //FindMaximum salary from Employee -----------------------------------------
    public double Findmaxsalary()
    {
        double d=0;
        String sql = "Select max(salary) as salary from employee";

        try(Connection con=MyDatabase.createConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();) {

        while (rs.next())
        {
            d=rs.getDouble("salary");
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }



    //FindsecondMaximum salary from Employee -----------------------------------------
    public double Findsecondmaxsalary()
    {
        double d=0;
        String sql = "select max(salary) as salary from employee where salary < " +
                "(select max(salary) as salary from employee)";

        try(Connection con=MyDatabase.createConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();) {

            while (rs.next())
            {
                d=rs.getDouble("salary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }




    //Find employee details those employee highest salary -----------------------------

    public List<Employee> FindHighestSalaryEmployee()
    {
        List<Employee > list = new ArrayList<>();

        String sql = "select * from Employee where salary = (select max(salary) from employee";

        try(Connection con = MyDatabase.createConnection();
        PreparedStatement pst =con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();) {

            list.addAll(MyDatabase.EmployeeRowMapper(rs));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

}