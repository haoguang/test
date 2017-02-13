/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author haoguang
 */
public class Person {
    private String name;
    private int tel;
    
    Person(String name, int tel){
        this.name = name;
        this.tel = tel;
    }
    
    Person(){
        this("",0);
    }
    
    
}
