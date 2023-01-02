/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author triet
 */

//su dung class nay de viet cac ham thu vien
//cho viec dung sau nay
public class MyTool {
    //ham nay de nhap 1 so tu ban phim
    //tra ve so da nhap
    //dieu kien kiem tra: so nhap >= min va <= max
    public static double inputNumber(String msg, double min, double max) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(msg);
        double number = sc.nextDouble();
        if( number < min || number > max ) throw new Exception();
        return number;
    }
    
    //ham nay de nhap 1 chuoi tu ban phim
    //tra ve chuoi da nhap
    //dieu kien validate:
    public static String inputString(String msg, String pattern, int minLenth)throws Exception
    {
        Scanner s = new Scanner(System.in);
        System.out.println(msg);
        String str = s.nextLine();
        str.trim();
        if( str.length() < minLenth) throw new Exception();
        if(!str.matches(pattern)) throw new Exception();
        return str;
    }
    
    public static boolean validPassword( String str, int minLen )
    {
        if( str.length() < minLen )
        {
            return false;
        }
        return str.matches(".*[a-zA-Z]+.*") && str.matches(".*[\\d]+.*") && str.matches(".*[\\d]+.*") && str.matches(".*[\\w]+.*");
        
    }
    
    public static Date parseDate( String dateStr, String dateFormat )
    {
        SimpleDateFormat df = (SimpleDateFormat)SimpleDateFormat.getInstance();
        df.applyPattern(dateFormat);
        try
        {
            long t =df.parse(dateStr).getTime();
            return new Date(t);
        }
        catch(ParseException e)
        {
            System.out.println(e);
        }
        return null;
    }
    
    public static String dataToStr( Date date, String format )
    {
        SimpleDateFormat df = (SimpleDateFormat)SimpleDateFormat.getInstance();
        df.applyPattern(format);
        try
        {
            String str =df.format(date);
            return str;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    
    public static boolean inputBoolean( String msg )
    {
        Scanner s = new Scanner(System.in);
        System.out.println(msg);
        String str = s.nextLine().trim();
        if( str.isEmpty() )
        {
            return false;
        }
        char c = str.trim().toUpperCase().charAt(0);
        return (c=='1'||c=='T'||c=='Y');
    }
    
    
}
