/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mng;

import data.CD;
import data.CDServices;
import java.util.Scanner;

/**
 *
 * @author triet
 */
public class main {
    public static void run(){
        CDServices list = new CDServices();
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do
        {
            System.out.println("1. Add CD to the catalog");
            System.out.println("2. Search CD by CD title");
            System.out.println("3. Display the catalog");
            System.out.println("4. Update CD");
            System.out.println("5. Save account to file");
            System.out.println("6. Print list CDs from file.");
            System.out.println("Others- Quit");
            System.out.println("Input your choice: ");
            choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                {
                    System.out.println("ADD NEW CD!!!");
                    CD newCD = new CD();
                    newCD.input();
                    list.addCD(newCD);
                    break;
                }
                case 2:
                {
                    list.searchCDbyTitle();
                    break;
                }
                case 3:
                {
                    list.display();
                    break;
                }
                case 4:
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
                            list.updateCD();
                            break;
                        }
                        case 2:
                        {
                            list.removeCD();
                            break;
                        }
                    }
                    break;
                }
                case 5:
                {
                    try
                    {
                        list.writeCD();
                    }catch(Exception e)
                    {
                        System.out.println("Error");
                    }
                    System.out.println("Done");
                    break;
                }
                case 6:
                {
                    try
                    {
                        list.readCD();
                    }catch(Exception e)
                    {
                        System.out.println("Error");
                    }
                    list.sortByName();
                    list.display();
                    break;
                }
            }
        }while( choice >= 1 && choice < 7 );
    }
    
    public static void main(String[] args) {
        run();
    }
}
