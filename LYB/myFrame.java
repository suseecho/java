import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
class myFrame extends JFrame
{
    public void init()
    {
        JFrame f = new JFrame("留言板程序");
        f.setSize(500,450);
//        f.setResizable(false);
//        this.setSize(600,500);
        Container c = f.getContentPane();
        GridBagLayout layout = new GridBagLayout();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        c.setLayout(layout);        //这一行非常重要!!!


        Font font1 = new Font("隶书",Font.PLAIN,35);

        GridBagConstraints s = new GridBagConstraints();

        JLabel label1 = new JLabel("留言板",JLabel.CENTER);
        label1.setFont(font1);

        s.gridx = 0;
        s.gridy = 0;
        s.gridwidth =5;
        s.gridheight =1;
        s.fill = GridBagConstraints.BOTH;
        layout.setConstraints(label1,s);
        c.add(label1);
       
		action act = new action();
        java.util.List<record> r = new ArrayList<record>();
		String url = "jdbc:mysql://127.0.0.1:3306/echo?useSSL=false";//localhost无法连接  原因未知
        String user = "echo";
        String passwd = "icanmakeit";
        String s_rec = "";
        TextArea textarea1 = new TextArea("留言内容:",20,60);
		r = act.showRecord(url,user,passwd);
System.out.println(r.size());
        for(int i = 0 ; i < r.size();i++)
        {
           s_rec +=  r.get(i).getTime()+"  你"+r.get(i).getFace()+"地说:"+r.get(i).getContent() + "\n";
        }
System.out.println(s_rec);
        textarea1.setText("留言内容:\n"+s_rec);
        textarea1.setEditable(false);
        s.gridx = 0 ;
        s.gridy = 1;
        s.gridwidth = 4;
        s.gridheight = 4;
        s.fill = GridBagConstraints.BOTH;
        layout.setConstraints(textarea1,s);
        c.add(textarea1);

        
        JButton button1 = new JButton("清屏");
//        qp act_qp = new qp();
         button1.addActionListener(new ActionListener()
        {
            Connection conn = null;
            Statement s = null;
            public void actionPerformed(ActionEvent e)
            {
               textarea1.setText("留言内容:");
               try
                {
                conn =(Connection) DriverManager.getConnection(url,user,passwd);
                s = conn.createStatement();
                s.executeUpdate("delete from test");
                }
                catch(SQLException a)
                {
                    a.printStackTrace();
                }
            }
        });
        s.gridx = 4;
        s.gridy = 1;
        s.gridwidth = 1;
        s.gridheight = 1;
        s.fill = GridBagConstraints.NONE;
        layout.setConstraints(button1,s);
        c.add(button1);


        JButton button2 = new JButton("至顶");
        s.gridx = 4;
        s.gridy = 4;
        s.gridwidth = 1 ;
        s.gridheight = 1;
        s.fill = GridBagConstraints.NONE;
        layout.setConstraints(button2,s);
        button2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                textarea1.setCaretPosition(0);
            }
        });
        c.add(button2);
        
        JButton button3 = new JButton("至底");
        s.gridx = 4;
        s.gridy = 5;
        s.gridwidth = 1 ;
        s.gridheight = 1;
        s.fill = GridBagConstraints.NONE;
        layout.setConstraints(button3,s);
        button3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                textarea1.setCaretPosition(textarea1.getText().length());
            }
        });

        c.add(button3);

        JLabel label2 = new JLabel("你");
        Font font2 = new Font("隶书",Font.PLAIN,25);
        label2.setFont(font2);
        s.gridx = 0 ;
        s.gridy = 7;
        s.gridwidth = 1;
        s.gridheight = 1;
        s.fill = GridBagConstraints.BOTH;
        layout.setConstraints(label2,s);
        c.add(label2);

        String [] s_face = {"微笑","大哭","愤怒"};
        Font font3 = new Font("隶书",Font.PLAIN,25);
        JComboBox face = new JComboBox(s_face);
        s.gridx = 1 ;
        s.gridy = 7;
        s.gridwidth = 1;
        s.gridheight = 1;
        s.fill = GridBagConstraints.BOTH;
        layout.setConstraints(face,s);
        c.add(face);

        JLabel label3 = new JLabel("地说");
        label3.setFont(font2);
        s.gridx = 2 ;
        s.gridy = 7;
        s.gridwidth = 1;
        s.gridheight = 1;
        s.fill = GridBagConstraints.BOTH;
        layout.setConstraints(label3,s);
        c.add(label3);

        JTextField inputtext = new JTextField(30);
        s.gridx = 3 ;
        s.gridy = 7;
        s.gridwidth = 1;
        s.gridheight = 1;
        s.fill = GridBagConstraints.BOTH;
        layout.setConstraints(inputtext,s);
        c.add(inputtext);

        JButton submit = new JButton("提交");
        s.gridx = 4 ;
        s.gridy = 7;
        s.gridwidth = 1;
        s.gridheight = 1;
        s.fill = GridBagConstraints.BOTH;
        layout.setConstraints(submit,s);
        submit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String t_face = null;
                String t_content = null;
                t_face = face.getSelectedItem().toString();
                t_content = inputtext.getText();
                if(!t_content.equals(""))
                {
                    action t_submit = new action();
                    t_submit.submit(t_face,t_content,url,user,passwd); 


                    java.util.List<record> r = new ArrayList<record>();
                    String s_rec = "";
                    r = act.showRecord(url,user,passwd);
                    for(int i = 0 ; i < r.size();i++)
                    {
                        s_rec +=  r.get(i).getTime()+"  你"+r.get(i).getFace()+"地说:"+r.get(i).getContent() + "\n";
                    }
                    System.out.println(s_rec);
                    textarea1.setText("留言内容:\n"+s_rec);
                    inputtext.setText("");
                }
                else
                {
                   JOptionPane.showMessageDialog(null, "输入不能为空","错误", 0);  
                }
            }
        });
                
        c.add(submit);
    }
}
