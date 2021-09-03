public class Knight extends Character {
    public Knight(){
        super(200);
        int boost = (super.charHealth())+50;
        super.changeHp(boost);
        changeClass(4);
    }
    int totalHp = super.charHealth();
    public void attack(Character opp){
        int defense = opp.charDefense();
        int attack = super.charAttack();
        super.changeAtk(totalHp - super.charHealth());
        opp.changeDef(0);
        super.attack(opp);
        opp.changeDef(defense);
        super.changeAtk(attack);
    }
}