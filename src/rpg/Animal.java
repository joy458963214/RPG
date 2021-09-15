package rpg;

public class Animal extends Monster {
    private Item item = new Item();

    public Animal() {
        setKind(1);  // 初始化設定動物在地圖1(森林)
        item.leather();
        getDropItems().add(item);
    }

    public void wolf() {
        ability.setName("狼");
        ability.setHp(3);
        ability.setStr(6);
        ability.setDef(3);
        ability.setHit(5);
        ability.setDex(6);
        ability.setExp(5);
        ability.setLV(1);
        Item item = new Item();
        item.wolfLeg();
        getDropItems().add(item);
    }  // 設定狼的基本屬性&死後掉落物

    public void lion() {
        ability.setName("獅子");
        ability.setHp(4);
        ability.setStr(7);
        ability.setDef(4);
        ability.setHit(5);
        ability.setDex(4);
        ability.setExp(6);
        ability.setLV(1);
        Item item = new Item();
        item.lionClaw();
        getDropItems().add(item);
    }  // 設定獅子的基本屬性&死後掉落物

    public void boar() {
        ability.setName("山豬");
        ability.setHp(6);
        ability.setStr(5);
        ability.setDef(7);
        ability.setHit(4);
        ability.setDex(4);
        ability.setExp(5);
        ability.setLV(1);
        Item item = new Item();
        item.pork();
        getDropItems().add(item);
    }  // 設定山豬的基本屬性&死後掉落物

    public void elephant() {
        ability.setName("大象");
        ability.setHp(10);
        ability.setStr(6);
        ability.setDef(7);
        ability.setHit(7);
        ability.setDex(3);
        ability.setExp(10);
        ability.setLV(2);
        Item item = new Item();
        item.amimalSlayerMark();
        getDropItems().add(item);
    }  // 設定大象的基本屬性&死後掉落物
}
