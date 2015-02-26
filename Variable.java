/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pk.edu.nust.seecs.bscs2.advancedprogramming.lab2;

/**
 *
 * @author test1
 */
public class Variable { //Add collections to this to make it generic. It should support boolean, float, double, long, String etc.
    
    private int value;
    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
    
}
