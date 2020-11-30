package com.tangjianbo.concurrent;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import java.text.NumberFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 18482
 */
public class TestNull {
    public static int getTree(int x) {
        x = x * 3;
        return x;
    }


    public static void main(String[] args) {
        TreeMap treeMap = new TreeMap<>();
        treeMap.put(3, 3);
        treeMap.put(2, 2);
        treeMap.put(1, 1);
        Set<Entry> set = treeMap.entrySet();
        Iterator<Entry> i = set.iterator();
        while (i.hasNext()) {
            Entry entry = i.next();
            System.out.println(entry.getKey().toString() + entry.getValue().toString());
        }
        HashMap map = new HashMap();
        Hashtable hashtable = new Hashtable();
        int a = 2;
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(1, 4);
        System.out.println(integers);
        getTree(a);
        System.out.println(getTree(a));
        System.out.println(a);
        Student student = new Student();
        Student student2 = new Student();
        System.out.println(student.hashCode());
        System.out.println(student2.hashCode());
        List list = Lists.newArrayList();
        student.getM().put(1, 1);
        System.out.println(student.getM().get(1));
        NumberFormat currencyNumberFormat = NumberFormat.getCurrencyInstance();

    }

    static class Student {
        private final HashMap m = new HashMap();
        private String s = "123";
        private String name;
        private Integer age;
        private int weight;

        public HashMap getM() {
            return m;
        }

        public void setS(String s) {
            this.s = s;
        }

        public String getS() {
            return s;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return weight == student.weight &&
                    Objects.equal(name, student.name) &&
                    Objects.equal(age, student.age);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name, age, weight);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", weight=" + weight +
                    '}';
        }
    }
}
