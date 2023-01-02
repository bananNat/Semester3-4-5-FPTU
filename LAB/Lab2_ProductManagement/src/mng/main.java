/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import data.Product;
import data.ProductList;
import java.util.Scanner;
import tools.MyTool;
import static tools.MyTool.inputNumber;

/**
 *
 * @author triet
 */
public class main {
    public static void main(String[] args) {
        ProductList list = new ProductList();
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do
        {
            System.out.println("1. Print");
            System.out.println("2. Create a Product");
            System.out.println("3. Check exits Product");
            System.out.println("4. Search Product’ information by Name");
            System.out.println("5. Update Product");
            System.out.println("6. Save Products to file");
            System.out.println("7. Print list Products from the file");
            System.out.println("Others- Quit");
            System.out.println("Input your choice: ");
            choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                {
                    list.display();
                    break;
                }
                case 2:
                {
                    System.out.println("CREATE NEW PRODUCT!!!");
                    Product newProduct = new Product();
                    newProduct.input();
                    list.addProduct(newProduct);
                    break;
                }
                case 3:
                {
                    list.checkExist();
                    break;
                }
                case 4:
                {
                    list.searchProductByName();
                    break;
                }
                case 5:
                {
                    System.out.println("5.1. Update product");
                    System.out.println("5.2. Delete product");
                    int subchoice;
                    System.out.println("Input your choice[1-2]: ");
                    Scanner s = new Scanner(System.in);
                    subchoice = s.nextInt();
                    switch(subchoice)
                    {
                        case 1:
                        {
                            list.updateProduct();
                            break;
                        }
                        case 2:
                        {
                            list.removeProduct();
                            break;
                        }
                    }
                    break;
                }
                case 6:
                {
                    try
                    {
                        list.writeProduct();
                    }catch(Exception e)
                    {
                        System.out.println("Error");
                    }
                    System.out.println("Done");
                    break;
                }
                case 7:
                {
                    try
                    {
                        list.readProduct();
                    }catch(Exception e)
                    {
                        System.out.println("Error");
                    }
                    list.sortByQuantity();
                    list.display();
                    break;
                }
            }
        }while( choice >= 1 && choice < 8 );
    }
}
