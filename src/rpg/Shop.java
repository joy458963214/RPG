package rpg;

import java.util.ArrayList;

public class Shop {
    ArrayList<Item> itemList = new ArrayList<Item>();
    public enum OptionType {
        i1(new Item().healingPotion()),
        i2(new Item().powerIncreasePotion()),
        i3(new Item().defenceIncreasePotion()),
        i4(new Item().leather()),
        i5(new Item().wolfLeg()),
        i6(new Item().lionClaw()),
        i7(new Item().pork()),
        i8(new Item().amimalSlayerMark()),
        i9(new Item().heartOfGhost()),
        i10(new Item().wolfTooth()),
        i11(new Item().glass()),
        i12(new Item().demomSlayerMark()),
        i13(new Item().intelliIncreasePotion()),
        i14(new Item().strongAcid()),
        i15(new Item().PowerBurst()),
        i16(new Item().WisdomBurst());

        Item item;
        OptionType(Item item){
            this.item = item;
        }

    }
    public interface Action{
        Item exec(Item item);
    }
    public  void printAllItem () {//印出所有Item
        int size = 16;
        OptionType[] array = OptionType.values();
        for (int i = 0 ; i< array.length ; i ++){
            Item temp = array[i].item;
            temp.printItem();
            itemList.add(temp);
        }
//
    }

}



    //顯示所有i商品
    //買商品
    //賣商品

//原始版本
//        Item i1 = new Item();
//        i1.healingPotion();
//        i1.printItem();
//        itemList.add(i1);
//
//        Item i2 = new Item();
//        i2.powerIncreasePotion();
//        i2.printItem();
//        itemList.add(i2);
//
//        Item i3 = new Item();
//        i3.defenceIncreasePotion();
//        i3.printItem();
//        itemList.add(i3);
//
//        Item i4 = new Item();
//        i4.leather();
//        i4.printItem();
//        itemList.add(i4);
//
//        Item i5 = new Item();
//        i5.wolfLeg();
//        i5.printItem();
//        itemList.add(i5);
//
//        Item i6 = new Item();
//        i6.lionClaw();
//        i6.printItem();
//        itemList.add(i6);
//
//        Item i7 = new Item();
//        i7.pork();
//        i7.printItem();
//        itemList.add(i7);
//
//        Item i8 = new Item();
//        i8.amimalSlayerMark();
//        i8.printItem();
//        itemList.add(i8);
//
//        Item i9 = new Item();
//        i9.heartOfGhost();
//        i9.printItem();
//        itemList.add(i9);
//
//        Item i10 = new Item();
//        i10.wolfTooth();
//        i10.printItem();
//        itemList.add(i10);
//
//        Item i11 = new Item();
//        i11.glass();
//        i11.printItem();
//        itemList.add(i11);
//
//        Item i12 = new Item();
//        i12.demomSlayerMark();
//        i12.printItem();
//        itemList.add(i12);
//
//        Item i13 = new Item();
//        i13.healingPotion();
//        i13.printItem();
//        itemList.add(i13);
//
//        Item i14 = new Item();
//        i14.intelliIncreasePotion();
//        i14.printItem();
//        itemList.add(i14);
//
//        Item i15 = new Item();
//        i15.strongAcid();
//        i15.printItem();
//        itemList.add(i15);
//
//        Item i16 = new Item();
//        i16.PowerBurst();
//        i16.printItem();
//        itemList.add(i16);
//
//        Item i17 = new Item();
//        i17.WisdomBurst();
//        i17.printItem();
//        itemList.add(i17);