/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import tools.MyTool;
import static tools.MyTool.inputString;


/**
 *
 * @author triet
 */
public class ProductList {
    private String url = "C:\\Users\\triet\\OneDrive\\Máy tính\\LAB\\Lab2_ProductManagement\\Product.txt";
    private ArrayList<Product> list;
    private int size;

    public ProductList() {
        list = new ArrayList<>();
        size = 0;
    }
    
    public int getSize() {
        return size;
    }
    
    public void checkExist()
    {
        ArrayList<Product> tempList = new ArrayList<>();
        try
        {
            FileReader f = new FileReader(url);
            BufferedReader bf = new BufferedReader(f);
            while(bf.ready())
            {
                String s = bf.readLine();
                String [] arr=s.split(",");
                if(arr.length == 5)
                {
                    Product p = new Product(arr[0], arr[1], Float.parseFloat(arr[2].trim()), Integer.parseInt(arr[3].trim()), arr[4]);
                    tempList.add(p);
                }
            }
            f.close();
            bf.close();
        }catch(Exception e)
        {
            System.out.println("ERROR");
        }
        if( tempList.size() == 0 )
        {
            System.out.println("Have no any Product in file");
            System.out.println("Failed");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Input name of product want to check: ");
        String find = sc.nextLine();
        for (Product product : tempList) {
            if( product.getProductName().trim().equals(find))
            {
                System.out.println("Exist Product");
                return;
            }
        }
        System.out.println("No Product Found!");
    }
    
    public Product searchProductByID( String id )
    {
        for (Product product : list) {
            if( product.getProductID().equals(id) )
            {
                return product;
            }
        }
        return null;
    }
    
    public void searchProductByName()
    {
        
        if( size == 0 )
        {
            System.out.println("Have no any Product");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Input search string of product want to search: ");
        String name = sc.nextLine();
        ProductList tempList = new ProductList();
        for (Product product : list) {
            if( product.getProductName().trim().contains(name) )
            {
                tempList.addProduct(product);
            }
        }
        if( tempList.getSize() == 0 )
        {
            System.out.println("Product not found");
        }
        else
        {
            tempList.sortByName();
            tempList.display();
        }
        
    }
    
    public boolean addProduct( Product add )
    {
        if( searchProductByID(add.getProductID()) != null )
        {
            System.out.println("Already existed ID");
            return false;
        }
        else
        {
            ++size;
            list.add(add);
            System.out.println("SUCCESSED");
            return true;
        }
    }
    
    public boolean removeProduct()
    {
        if( size == 0 )
        {
            System.out.println("Have no any Product");
            System.out.println("Failed");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Input ID of product want to remove: ");
        String id = sc.nextLine();
        if( searchProductByID(id) != null )
        {
            list.remove(searchProductByID(id));
            System.out.println("SUCCESSED");
            return true;
        }
        else
        {
            System.out.println("Productname does not exist");
            System.out.println("Failed");
            return false;
        }
    }
    
    public boolean updateProduct()
    {
        if( size == 0 )
        {
            System.out.println("Have no any Product");
            System.out.println("Failed");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Input ID of product want to update: ");
        String id = sc.nextLine();
        if( searchProductByID(id) != null )
        {
            System.out.println("UPDATE!!!");
            boolean flag = false;
            do
            {
                try
                {
                    String newName = inputString("Input new name: ", "^[a-zA-Z]+||^.*$", 0);
                    if(!newName.matches(""))
                    {
                        searchProductByID(id).setProductName(newName);
                    }
                    
                    String newPrice = inputString("Input new price: ", "^[0-9]+||^.*$", 0);
                    System.out.println(newPrice);
                    if(!newName.matches(""))
                    {
                        searchProductByID(id).setUnitPrice(Float.parseFloat(newPrice));
                    }
                    
                    String newQuantity = inputString("Input new quantity: ", "^[0-9]+||^.*$", 0);
                    System.out.println(newQuantity);
                    if(!newName.matches(""))
                    {
                        searchProductByID(id).setQuantity(Integer.parseInt(newQuantity));
                    }
                    
                    String newStatus = inputString("Input new status: ", "Available|Not Available||^.*$", 0);
                    if(!newName.matches(""))
                    {
                        searchProductByID(id).setStatus(newStatus);
                    }
                    flag = true;
                }catch(Exception e)
                {
                    System.out.println("Input error, please input right format again");
                }
            }while(!flag);
            System.out.println("SUCCESSED");
            return true;
        }
        else
        {
            System.out.println("Product id does not exist");
            System.out.println("Failed");
            return false;
        }
    }
    
    public void writeProduct() throws Exception
    {
        if( list != null && list.size() > 0 )
        {
            PrintWriter w = new PrintWriter(url);
            for(Product product:list)
            {
                w.println(product.toString());
            }
            
            w.close();
        }
    }
    
    
    public void readProduct() throws Exception
    {
        //mo file
        //doc file
        //dong file
        FileReader f = new FileReader(url);
        BufferedReader bf = new BufferedReader(f);
        while(bf.ready())
        {
            String s = bf.readLine();
            String [] arr=s.split(",");
            if(arr.length == 5)
            {
                Product p = new Product(arr[0], arr[1], Float.parseFloat(arr[2].trim()), Integer.parseInt(arr[3].trim()), arr[4]);
                this.addProduct(p);
            }
        }
        f.close();
        bf.close();
    }
    
    public void sortByName()
    {
        Comparator<Product> comparator = new Comparator<Product>()
        {
            @Override
            public int compare(Product p1, Product p2)
            {
                return p1.getProductName().compareTo(p2.getProductName());
            }
            
        };
        Collections.sort(list,comparator);
    }
    
    public void sortByQuantity()
    {
        Comparator<Product> comparator = new Comparator<Product>(){
            @Override
            public int compare(Product t, Product t1) {
                if( t.getQuantity() < t1.getQuantity() || t.getQuantity() == t1.getQuantity() && t.getUnitPrice() > t1.getUnitPrice())
                {
                    return 1;
                }
                return -1;
            }
        };
        Collections.sort(list,comparator);
    }
    
    public void display()
    {
        if( size == 0 )
        {
            System.out.println("Have no any Product");
            return;
        }
        System.out.println("PRODUCT LIST!!!");
        for (Product product : list) {
            product.output();
        }
    }
}
