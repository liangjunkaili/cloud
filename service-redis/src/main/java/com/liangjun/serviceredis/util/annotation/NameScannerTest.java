package com.liangjun.serviceredis.util.annotation;

@NameScanner
public class NameScannerTest {
    @NameScanner
    private String name;

    @NameScanner
    private int age;

    @NameScanner
    public String getName(){
        return this.name;
    }

    @NameScanner
    public void setName(String name){
        this.name = name;
    }

    public static void main(String[] args){
        System.out.println("--finished--");
    }

}
