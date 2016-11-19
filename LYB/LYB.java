import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
class Demo
{
    public static void main(String args[]) throws SQLException
    {
/*
        String user;
        String passwd;
        System.out.println("请输入用户名");
        Scanner reader = new Scanner(System.in);
        user = reader.nextLine();
        System.out.println("
*/


        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/echo";//localhost无法连接  原因未知
        String user = "echo";
        String passwd = "icanmakeit";
        try
        {
            Class.forName(driver);
            System.out.println("创建驱动成功");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        Connection conn = null;
        try
        {
            conn =(Connection) DriverManager.getConnection(url,user,passwd);
            System.out.println("连接数据库成功");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        Statement s = conn.createStatement();
        s.executeUpdate(
            "create table test");
        s.close();
        conn.close();

        myFrame demo = new myFrame();
        demo.init();
    }
}
