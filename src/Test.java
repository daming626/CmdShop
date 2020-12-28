import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        InputStream in=Class.forName("Test").getResourceAsStream("/user.xlsx");
        ReadExcel readExcel=new ReadExcel();
        User users[]=readExcel.readExcel(in);

        System.out.println("请输入用户名：");
        Scanner scanner=new Scanner(System.in);
        String username=scanner.next();

        System.out.println("请输入密码：");
        String password=scanner.next();

        for (User user:users){
            if(username.equals(user.getUsername())&&password.equals(user.getPassword())) {
                System.out.println("登陆成功");
                break;
            }
            else {
                System.out.println("登陆失败");
            }
        }

    }
}
