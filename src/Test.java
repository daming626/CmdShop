import java.io.InputStream;
import java.util.Scanner;

public class Test {
    static Product carts[] = new Product[3];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ClassNotFoundException {
        InputStream in=Class.forName("Test").getResourceAsStream("/user.xlsx");
        ReadUserExcel readUserExcel=new ReadUserExcel();
        User users[]=readUserExcel.readUserExcel(in);

        InputStream ins=Class.forName("Test").getResourceAsStream("/Product.xlsx");


       /*for (User u:users){
            System.out.print(u.getUsername());
            System.out.print("\t"+u.getPassword());
            System.out.print("\t"+u.getAddress());
            System.out.println("\t"+u.getPhone());
        }*/

        boolean bo = true;
        while(bo) {
            System.out.println("请输入用户名：");

            String username = scanner.next();

            System.out.println("请输入密码：");
            String password = scanner.next();

            for (User user : users) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    System.out.println("登陆成功");
                    bo = false;
                    while(true) {
                        System.out.println("购买商品请按1");
                        System.out.println("查看购物车请按2");
                        System.out.println("结账请按3");
                        System.out.println("退出请按4");
                        int choose=scanner.nextInt();
                        if (choose==1){
                            shopping(ins);
                        }
                        else if (choose == 2) {
                                    /*
                                    查看购物车
                                     */
                            System.out.println("购物车商品信息如下：");
                            viewCart();
                        }
                        else if (choose==3){
                            Order order=new Order();
                            order.setUser(user);
                            order.setProducts(carts);
                            /*
                             统计每个商品的数量代码你去实现
                             */
                        }
                        else if (choose==4){
                            System.exit(0);
                        }
                    }
                    break;
                }
                 else{
                    System.out.println("登陆失败!用户名或密码错误！");
                }
            }
        }
    }

    public static void shopping(InputStream ins) throws ClassNotFoundException {
        ReadProductExcel readProductExcel=new ReadProductExcel();
        Product products[]=readProductExcel.getAllProduct(ins);
        for (Product product:products){
        System.out.print(product.getpId());
        System.out.print("\t"+product.getpName());
        System.out.print("\t"+product.getpDesc());
        System.out.println("\t"+product.getpPrice());
        }
        System.out.println("请输入商品ID");
        String pId=scanner.next();
        int count = 0;
                        /*
                        根据此ID去Excel中去查找是否有该ID的商品信息，如果有则返回该商品即可
                         */
        ins = null;
        ins = Class.forName("Test").getResourceAsStream("/product.xlsx");
        Product product = readProductExcel.getProductById(pId, ins);
        System.out.println("要购买的商品价格：" + product.getpPrice());
        if(product!=null){
        carts[count++]=product;
        }
    }

    public static void viewCart(){
        for (Product p : carts) {
            if (p != null) {
            System.out.print(p.getpId());
            System.out.print("\t" + p.getpName());
            System.out.print("\t" + p.getpPrice());
            System.out.println("\t" + p.getpDesc());
            }
        }
    }
}