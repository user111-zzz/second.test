package test3;

public class test {
    public static void main(String[] args) {
        Student s1 = new Student(); //构造方法为空参构造方法
        s1.setName("张三");
        s1.setAge(18);
        s1.setSex("男");

        System.out.println(s1.getName());
        System.out.println(s1.getAge());
        System.out.println(s1.getSex());

        Student s2 = new Student("李四", 19, "女"); //构造方法为有参构造方法

        System.out.println(s2.getName());
        System.out.println(s2.getAge());
        System.out.println(s2.getSex());
    }
}
