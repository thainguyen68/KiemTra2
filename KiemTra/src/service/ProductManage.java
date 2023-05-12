package service;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductManage implements Manage<Product> {
    private Scanner scanner;
    private List<Product> productList = new ArrayList<>();


    public ProductManage(Scanner scanner) {
        this.scanner = scanner;
        productAdd();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void productAdd() {
        Product product1 = new Product(1, "ip1", 5.5, 9, "new");
        Product product2 = new Product(2, "ip2", 5, 8, "like-new");
        Product product3 = new Product(3, "ip3", 7.5, 7, "new");
        Product product4 = new Product(4, "ip4", 7, 6, "90%");
        Product product5 = new Product(5, "ip5", 6.8, 5, "80%");

        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
    }


    @Override
    public Product create() {
        System.out.println("Thêm sản phẩm mới: ");
        System.out.println("Mã sản phẩm:");
        int id = -1;
        try {
            id = Integer.parseInt(scanner.nextLine());
            boolean check = true;
            do {
                for (Product p : productList){
                    if (id!=p.getId()){
                        check = true;
                    }
                    else {
                        check = false;
                    }
                }
            }while (!check);
        } catch (NumberFormatException e) {
            System.out.println("Hãy nhập số !");
        }
        System.out.println("Tên sản phẩm mới:");
        String name = scanner.nextLine();
        System.out.println("Giá sản phẩm: ");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Số lượng sản phẩm: ");
        int quantity = 0;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Hãy nhập số !");
        }
        System.out.println("Mô tả sản phẩm: ");
        String description = scanner.nextLine();
        Product productNew = new Product(id, name, price, quantity, description);
        productList.add(productNew);
        return productNew;
    }

    @Override
    public Product update() {
        displayAll();
        Product product = getById();
        if (product != null) {
            System.out.println("Đổi tên sản phẩm: ");
            String name = scanner.nextLine();
            if (!name.equals("")) {
                product.setName(name);
            }

            System.out.println("Đổi giá sản phẩm: ");
            double price;
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price > 0) {
                    product.setPrice(price);
                }
            } catch (NumberFormatException e) {
                System.out.println("Hãy nhập số!");
            }

            System.out.println("Đổi số lượng sản phẩm: ");
            int quantity;
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity > 0) {
                    product.setPrice(quantity);
                }
            } catch (NumberFormatException e) {
                System.out.println("Hãy nhập số!");
            }

            System.out.println("Đổi mô tả sản phẩm: ");
            String description = scanner.nextLine();
            if (!description.equals("")) {
                product.setDescription(description);
            }
        }
        return product;
    }

    @Override
    public Product delete() {
        displayAll();
        Product product = getById();
        System.out.println("Bạn có muốn xóa ?");
        System.out.println("y: có");
        System.out.println("n: Không");
        String choose = scanner.nextLine();
        if (choose.equals("y") && product != null){
                productList.remove(product);
        }else {
            return null;
        }
        return product;
    }


    @Override
    public Product getById() {
        int id;
        do {
            try {
                System.out.println("Nhập mã bạn muốn: ");
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.err.println("Hãy nhập số!");
            }
        } while (true);
        boolean checkIdPro = false;
        do {
            for (Product product : productList) {
                if (product.getId() == id) {
                    checkIdPro = true;
                    return product;
                }
            }
            if (!checkIdPro) {
                System.out.println("Không tìm thấy mã");
                return getById();
            }
        } while (true);
    }


    @Override
    public void displayAll() {
        System.out.printf("%-15s%-17s%-20s%-20s%s",
                "Mã", "Tên", "Giá", "Số Lượng", "Mô tả\n");
        for (Product p : productList) {
            p.display();
        }
    }


    public void sortByPrice() {
        displayAll();
        int chooseSort = -1;
        do {
            System.out.println("1. Giá từ thấp tới cao ");
            System.out.println("2. Giá từ cao tới thấp ");
            System.out.println("3. Quay lại menu ");
            System.out.println("Nhập lựa chọn:");
            try {
                chooseSort = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Please re-enter the number!");
            }

            switch (chooseSort) {
                case 1:
                    sortByPriceLowToHigh();
                    break;
                case 2:
                    sortByPriceHighTolLow();
                    break;
                case 3:
                  break;
            }
        } while (chooseSort != 0);
    }

    public void sortByPriceLowToHigh() {
        productList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrice() > o2.getPrice()) return 1;
                else if (o1.getPrice() < o2.getPrice()) return -1;
                else return 0;
            }
        });
        displayAll();
    }

    public void sortByPriceHighTolLow() {
        productList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrice() > o2.getPrice()) return -1;
                else if (o1.getPrice() < o2.getPrice()) return 1;
                else return 0;
            }
        });
        displayAll();
    }

    public void searchByPrice() {
        double max = 0;
        for (Product p : productList) {
            if (p.getPrice() > max) {
                max = p.getPrice();
            }
        }
        for (Product p : productList) {
            if (p.getPrice() == max) {
                System.out.println("Sản phẩm có giá cao nhất:");
                System.out.printf("%-15s%-17s%-20s%-20s%s",
                        "Mã", "Tên", "Giá", "Số Lượng", "Mô tả\n");
                p.display();
            }
        }
    }

    public void write( List<Product> productList) {
       ;
        File file = new File("E:\\KiemTra2\\KiemTra\\src\\data\\Product.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Product p : productList) {
                bufferedWriter.write(p.toString() + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ioException) {
            System.out.println("Ghi file lỗi!");
        }

    }

    public List<Product> read() {
        File file = new File("E:\\KiemTra2\\KiemTra\\src\\data\\Product.txt");
        List<Product> productList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                String[] strings = data.split(",");
                Product product = new Product(Integer.parseInt(strings[0]), strings[1], Double.parseDouble(strings[3]),Integer.parseInt(strings[4]), strings[5]);
                productList.add(product);
            }
        } catch (IOException ioException) {
            System.err.println("Đọc file lỗi!");
        }
        return productList;
    }
}
