import java.util.Scanner;
public class Game{
    public static void main(String[] args){
        int gameMode = 0;
        int turn = 1;
        int saved = 0;
        int save = 0;
        Character p1 = null;
        Character p2 = null;
        boolean validNum = false;
        Scanner input = new Scanner(System.in);
        int newLoad = 0;
        System.out.println("New game = 1, Load game = 2");
        newLoad = Integer.valueOf(input.next());
        if(newLoad == 1){
            while(validNum == false){
                System.out.println("Do you want to play as a Lich, Assassin, Wizard, or Knight?");
                System.out.println("1 = Lich, 2 = Assassin, 3 = Wizard, 4 = Knight");
                int type = Integer.valueOf(input.next());
                if(type == 1){
                    p1 = new Lich();
                    validNum = true;
                }else if(type == 2){
                    p1 = new Assassin();
                    validNum = true;
                }else if(type == 3){
                    p1 = new Wizard();
                    validNum = true;
                }else if(type ==4){
                    p1 = new Knight();
                    validNum = true;
                }else{
                    System.out.println("Not a valid number! Try again");
                }
            }
            System.out.println("Now, player two's turn");
            validNum = false;
            while(validNum == false){
		System.out.println("Do you want to play as a Lich, Assassin, Wizard, or Knight?");
                System.out.println("1 = Lich, 2 = Assassin, 3 = Wizard, 4 = Knight");
                int type = Integer.valueOf(input.next());
                if(type == 1){
                    p2 = new Lich();
                    validNum = true;
                }else if(type == 2){
                    p2 = new Assassin();
                    validNum = true;
                }else if(type == 3){
                    p2 = new Wizard();
                    validNum = true;
                }else if(type ==4){
                    p2 = new Knight();
                    validNum = true;
                }else{
                    System.out.println("Not a valid number! Try again");
                }
            }
            System.out.println("Do you want to play Co-op or VS?");
            System.out.println("1 = co-op, 2 = VS");
            gameMode = Integer.valueOf(input.next());
        }else{
            saved = 1;
            Character p1class = new Character();
            Character p2Class = new Character();
            p1class.loadClass("player1class");
            p2Class.loadClass("player2class");
            System.out.println(p1class.currentClass());
            System.out.println(p2Class.currentClass());
            if(p1class.currentClass()==1){
                p1 = new Ravi();
            }else if(p1class.currentClass()==2){
                p1 = new Shuri();
            }else if(p1class.currentClass()==3){
                p1 = new Luluca();
            }else if(p1class.currentClass()==4){
                p1 = new Krau();
            }
            if(p2Class.currentClass()==1){
                p2 = new Ravi();
            }else if(p2Class.currentClass()==2){
                p2 = new Shuri();
            }else if(p2Class.currentClass()==3){
                p2 = new Luluca();
            }else{
                p2 = new Krau();
            }
            p1.loadCharacter("player1savedata");
            p2.loadCharacter("player2savedata");
            gameMode = 1;
        }
        if(gameMode == 1){
            Character[] party = new Character[2];
            party[0] = p1;
            party[1] = p2;
            if(saved == 0){
                Character slime = new Character(130, false);
                Character goblin = new Character(150, false);
                System.out.println("A slime and a goblin appear!");
                while((goblin.charHealth()>0 || slime.charHealth()>0)&&(p1.charHealth()>0 || p2.charHealth()>0)){
                    if(turn == 1){
                        if(p1.charHealth()>0){
                            System.out.println("Who should "+p1+" attack?");
                            System.out.println("1 = slime, 2 = goblin");
                            int target = Integer.valueOf(input.next());
                            if(target == 1){
                                p1.attack(slime);
                                System.out.println("slime hp:"+slime.charHealth());
                            }else if(target == 2){
                                p1.attack(goblin);
                                System.out.println("goblin hp:"+goblin.charHealth());
                            }else{
                                System.out.println("Invalid response, skipping turn");
                            }
                        }
                        turn = 2;
                    }else if(turn == 2){
                        if(p2.charHealth()>0){
                            System.out.println("Who should "+p2+" attack?");
                            System.out.println("1 = slime, 2 = goblin");
                            int target = Integer.valueOf(input.next());
                            if(target == 1){
                                p2.attack(slime);
                                System.out.println("slime hp:"+slime.charHealth());
                            }else if(target == 2){
                                p2.attack(goblin);
                                System.out.println("goblin hp:"+goblin.charHealth());
                            }else{
                                System.out.println("Invalid response, skipping turn");
                            }
                        }
                        turn = 3;
                    }else if(turn == 3){
                        if(slime.charHealth()>0){
                            System.out.println("The slime attacks!");
                            int target = (int)(1.9*Math.random());
                            if(target == 0 && p1.charHealth()>0){
                                slime.attack(p1);
                                System.out.println(p1+"'s hp:"+p1.charHealth());
                            }else if(target == 1 && p2.charHealth()>0){
                                slime.attack(p2);
                                System.out.println(p2+"'s hp:"+p2.charHealth());
                            }else{
                                slime.attack(p1);
                                System.out.println(p1+"'s hp:"+p1.charHealth());
                            }
                        }
                        turn = 4;
                    }else{
                        if(goblin.charHealth()>0){
                            System.out.println("The goblin attacks!");
                            int target = (int)(1.9*Math.random());
                            if(target == 0 && p1.charHealth()>0){
                                goblin.attack(p1);
                                System.out.println(p1+"'s hp:"+p1.charHealth());
                            }else if(target == 1 && p2.charHealth()>0){
                                goblin.attack(p2);
                                System.out.println(p2+"'s hp:"+p2.charHealth());
                            }else{
                                goblin.attack(p1);
                                System.out.println(p1+"'s hp:"+p1.charHealth());
                            }
                        }
                        turn = 1;
                    }
                }
            }
            if(p1.charHealth()<0 && p2.charHealth()<0){
                System.out.println("Too bad, you lose :(");
            }else{
                if(saved!=1){
                    System.out.println("You won! Exp +100");
                    p1.gainExp(100);
                    p2.gainExp(100);
                    System.out.println("Save and Continue = 1, Save and quit = 2");
                    save = Integer.valueOf(input.next());
                }
                if(save == 2){
                    p1.saveCharacter("player1savedata");
                    p2.saveCharacter("player2savedata");
                    p1.saveClass("player1class");
                    p2.saveClass("player2class");
                }else{
                    int energy = 0;
                    boolean usedTeamAttack = false;
                    turn = 1;
                    Character BBK = new Character(200, true);
                    System.out.println("Boss Battle!");
                    System.out.println("You encountered Blood Blade Karin!");
                    System.out.println("Battle Begin!");
                    while(BBK.charHealth()>0 && (p1.charHealth()>0 || p2.charHealth()>0)){
                        if(energy == 4){
                            if(usedTeamAttack == false){
                                System.out.println("Would you like to use a team attack?");
                                System.out.println("1 = yes");
                                if(Integer.valueOf(input.next())==1){
                                    for(int i = 0; i<party.length;i++){
                                        party[i].attack(BBK);
                                    }
                                    usedTeamAttack = true;
                                    turn = 3;
                                }
                            }
                        }
                        if(turn == 1){
                            if(p1.charHealth() > 0){
                                p1.attack(BBK);
                                System.out.println("BBK's hp:"+BBK.charHealth());
                                turn = 2;
                                energy++;
                            }
                        }else if(turn == 2){
                            if(p2.charHealth() > 0){
                                p2.attack(BBK);
                                System.out.println("BBK's hp:"+BBK.charHealth());
                                turn = 3;
                                energy++;
                            }
                        }else{
                            if(BBK.charHealth() > 0){
                                if(p1.charHealth()>0){
                                    BBK.attack(p1);
                                    System.out.println(p1+"'s hp:"+p1.charHealth());
                                }else{
                                    BBK.attack(p2);
                                    System.out.println(p2+"'s hp:"+p2.charHealth());
                                }
                                turn = 1;
                            }
                        }
                    }
                    if(BBK.charHealth()<0){
                        System.out.println("Congratulations! you win!");
                    }else{
                        System.out.println("Too bad, you lose :(");
                    }
                }
            }
        }else if(gameMode == 2){
            System.out.println("Now, time for battle");
            while(p1.charHealth()>0 && p2.charHealth()>0){
                if(turn == 1){
                    p1.attack(p2);
                    p1.toString();
                    p2.toString();
                    turn = 2;
                }else if(turn == 2){
                    p2.attack(p1);
                    p2.toString();
                    p1.toString();
                    turn = 1;
                }
            }
            if(p2.charHealth()<=0){
                System.out.println("Player 1 wins!");
            }else if(p1.charHealth()<=0){
                System.out.println("Player 2 wins!");
            }
        }else{
            System.out.println("Nice one");
        }
    }

    public static void simulation(){
        Character one = new Character(50);
        Character two = new Character(50);
        int i = 0;
        int rounds = 0;            
        double oneWins = 0;
        int twoWins = 0;
        int turn = 1;
        for(i = 0; i<100;i++){
            while(one.charHealth()>0 &&two.charHealth()>0){
                rounds++;
                if(turn == 1){
                    one.attack(two);
                    turn = 2;
                }else if(turn == 2){
                    two.attack(one);
                    turn = 1;
                }
            }
            if(one.charHealth()>two.charHealth()){
                oneWins++;
            }else if(two.charHealth()>one.charHealth()){
                twoWins++;
            }
            one.changeHp(15);
            two.changeHp(15);
        }

        System.out.println("Player 1's wins:"+(int)oneWins);
        System.out.println("Player 2's wins:"+twoWins);
        System.out.println("Average rounds:"+(rounds/200));
        System.out.println("Win Percentage:"+(oneWins/100)*100+"%");
    }
}
