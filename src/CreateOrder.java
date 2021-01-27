import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

import java.io.FileOutputStream;


public class CreateOrder {
    /**
     * Excel 文件要存放的位置，假定在F盘下
     */
    public static String outputFile = "F:\\test.xls";

    public static void createOrder(Order order) {
        try {
            // 创建新的Excel 工作簿
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 在Excel工作簿中建一工作表，其名为缺省值
            // 如要新建一名为"效益指标"的工作表，其语句为：
            HSSFSheet sheet = workbook.createSheet("订单");
            // 在索引0的位置创建行（最顶端的行）

            HSSFRow firstRow = sheet.createRow((short) 0);

            HSSFCell[] cell = new HSSFCell[6];

            for (int i = 0; i < cell.length; i++) {
                cell[i] = firstRow.createCell((short) i);
            }

            cell[0].setCellValue("用户");
            cell[1].setCellValue("商品");
            cell[2].setCellValue("数量");
            cell[3].setCellValue("总价");
            cell[4].setCellValue("最终价格");
            cell[5].setCellValue("订单信息");

            for (int i = 0; i < order.getProducts().length; i++) {
                HSSFRow row = sheet.createRow(i + 1);
                for (int j = 0; j < 6; j++) {
                    HSSFCell cell1 = row.createCell(j);
                    if (j == 0) {
                        cell1.setCellValue(order.getUser().getUsername());
                    } else if (j == 1) {
                        cell1.setCellValue(order.getProducts()[i].getpId());
                    } else if (j == 2) {
                        cell1.setCellValue(order.getProductAmmount());
                    } else if (j == 3) {
                        cell1.setCellValue(order.getTotalPrice());
                    } else if (j == 4) {
                        cell1.setCellValue(order.getFinalPay());
                    } else if (j == 5) {
                        cell1.setCellValue(order.getOrderDate());
                    }
                }
            }

            // 新建一输出文件流
            FileOutputStream fOut = new FileOutputStream(outputFile);
            // 把相应的Excel 工作簿存盘
            workbook.write(fOut);
            fOut.flush();
            // 操作结束，关闭文件
            fOut.close();
            System.out.println("文件生成...");
        } catch (Exception e) {
            System.out.println("已运行 xlCreate() : " + e);
        }
    }
}