import java.sql.*;
import javax.swing.*;
import java.util.*;
class action
{
    public List<record> showRecord(String url , String user , String passwd)
	{
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
        final String driver = "com.mysql.jdbc.Driver";
		List<record> r_list = new ArrayList<record>();
		try
        {
            Class.forName(driver);
//            System.out.println("创建驱动成功");
        }
        catch(ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(0);
        }

        try
        {

            conn =(Connection) DriverManager.getConnection(url,user,passwd);
			s = conn.createStatement();
			rs = s.executeQuery("select * from test");
//            System.out.println("连接数据库成功");
			while(rs.next())
			{
				record rec = new record();
				rec.setTime(rs.getString("time"));
				rec.setFace(rs.getString("face"));
				rec.setContent(rs.getString("content"));
/*
System.out.println(rs.getString("time"));
System.out.println(rs.getString("face"));
System.out.println(rs.getString("content"));
*/
                r_list.add(rec);
			}
            s.close();
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
        return r_list;
    }


    public void submit(String face,String content ,String url ,String user ,String passwd  )
    {
        Connection conn = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        try
        {
            conn = (Connection) DriverManager.getConnection(url,user,passwd);
            String sql = "insert into test(time,face,content) values(?,?,?)";
            s = conn.prepareStatement(sql);
            s.setString(1,null);
            s.setString(2,face);
            s.setString(3,content);
            s.executeUpdate();

            s.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
/*
        action act = new action();
        act.showRecord(url,user,passwd);
        r = act.showRecord(url,user,passwd);
        for(int i = 0 ; i < r.size();i++)
        {
           s_rec +=  r.get(i).getTime()+"你"+r.get(i).getFace()+"地说:"+r.get(i).getContent() + "\n";
        }
System.out.println(s_rec);
        textarea1.setText("留言内容:\n"+s_rec);
*/
//        myFrame demo = new myFrame();
//        demo.init();
            
    }

}






class record
{
	private String time;
	private String face;
	private String content;
	public void setTime(String s)
	{
		time = s;
	}
	public void setFace(String f)
	{
		face = f;
	}
	public void setContent(String con)
	{
		content = con;
	}
    public String getTime()
    {
        return time;
    }
    public String getFace()
    {
        return face;
    }
    public String getContent()
    {
        return content;
    }
}
