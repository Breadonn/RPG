import java.util.Scanner;
public class Character{
    Scanner input = new Scanner(System.in);
    private int charClass;
    private int exp = 0;
    private int lvl = 1;
    private int atk;
    private int def;
    private int hp;
    private String name;

    public Character(String name, int str, int def, int hp){
        this.name = name;
        atk = str;
        this.def = def;
        this.hp = hp;
    }

    public Character(){
        atk = (int)(Math.random()*16);
        def = 25 - atk;
        hp = 150;
    }

    public Character(int statTotal){
        double val;
        int dice = (int)(Math.random()*3);
        if(dice == 0){
            val = 2.2;
        }else if(dice == 1){
            val = 2.3;
        }else{
            val = 2.4;
        }
        def = (int)(statTotal/val);
        atk = statTotal-def;
        hp = 200;        
        System.out.println("What is your character's name?");
        name = input.next();
        System.out.println(name);
        System.out.println("Attack:"+atk);
        System.out.println("Defense:"+def);
        System.out.println("Health:"+hp);
    }

    public Character(int statTotal, boolean boss){
        double val;
        int dice = (int)(Math.random()*3);
        if(dice == 0){
            val = 2.2;
        }else if(dice == 1){
            val = 2.3;
        }else{
            val = 2.4;
        }
        def = (int)(statTotal/val);
        atk = statTotal-atk;
        hp = statTotal;
        if(boss == true){
            atk -= 40;
            hp += 100;
        }
    }

    public int charAttack(){
        return atk;
    }

    public int charDefense(){
        return def;
    }

    public int charHealth(){
        return hp;
    }
    
    public int currentClass(){
        return charClass;
    }

    public void changeHp(int newHp){
        hp = newHp;
    }

    public void changeDef(int newDef){
        def = newDef;
    }

    public void changeAtk(int newAtk){
        atk = newAtk;
    }

    public void changeName(String newName){
        name = newName;
    }
    
    public void changeClass(int newClass){
        charClass = newClass;
    }

    public void gainExp(int earnedExp){
        int levelUp = 20;
        exp += earnedExp;
        while(exp > levelUp){
            exp -= levelUp;
            lvl +=1;
            levelUp = 20 + (20 * lvl -1);
            atk += 5;
            def += 5;
            System.out.println("Level up! "+(lvl-1)+" -> "+lvl);
            System.out.println("You gained 5 atk and def");
        }
    }   
    
    public void saveClass(String fileName){
        MediaFile.setOutputFile(fileName);
        MediaFile.writeString("%"+charClass, true);
        MediaFile.saveAndClose();
    }
    
    public void loadClass(String fileName){
        MediaFile.setInputFile(fileName);
        String theClass = MediaFile.readString();
        charClass = Integer.valueOf(theClass.substring(theClass.indexOf("%")+1));
    }

    public void saveCharacter(String fileName){
        MediaFile.setOutputFile(fileName);
        MediaFile.writeString(name, false);
        MediaFile.writeString("0"+atk, false);
        MediaFile.writeString("0"+def, false);
        MediaFile.writeString("0"+hp, false);
        MediaFile.writeString("0"+lvl, false);
        MediaFile.writeString("0"+exp, false);
        MediaFile.saveAndClose();
    }

    public void loadCharacter(String fileName){
        MediaFile.setInputFile(fileName);
        String saveData = MediaFile.readString();
        if(saveData != null){
            for(int i = 0; i < 6; i++){
                if(i==0){
                    name = saveData.substring(0, saveData.indexOf("|"));
                }else if(i==1){
                    atk = Integer.valueOf(saveData.substring(0, saveData.indexOf("|")));
                }else if(i==2){
                    def = Integer.valueOf(saveData.substring(0, saveData.indexOf("|")));
                }else if(i==3){
                    hp = Integer.valueOf(saveData.substring(0, saveData.indexOf("|")));
                }else if(i==4){
                    lvl = Integer.valueOf(saveData.substring(0, saveData.indexOf("|")));
                }else if(i==5){
                    exp = Integer.valueOf(saveData.substring(0, saveData.indexOf("|")));
                }
                saveData = saveData.substring(saveData.indexOf("|")+1);
            }
        }
    }

    public void attack(Character opp){
        double critCheck = Math.random();
        int damage = (int)(Math.random()*atk-Math.random()*opp.charDefense());
        if(critCheck <= 0.05){
            damage *= 1.5;
            System.out.println("Critical hit!");
        }
        if(damage > 0){
            opp.hp -= damage;
        }else{
            opp.hp -= 1;
        }
    }

    public String toString() {
        return name;
    }
}
