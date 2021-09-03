public class Lich extends Character {
    public Lich(){
        super(200);
        changeClass(1);
    }

    public void attack(Character opp){
        int lifeSteal = opp.charHealth();
        super.attack(opp);
        lifeSteal -= opp.charHealth();
        lifeSteal /= 2;
        super.changeHp(super.charHealth() + lifeSteal);
        if(super.charHealth()>200){
            super.changeHp(200);
        }
    }
}
