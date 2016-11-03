import java.io.FileReader;
import java.lang.Math.*;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.util.*;
class test
{
    
    public static void main(String args[]) throws Exception
    {
        System.out.println("请输入身份证号码");
        Scanner reader = new Scanner(System.in);
        String num = reader.nextLine();
        System.out.println("输入的身份证号码为："+num);


//        FileReader temp  = new FileReader("sfz.txt");
        InputStreamReader temp = new InputStreamReader(new FileInputStream("sfz.txt"));
        BufferedReader f1 = new BufferedReader(temp);
        String s = null;
        String[] bm = new String[3466];
        String[] dz = new String[3466];
        String t1 = null;
        String t2 = null;
        String s_rq = null ,  s_n = null , s_y = null ,s_r = null;
        int i_n = 0, i_y  = 0 ,i_r = 0 ;
        boolean isOld = false;
        if(num.length() == 18 || num.length() == 15)
        {
            if(num.length()==18)
            {
                isOld = false;
                s_rq = num.substring(6,14);
                s_n = s_rq.substring(0,4);
                s_y = s_rq.substring(4,6);
                s_r = s_rq.substring(6,8);
                i_n = Integer.parseInt(s_n);
                i_y = Integer.parseInt(s_y);
                i_r = Integer.parseInt(s_r);
            }
            if(num.length()==15)
            {
                isOld = true;
                s_rq = num.substring(6,12);
                s_n = s_rq.substring(0,2);
                s_y = s_rq.substring(2,4);
                s_r = s_rq.substring(4,6);
                i_n = Integer.parseInt(s_n);
                i_y = Integer.parseInt(s_y);
                i_r = Integer.parseInt(s_r);
            }
            String s_gender = null;
            int i_gender = 0;
            System.out.print("性别：");
            if(isOld)
            {
                s_n = "19"+s_n;
                s_gender = num.substring(14);
                i_gender = Integer.parseInt(s_gender);
                if(i_gender%2==1)
                    System.out.println("男");
                else
                    System.out.println("女");
            }
            else
            {
                s_gender = num.substring(17);
                i_gender = Integer.parseInt(s_gender);
                if(i_gender%2==1)
                    System.out.println("男");
                else
                    System.out.println("女");
            }
            
                
                
            if(i_n < 1890 && !isOld || i_n >2017  && !isOld || i_y < 1 || i_y > 12 || i_r < 1 || i_r > 31)
            {
                System.out.println("输入的身份证号码出生日期异常");
                return ;
            }
            else
            {
                System.out.println("出生日期："+s_n+"年"+s_y+"月"+s_r+"日");
            }
//            System.out.println(s_n);
//            System.out.println("身份证位数只能是15或18位");
        }
        else
        {
            System.out.println("身份证位数只能是15或18位");
            return ;
        }
        



        for( int j = 0 ; j < 3466 ; j++)
        {
            s = f1.readLine();
            t1 = s.substring(0,5);
            bm[j] = t1;
            t2 = s.substring(6);
            dz[j] = t2;
        }

        int fz = 0 ;
        boolean flag = false;
        String t3 = null;
        t3 = num.substring(0,5);
        for(int i = 0  ; i < 3466 ; i++)
        {
            if(t3.equals(bm[i]))
            {
                flag = true;
                fz = i;
                break;
            }
        }
        if(flag)
        {
            System.out.println("地址：" + dz[fz]);
        }
        else
        {
            System.out.println("没有这个身份证号码所对应的地址");
            return ;
        }
        
        if(num.length() == 15)
        {
        String s_xnum = null;
        int[] ai = new int[18];
        int[] wi = new int[18];
        s_xnum = num.substring(0,6)+"19"+num.substring(6,12)+num.substring(12,15)+"0";
//       System.out.println(s_xnum);
        int jym = 0 ;
        System.out.print("输入的号码位旧号码,转换为新号码为：");
        for(int i = 0 ; i < 18 ; i++)
        {
            char ch ;
            ch = s_xnum.charAt(i);
//            System.out.println(ch);
            String s_char = null;
            s_char = ""+ch;
            ai[i] = Integer.parseInt(s_char);
//            System.out.print(ai[i]+" ");
//            System.out.println(ai[i]);
            
//            wi[i] = ((int)(Math.pow(2,i))) % 11;
//            System.out.println(wi[i]);
        }
//            System.out.println();
        for(int i = 0 ; i < 18 ; i++)
        {
            wi[i] = ((int)(Math.pow(2,(17-i)))) % 11;
//            System.out.print(wi[i]+" ");
        }
//            for(int i = 0 ; i < 18 ; i++)   System.out.println(wi[i]);
        int sum = 0 ;
        for(int  i = 0 ; i < 18 ; i++)
        {
            sum +=( ai[i] * wi[i]);
        }
//           System.out.println();
        jym = sum % 11;
//        System.out.println(jym);
        String[] s_jym = {"1","0","X","9","8" ,"7", "6","5","4","3","2"};
        String x_num = null;
        System.out.println(s_xnum.substring(0,17)+s_jym[jym]);
        }

    }
}
