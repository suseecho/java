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
        String url = "jdbc:mysql://119.29.168.136:3306/lts?useUnicode=true&characterEncoding=utf-8&useSSL=false";//localhost无法连接  原因未知
        String user = "qwer";
        String passwd = "test";


        myFrame demo = new myFrame();
        demo.init(url,user,passwd);
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
            
/*
        s.executeUpdate("create table if not exist test( time TIMESTAMP, face VARCHAR(20), content VARCHAR(200))");
                
*/
        s.close();
        conn.close();

    }
}
