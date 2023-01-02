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
public class CDServices {
    private String url = "C:\\Users\\triet\\OneDrive\\Máy tính\\LAB\\Lab3\\CD_management.txt";
    private CD[] list;
    private int cdCounter;
    final private int MAX = 700;

    public CDServices() {
        list = new CD[MAX];
        cdCounter = 0;
    }
    
    public int getSize() {
        return cdCounter;
    }
    
    public CD searchCDbyID( int id )
    {
        if( cdCounter == 0 )
        {
            System.out.println("Have no any CD");
        }
        
        for( int i = 0; i < cdCounter; ++i )
        {
            if( list[i].getId() == id )
            {
                return list[i];
            }
        }
        return null;
        
    }
    
    public void searchCDbyTitle()
    {
        
        if( cdCounter == 0 )
        {
            System.out.println("Have no any CD");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Input CD title: ");
        String name = sc.nextLine();
        for( int i = 0; i < cdCounter; ++i )
        {
            if( list[i].getTitle().equals(name) )
            {
                list[i].output();
                return;
            }
        }
        System.out.println("Cannot found");
        
    }
    
    public boolean addCD( CD add )
    {
        if( cdCounter == MAX )
        {
            return false;
        }else
        {
            list[cdCounter] = add;
            ++cdCounter;
            return true;
        }
    }
    
    public void display()
    {
        if( cdCounter == 0 )
        {
            System.out.println("Have no CD");
            return;
        }
        System.out.println("CD LIST!!!");
        for( int i = 0; i < cdCounter; ++i )
        {
            list[i].output();
        }
    }
    
    
    public boolean removeCD()
    {
        if( cdCounter == 0 )
        {
            System.out.println("Failed");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Input ID of CD want to remove: ");
        int id = sc.nextInt();
        for( int i = 0; i < cdCounter; ++i )
        {
            if( list[i].getId() == id )
            {
                for( int j = i; j < cdCounter; ++ j )
                {
                    list[j] = list[j+1];
                }
                --cdCounter;
                return true;
            }
        }
        return false;
    }
    
    public boolean updateCD()
    {
        if( cdCounter == 0 )
        {
            System.out.println("Have no any CD");
            System.out.println("Failed");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Input ID of CD want to update: ");
        int id = sc.nextInt();
        
        if( searchCDbyID(id) != null )
        {
            System.out.println("UPDATE!!!");
            boolean flag = false;
            do
            {
                try
                {
                    String newName = inputString("Input collection name: ", "^[a-zA-Z]+||^.*$", 0);
                    if(!newName.matches(""))
                    {
                        searchCDbyID(id).setCollectionName(newName);
                    }
                    
                    String newType = inputString("Input type: ", "^[a-zA-Z]+||^.*$", 0);
                    if(!newType.matches(""))
                    {
                        searchCDbyID(id).setType(newType);
                    }
                    
                    String newTitle = inputString("Input title: ", "^[a-zA-Z]+||^.*$", 0);
                    if(!newTitle.matches(""))
                    {
                        searchCDbyID(id).setTitle(newTitle);
                    }
                    
                    String newUnitPrice = inputString("Input new unit price: ", "^[0-9]+||^.*$", 0);
                    if(!newUnitPrice.matches(""))
                    {
                        searchCDbyID(id).setUnitPrice(Integer.parseInt(newUnitPrice));
                    }
                    
                    String newId = inputString("Input new ID: ", "^[0-9]+||^.*$", 0);
                    if(!newId.matches(""))
                    {
                        searchCDbyID(id).setId(Integer.parseInt(newId));
                    }
                    
                    String newYear = inputString("Input new year: ", "^[0-9]+||^.*$", 0);
                    if(!newYear.matches(""))
                    {
                        searchCDbyID(id).setYear(Integer.parseInt(newYear));
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
    
    public void writeCD() throws Exception
    {
        if( list != null && cdCounter > 0 )
        {
            PrintWriter w = new PrintWriter(url);
            for( int i = 0; i < cdCounter; ++i )
            {
                w.println(list[i].toString());
            }
            w.close();
        }
    }
    
    
    public void readCD() throws Exception
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
            if(arr.length == 6)
            {
                CD p = new CD(arr[0], arr[1], arr[2], Integer.parseInt(arr[3].trim()), Integer.parseInt(arr[4].trim()), Integer.parseInt(arr[5].trim()));
                this.addCD(p);
            }
        }
        f.close();
        bf.close();
    }
    
    public void sortByName()
    {
        Comparator<CD> comparator = new Comparator<CD>() {
            @Override
            public int compare(CD t, CD t1) {
                return t.getCollectionName().compareToIgnoreCase(t1.getCollectionName());
            }
        };
        
    }  
}
