package com.youngtak.algo;

import java.util.*;

public class B11720 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        
        int sum = 0;
        int t = sc.nextInt();
        String raw = sc.next();
        
        
        for (int i=0; i<t; i++) {
        	int num = (int)(raw.charAt(i) - '0');
            sum += num;
        }
        
        System.out.println(sum);
    }
}