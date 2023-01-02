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
public class CD {
    private String collectionName;
    private String type;
    private String title;
    private int unitPrice;
    private int id;
    private int year;

    public CD() {
    }

    public CD(String collectionName, String type, String title, int unitPrice, int id, int year) {
        this.collectionName = collectionName;
        this.type = type;
        this.title = title;
        this.unitPrice = unitPrice;
        this.id = id;
        this.year = year;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    
    
    public void input()
    {
        boolean flag = false;
        do
        {
            try
            {
                collectionName = inputString("Input collection[game|movie|music]: ", "game|movie|music", 4);
                type = inputString("Input type[audio|video]: ", "audio|video", 5);
                title = inputString("Input title: ", "^[A-Za-z0-9\\s]+$", 0);
                unitPrice = (int)inputNumber("Input unit price: ", 0, 1000);
                id = (int)inputNumber("Input ID: ", 0, 1000);
                year = (int)inputNumber("Input year: ", 0, 3000);
                flag = true;
            }catch(Exception e)
            {
                System.out.println("Error");
            }
        }while(!flag);
    }
    
    public void output()
    {
        String str = String.format("%-10s%-10s%-20s%-5d%-5d%-5d", collectionName, type, title, unitPrice, id, year );
        System.out.println(str);
    }

    @Override
    public String toString() {
        String str = String.format("%-10s,%-10s,%-20s,%-5d,%-5d,%-5d", collectionName, type, title, unitPrice, id, year );
        return str;
    }
    
    
    
}
