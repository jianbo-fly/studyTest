package com.tangjianbo.testfinal;

import com.google.common.collect.Lists;

import java.util.*;

public class TestUntils {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        Hashtable hashtable = new Hashtable();
        Map  map = new TreeMap();
        HashSet set = new HashSet();
        set.add(1);
        LinkedList linkedList = new LinkedList();
        ArrayList list = Lists.newArrayList();
        try{
            System.out.println("执行return！");
            return;
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finall");
        }

    }
}
