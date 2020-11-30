package com.tangjianbo.testfinal;

import com.tangjianbo.concurrent.TestNull;

import java.util.HashMap;

/**
 * @author 18482
 */
public class TestFinal {
    private static final Student map = new Student("1") ;
    private static final String str = "66" ;

    public static void main(String[] args) {
        map.setName("2");
        String s1= "123";
        s1 = "1212";
        System.out.println(s1);
     //   str ="77";
        System.out.println(map.getName());
    }


    public static class Student {
        private String name;

        Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
