package Game1;

import java.util.Random;

public class Gametest {
    public static void main(String[] args) {
        Role r1 = new Role("勇者艾德里安", 100,0,'男', 100);
        Role r2 = new Role("哥布林", 20, 0,'男', 10);
        Role r3 = new Role("大哥布林", 40, 0,'男', 10);

        //展示角色信息
        r1.showRoleInfo();
        r3.showRoleInfo();

        //回合制
        while(true){
            Random r = new Random();
            int index = r.nextInt(5);
            String skill = Role.BasicSkills[index];
            while(true) {
                int index2 = r.nextInt(2);
                if (skill.equals("蓄力斩") && r1.getMagic() >= 8) {
                    r1.ChargeSlash(r3);
                    break;
                } else if (skill.equals("治疗术") && r1.getMagic() >= 8 && r1.getBlood() < 80) {
                    System.out.println(r1.getName() + "使用了治疗术，恢复了15点血量");
                    r1.setBlood(r1.getBlood() + 15);
                    r1.setMagic(r1.getMagic() - 8);
                    break;
                } else if (skill.equals("神圣庇护") && r1.getMagic() >= 12) {
                    System.out.println(r1.getName() + "使用了神圣庇护，防御力增加了10点");
                    r1.setDefense(r1.getDefense() + 10);
                    r1.setMagic(r1.getMagic() - 12);
                    break;
                } else if(skill.equals("魔法回复") && r1.getMagic() < 100) {
                    System.out.println(r1.getName() + "使用了魔法回复");
                    r1.setMagic(r1.getMagic() + 10);
                    break;
                }else{
                    System.out.println(r1.getName() + "使用了普通攻击");
                    r1.attack1(r3);
                    break;
                }
            }
            if(r3.getBlood() <= 0){
                System.out.println(r3.getName() + "被攻击了，"+ r3.getName() +"死亡");
                break;
            }
            r3.attack3(r1);
            if(r1.getBlood() <= 0){
                System.out.println(r1.getName() + "被攻击了，"+ r1.getName() +"死亡");
                break;
            }
        }
    }
}
