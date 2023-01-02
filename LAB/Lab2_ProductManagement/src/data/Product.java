/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import tools.MyTool;
import static tools.MyTool.inputNumber;
import static tools.MyTool.inputString;

/**
 *
 * @author triet
 */
public class Product {
    private String productID;
    private String productName;
    private double unitPrice;
    private int quantity;
    private String status;

    public Product() {
    }

    public Product(String productID, String productName, double unitPrice, int quantity, String status) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.status = status;
    }
    
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void input()
    {
        boolean flag = false;
        do
        {
            try
            {
                productID = inputString("Input product ID: ", "^[A-Za-z0-9]+$", 0);
                productName = inputString("Input product name: ", "^[A-Za-z0-9\\s]+$", 5);
                unitPrice = inputNumber("Input price [0-10000]: ", 0, 10000);
                quantity = (int)inputNumber("Input quantity [0-1000]: ", 0, 1000);
                status = inputString("Input status [Available or Not Available]: ", "Available|Not Available", 0);
                flag = true;
            }catch(Exception e)
            {
                System.out.println("Input error, please input right format again");
            }
        }while(!flag);
    }
    
    public void output()
    {
        String str = String.format("%s, %s, %f, %d, %s", productID, productName, unitPrice, quantity, status);
        System.out.println(str);
    }
    
    @Override
    public String toString() {
        String str = String.format("%s, %s, %f, %d, %s", productID, productName, unitPrice, quantity, status);
        return str;
    }
}
