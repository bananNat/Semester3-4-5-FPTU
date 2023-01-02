/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 * @author triet
 */
public class ItemList extends ArrayList<Patient> {
    public void addItem( Patient item)
    {
        this.add(item);
        System.out.println(this.get(0));
    }
}
