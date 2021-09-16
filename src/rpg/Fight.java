package rpg;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Fight {
    private Random random = new Random();
    int round = 0;
    //以下第二週新增
    private boolean isEscap = false;

    public boolean isEscap() {
        return isEscap;
    }

    public void setEscap(boolean escap) {
        isEscap = escap;
    }
    //以上第二週新增


    public boolean isEscaping(Player player, Monster monster) { //是否逃跑成功
        setEscap(false);
        if (random.nextDouble() <= (player.getAbility().getDex() - monster.getAbility().getDex()) * 0.4) { //(自身敏捷-怪物敏捷)*40%為逃跑率
            setEscap(true);
        }
        return isEscap();
    }

    public void startFight(Player player, Monster monster) throws InterruptedException {  //戰鬥開始
        Scanner sc = new Scanner(System.in);
        player.setFighting(true); //處於戰鬥狀態
        player.buffTakeEffect(monster); //buff生效
        //印出遇到怪物的資料


        if (player.getAbility().getDex() >= monster.getAbility().getDex()) { //判斷誰敏捷高誰先攻擊
            System.out.println("== " + player.getAbility().getName() + " 先攻==");//角色先攻

            while (true){
                round++;
                System.out.println("\n=====第" + round + "回合=====");
                //角色先攻 選擇普通攻擊 還是技能
                System.out.println("請選擇：\n" +
                        "1.普通攻擊\n" +
                        "2.技能選擇");
                int choose;
                try {
                    choose =  sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("沒有這個功能啦!快回去重來!");
                    sc.next();
                    continue;
                }  // 防呆(抓取可能輸入非數字的錯誤)

                switch (choose){
                    case 1:
                        //普通攻擊
                        System.out.println(player.getAbility().getName() + "攻擊!");
                        attack(player, monster);
                        if (!monster.isDead()) {  //怪物死了就不用繼續打下去
                            System.out.println(monster.getAbility().getName() + "攻擊!");
                            attack(monster, player);
                            break;
                        }else{
                            System.out.println("怪物判定死亡正常");
                            break;
                        }
                    case 2:
                        //技能攻擊
                        System.out.println("請選擇技能");
                        int whichSkill=sc.nextInt();;
                        System.out.println(player.getAbility().getName() + "施放" + "技能");
                        skillAttack(player, monster, whichSkill);


                        if (!monster.isDead()) {  //怪物死了就不用繼續打下去
                            System.out.println(monster.getAbility().getName() + "攻擊!");
                            attack(monster, player);
                            break;
                        }else{
                            System.out.println("怪物判定死亡正常");
                            break;
                        }
                    default:
                        System.out.println("選擇普通攻擊還是技能的功能異常");
                }
                if (choose == 1 || choose == 2){
                    break;
                }else{
                    player.buffCountDown(); //玩家buff扣除一回合
                    continue;
                }
            }



        } else {
            System.out.println("== " + monster.getAbility().getName() + " 先攻=="); //怪物先攻


            while (true){
                round++;
                System.out.println("\n=====第" + round + "回合=====");
                System.out.println(monster.getAbility().getName() + "攻擊!");
                attack(monster, player);

                //角色後攻且沒死 選擇普通攻擊 還是技能
                if (!player.isDead()) {
                    System.out.println("請選擇：\n" +
                            "1.普通攻擊\n" +
                            "2.技能選擇");
                    int choose;
                    try {
                        choose =  sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("沒有這個功能啦!快回去重來!");
                        sc.next();
                        continue;
                    }  // 防呆(抓取可能輸入非數字的錯誤)

                    switch (choose){
                        case 1:
                            //普通攻擊
                            System.out.println(player.getAbility().getName() + "攻擊!");
                            attack(player, monster);
                            if (!monster.isDead()) {  //怪物死了就不用繼續打下去
                                System.out.println(monster.getAbility().getName() + "攻擊!");
                                attack(monster, player);
                                break;
                            }else{
                                System.out.println("怪物判定死亡正常");
                                break;
                            }

                        case 2:
                            //技能攻擊
                            System.out.println("請選擇技能");
                            int whichSkill=sc.nextInt();;

                            System.out.println(player.getAbility().getName() + "施放" + "技能");
                            skillAttack(player, monster, whichSkill);



                            if (!monster.isDead()) {  //怪物死了就不用繼續打下去
                                System.out.println(monster.getAbility().getName() + "攻擊!");
                                attack(monster, player);
                                break;
                            }else{
                                System.out.println("怪物判定死亡正常");
                                break;
                            }

                        default:
                            System.out.println("選擇普通攻擊還是技能的功能異常");
                    }

                    if (choose == 1 || choose == 2){
                        player.buffCountDown(); //玩家buff扣除一回合
                        break;
                    }else{
                        continue;
                    }
                }
            }
            player.buffCountDown();
        }
//        round = 0;//round 歸零 //第二週：移到overFight內
//        player.removeBuff(); //buff移除 //第二週：移到overFight內
//        player.setFighting(false); //離開戰鬥狀態 //第二週：移到overFight內
//        overFight(player, monster); //結算
    }

    public boolean isHit(Character atker, Character defer) {
        return random.nextDouble() >= (defer.getAbility().getDex() - atker.getAbility().getHit()) * 0.2;///判斷如果隨機(0~1)大於(被攻擊方敏捷-攻擊方命中*20%)，就攻擊成功
    }

    public void attack(Character atker, Character defer) throws InterruptedException {
        //先手攻擊
        if (isHit(atker, defer)) { //判定命中
            Thread.sleep(1500);
            System.out.println("命中了");
            int damage = atker.getAbility().getStr() - defer.getAbility().getDef(); //計算傷害
            if (damage > 0) { //判定傷害是否>0
                defer.getAbility().addHp(damage * (-1)); //扣血(+負的血量)
                Thread.sleep(1500);
                System.out.println(atker.getAbility().getName() + "對" + defer.getAbility().getName() + "造成了" + damage + "傷害\n");
                if (defer.isDead()) { //死亡就跳出
                    return;
                }
            } else {
                Thread.sleep(1500);
                defer.getAbility().addHp(-1);
                System.out.println("被擋下來了，只扣一滴\n");  //被擋下，也有一滴傷害
                if (defer.isDead()) { //死亡就跳出
                    return;
                }
            }
        } else {
            Thread.sleep(1500);
            System.out.println("Miss!");
        }
    }

    public void overFight(Player player, Monster monster) throws InterruptedException {
        round = 0;//round 歸零
        player.removeBuff(); //buff移除
        player.setFighting(false); //離開戰鬥狀態
        if (player.isDead()) {
            System.out.println("你死了QQ，請重新來過吧\n\n");
        } else {
            int exp = monster.getAbility().getExp();
            Thread.sleep(1500);
            System.out.println("恭喜擊敗 " + monster.getAbility().getName() + " !");
            Thread.sleep(1500);
            player.getAbility().addExp(exp);
            System.out.print("獲得" + exp + "EXP   ");
            player.lvelUp();
            player.getItem(monster.getDropItem());
            System.out.println();
        }
    }

    public void skillAttack(Character atker, Character defer, int whichSkill) throws InterruptedException {
        //技能攻擊
        if (isHit(atker, defer)) { //判斷命中
            Thread.sleep(1500);
            System.out.println("命中了");
            int damage;
            if(whichSkill!=0){ //玩家指定技能
                damage = atker.getAbility().getSkill().get(whichSkill - 1).doItEffect(atker.getAbility().getSkill().get(whichSkill - 1).getId(), atker) - defer.getAbility().getDef();
                System.out.println(atker.getAbility().getSkill().get(whichSkill - 1).getName());
            }
            else{ //怪物觸發技能
                if(atker.getAbility().getSkill().size()==1){ //只有一項技能
                    // random num<機率
                    int randomNum = random.nextInt((100 - 1) + 1) + 1;
                    if(randomNum<atker.getAbility().getSkill().get(1).getProbability()){ //技能觸發
                        damage = atker.getAbility().getSkill().get(1).doItEffect(atker.getAbility().getSkill().get(0).getId(), atker) - defer.getAbility().getDef();
                        System.out.println(atker.getAbility().getSkill().get(0).getName());
                    }
                    else{ //普攻
                        damage = atker.getAbility().getStr() - defer.getAbility().getDef();
                        System.out.println("技能觸發失敗，採用普通攻擊");
                    }
                }
                else{ // 怪物有多個技能
                    int randomIndex = random.nextInt(atker.getAbility().getSkill().size());
                    int randomNum = random.nextInt((100 - 1) + 1) + 1;
                    if(randomNum < atker.getAbility().getSkill().get(randomIndex).getProbability()){
                        damage = atker.getAbility().getSkill().get(randomIndex).doItEffect(atker.getAbility().getSkill().get(randomIndex).getId(), atker) - defer.getAbility().getDef();
                        System.out.println(atker.getAbility().getSkill().get(randomIndex).getName());
                    }
                    else{
                        damage = atker.getAbility().getStr() - defer.getAbility().getDef();
                        System.out.println("技能觸發失敗，採用普通攻擊");
                    }
                }

            }
            if (damage > 0) { //判定傷害是否>0
                defer.getAbility().addHp(damage * (-1)); //扣血(+負的血量)
                Thread.sleep(1500);
                System.out.println(atker.getAbility().getName() + "對" + defer.getAbility().getName() + "造成了" + damage + "傷害\n");
                if (defer.isDead()) { //死亡就跳出
                    return;
                }
            } else {
                Thread.sleep(1500);
                defer.getAbility().addHp(-1);
                System.out.println("被擋下來了，只扣一滴\n");  //被擋下，也有一滴傷害
                if (defer.isDead()) { //死亡就跳出
                    return;
                }
            }
        }
        else{
            Thread.sleep(1500);
            System.out.println("Miss");
        }
    }
}