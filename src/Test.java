import java.io.InputStream;
import java.util.Scanner;

public class Test {
    static Product carts[] = new Product[3];
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ClassNotFoundException {
        InputStream in=Class.forName("Test").getResourceAsStream("/user.xlsx");
        ReadUserExcel readUserExcel=new ReadUserExcel();
        User users[]=readUserExcel.readUserExcel(in);

        InputStream inss=Class.forName("Test").getResourceAsStream("/Product.xlsx");


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
                            shopping(inss);
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
                            int a=0;
                            int b=0;
                            float c=0;
                            float d=0;
                            for (Product o:carts){
                                if (o!=null) {
                                    if (o.getpId().equals("111")) {
                                        a += 1;
                                        c += Float.parseFloat(o.getpPrice());
                                        //c = Float.parseFloat(o.getpPrice()) * a;//覆盖前面的c值取最后一个
                                    } else if (o.getpId().equals("222")) {
                                        b += 1;
                                        d += Float.parseFloat(o.getpPrice());
                                        //d = Float.parseFloat(o.getpPrice()) * b;//覆盖前面的d值取最后一个
                                    }
                                }
                            }
                            order.setTotalPrice(c+d);
                            order.setFinalPay(c*1+d*1);//10折优惠
                            System.out.println("购买商品111的数量为：" + a);
                            System.out.println("购买商品222的数量为：" + b);
                            System.out.println("购买商品111的价格为：" + c);
                            System.out.println("购买商品222的价格为：" + d);
                            System.out.println("总价= "+order.getFinalPay());

                            /*
                            int a=0;
                            float b=0;
                            for (Product cart:carts){
                                if (cart!=null){
                                    a+=1;
                                    b+=Float.parseFloat(cart.getpPrice());
                                }
                            }
                            System.out.println("购买商品的数量为："+a);
                            System.out.println("商品总价"+b);
                            */

                            /*
                             统计每个商品的数量代码你去实现
                             */
                        }
                        else if (choose==4){
                            System.exit(0);
                        }
                    }
                    //break;
                }
                 else{
                    System.out.println("登陆失败!用户名或密码错误！");
                }
            }
        }
    }

    public static void shopping(InputStream inss) throws ClassNotFoundException {
        ReadProductExcel readProductExcel=new ReadProductExcel();
        Product products[]=readProductExcel.getAllProduct(inss);
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
        inss = null;
        inss = Class.forName("Test").getResourceAsStream("/product.xlsx");
        Product product = readProductExcel.getProductById(pId, inss);
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