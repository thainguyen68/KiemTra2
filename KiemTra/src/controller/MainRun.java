package controller;

import model.Product;
import service.ProductManage;

import java.util.Scanner;

public class MainRun {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManage productManage = new ProductManage(scanner);
        int choice = -1;
        do {
                System.out.println("_ _ _ _ CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM _ _ _ _ _|");
            System.out.println("Chọn chức năng theo số ( để tiếp tục )");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Sắp xếp");
            System.out.println("6. Tìm sản phẩm có gí trị đắt nhất");
            System.out.println("7. Đọc từ file");
            System.out.println("8. Ghi từ file");
            System.out.println("9. Thoát");
            System.out.println("Chọn chức năng: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Please re-enter the number!");
            }

            switch (choice) {
                case 1:
                    productManage.displayAll();
                    break;
                case 2:
                    productManage.create();
                    break;
                case 3:
                    productManage.update();
                    break;
                case 4:
                    productManage.delete();
                    break;
                case 5:
                    productManage.sortByPrice();
                    break;
                case 6:
                    productManage.searchByPrice();
                    break;
                case 7:
                    productManage.read(productManage.getProductList());
                    break;
                case 8:
                    productManage.write(productManage.getProductList());
                    break;
                case 9:
                    System.exit(0);
            }
        } while (true);
    }
}
