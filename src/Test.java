import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        InputStream in=Class.forName("Test").getResourceAsStream("/user.xlsx");
        ReadUserExcel readUserExcel=new ReadUserExcel();
        User users[]=readUserExcel.readUserExcel(in);

        InputStream ins=Class.forName("Test").getResourceAsStream("/Product.xlsx");
        ReadProductExcel readProductExcel=new ReadProductExcel();
        Product products[]=readProductExcel.getAllProduct(ins);

       /*for (User u:users){
            System.out.print(u.getUsername());
            System.out.print("\t"+u.getPassword());
            System.out.print("\t"+u.getAddress());
            System.out.println("\t"+u.getPhone());
        }*/

        boolean bo = true;
        while(bo) {
            System.out.println("请输入用户名：");
            Scanner scanner = new Scanner(System.in);
            String username = scanner.next();

            System.out.println("请输入密码：");
            String password = scanner.next();

            for (User user : users) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    System.out.println("登陆成功");
                    bo = false;
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
                    创建一个购物车的数组：存的是商品
                     */
                    Product carts[] = new Product[3];
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
                    System.out.println("继续购买商品请按1");
                    System.out.println("查看购物车请按2");
                    int choose=scanner.nextInt();
                    if(choose==1){
                        ins = null;
                        ins = Class.forName("Test").getResourceAsStream("/product.xlsx");
                       // readProductExcel = new ReadProductExcel();
                        products = readProductExcel.getAllProduct(ins);
                        for (Product p : products) {
                            System.out.print(p.getpId());
                            System.out.print("\t" + p.getpName());
                            System.out.print("\t" + p.getpPrice());
                            System.out.println("\t" + p.getpDesc());
                        }
                        System.out.println("请输入商品ID把该商品加入购物车");
                        pId = scanner.next();
                        ins = null;
                        ins = Class.forName("Test").getResourceAsStream("/product.xlsx");
                        product = readProductExcel.getProductById(pId, ins);
                        System.out.println("要购买的商品价格：" + product.getpPrice());
                        if(product!=null){
                            carts[count++]=product;
                        }
                    }else if(choose==2){
                        /*
                        查看购物车
                         */
                        System.out.println("购物车商品信息如下：");
                        for (Product p:carts){
                            if (p!=null){
                                System.out.print(p.getpId());
                                System.out.print("\t" + p.getpName());
                                System.out.print("\t" + p.getpPrice());
                                System.out.println("\t" + p.getpDesc());
                            }
                        }
                        /*
                        1、按1结账
                        2、按2继续购物
                         */
                        System.out.println("结账请按1");
                        System.out.println("继续添加物品请按2");
                        choose=scanner.nextInt();
                        if (choose==1){
                           /* int a=0;
                            for (Product p:carts){
                                a=a+p.getpPrice();
                            }
                            System.out.println("总价格为" + a);
*/
                        }else if(choose==2){
                            ins = null;
                            ins = Class.forName("Test").getResourceAsStream("/product.xlsx");
                            products = readProductExcel.getAllProduct(ins);
                            for (Product p:products){
                                System.out.print(p.getpId());
                                System.out.print("\t" + p.getpName());
                                System.out.print("\t" + p.getpPrice());
                                System.out.println("\t" + p.getpDesc());
                            }
                            System.out.println("请输入需要购买的商品的ID");
                            pId = scanner.next();
                            product = readProductExcel.getProductById(pId,ins);
                            System.out.println("要购买的商品的价格" + product.getpPrice());
                            if (product!=null){
                                carts[count++]=product;
                            }
                        }
                    }
                    break;
                } else {
                    System.out.println("登陆失败!用户名或密码错误！");
                }
            }
        }

    }
}
