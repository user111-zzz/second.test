package test1;

import java.util.Scanner;

public class variableDemo1 {
    public static void main(String[] args) {
        int a = 10;
        System.out.println(a);

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个整数:");
        int b = sc.nextInt();
        System.out.println("你输入的整数是: " + b);
    }
}
