package Game1;

import java.util.Random;

public class Role {
    private String name;
    private int blood;
    private int defense;
    private char gender;
    private int magic;

    public static String[] BasicSkills = {"蓄力斩","治疗术","神圣庇护","魔法回复","普通攻击"};
    public static String[] monsters = {"哥布林","大哥布林"};
    // 空参构造方法
    public Role() {
    }

    public Role(String name, int blood, int defense, char gender, int magic) {
        this.name = name;
        this.blood = blood;
        this.defense = defense;
        this.gender = gender;
        this.magic = magic;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    //方法
    public void attack1(Role role) {
        Random r = new Random();
        int hurt = r.nextInt(10) + 1;
        int remainblood = role.getBlood() - hurt;
        remainblood = remainblood < 0 ? 0 : remainblood;
        role.setBlood(remainblood);

        System.out.println(this.getName() + "攻击了" + role.getName()
                + "，造成了" + hurt + "点伤害，" + role.getName() + "剩余" + remainblood + "点血量");
    }

    public void attack2(Role role) {
        Random r = new Random();
        int hurt = r.nextInt(5) + 1;
        hurt -= role.getDefense();
        if(hurt < 0){
            hurt = 0;
        }
        int remainblood = role.getBlood() - hurt;
        remainblood = remainblood < 0 ? 0 : remainblood;
        role.setBlood(remainblood);

        System.out.println(this.getName() + "攻击了" + role.getName()
                + "，造成了" + hurt + "点伤害，" + role.getName() + "剩余" + remainblood + "点血量");
    }

    public void attack3(Role role) {
        Random r = new Random();
        int hurt = r.nextInt(8) + 1;
        hurt -= role.getDefense();
        if(hurt < 0){
            hurt = 0;
        }
        int remainblood = role.getBlood() - hurt;
        remainblood = remainblood < 0 ? 0 : remainblood;
        role.setBlood(remainblood);

        System.out.println(this.getName() + "攻击了" + role.getName()
                + "，造成了" + hurt + "点伤害，" + role.getName() + "剩余" + remainblood + "点血量");
    }

    public void showRoleInfo(){
        System.out.println("名称为: " + getName());
        System.out.println("血量为: " + getBlood());
        System.out.println("性别为: " + getGender());
    }

    //技能 蓄力斩
    public void ChargeSlash(Role role){
        System.out.print(this.getName() + "使用了技能: " + "<蓄力斩>");
        this.magic -= 8;
        Random r = new Random();
        int hurt = r.nextInt(5) + 18;
        int remainblood = role.getBlood() - hurt;
        remainblood = remainblood < 0 ? 0 : remainblood;
        role.setBlood(remainblood);

        System.out.println("，对" + role.getName()
                + "，造成了" + hurt + "点伤害，" + role.getName() + "剩余" + remainblood + "点血量");
    }
}
//    public void useSkill() {
//        System.out.println(a + "使用了技能" + skill);
//    }
