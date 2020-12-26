import java.io.File;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\欧家明\\ComShop\\src\\user.xlsx");
        ReadExcel readExcel=new ReadExcel();
        User users[]=readExcel.readExcel(file);

        System.out.println("请输入用户名：");
        Scanner scanner=new Scanner(System.in);
        String username=scanner.next();

        System.out.println("请输入密码：");
        String password=scanner.next();

        for (User user:users){
            if(username.equals(user.getUsername())&&password.equals(user.getPassword()))
                System.out.println("登陆成功");
            else
                System.out.println("登陆失败");
        }

    }
}
