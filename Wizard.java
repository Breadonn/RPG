public class Wizard extends Character {
    private int max = 0;
    public Wizard(){
        super(200);
        changeClass(3);
    }

    public void attack(Character opp){
        super.attack(opp);
        if(max < 4){
            int boost = (int)((super.charAttack()) * 1.2);
            super.changeAtk(boost);
            max++;
        }
    }
}