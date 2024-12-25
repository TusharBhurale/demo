package com.util;

import com.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyDatabase {
    public static Connection  createConnection ()
    {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jap72", "root", "Tushar");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeConnection(PreparedStatement pst , Connection con)
    {
        try{
            pst.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
//        return con;
    }

    public static void closeConnection(ResultSet rs, PreparedStatement pst , Connection con)
    {
        try{
            rs.close();
            pst.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Employee> EmployeeRowMapper(ResultSet rs) throws SQLException {
        List<Employee> list = new ArrayList<>();
        while (rs.next())
        {
            Employee s = new Employee();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setDesignation(rs.getString("designation"));
            s.setCompany(rs.getString("company"));
            s.setSalary(rs.getDouble("salary"));

            list.add(s);
        }
        return list;
    }
}
