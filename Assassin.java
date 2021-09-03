public class Assassin extends Character{
    public Assassin(){
        super(200);
        changeClass(2);
    }
    public void attack(Character opp){
        int defense = opp.charDefense();
        opp.changeDef(0);
        super.attack(opp);
        opp.changeDef(defense);
    }
}