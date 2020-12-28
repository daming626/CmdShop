import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        InputStream in=Class.forName("Test").getResourceAsStream("/user.xlsx");
        ReadUserExcel readExcel=new ReadUserExcel();
        User users[]=readExcel.readExcel(in);

        InputStream ins=Class.forName("Test").getResourceAsStream("/Product.xlsx");
        ReadProductExcel readProductExcel=new ReadProductExcel();
        Product products[]=readProductExcel.readExcel(ins);

        boolean bo=true;
        while(bo) {
            System.out.println("请输入用户名：");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.next();

            System.out.println("请输入密码：");
            String password = scanner.next();

            for (User user : users) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    System.out.println("登陆成功");
                    bo=false;
                    for (Product product:products){
                        System.out.print(product.getpId());
                        System.out.print("\t"+product.getpName());
                        System.out.print("\t"+product.getpDesc());
                        System.out.println("\t"+product.getpPrice());
                    }
                    break;
                } else {
                    System.out.println("登陆失败");
                }
            }
        }

    }
}
