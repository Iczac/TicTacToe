package com.rixig;


import java.util.*;

public class Process {

    private String[][] ColArray = new String[3][3];
    private String xTurn;
    private int wonX;
    private int wonO;

//    Constructor

    public Process(){
        this.ColArray[0][0]= "   ";
        this.ColArray[0][1]= "   ";
        this.ColArray[0][2]= "   ";
        this.ColArray[1][0]= "   ";
        this.ColArray[1][1]= "   ";
        this.ColArray[1][2]= "   ";
        this.ColArray[2][0]= "   ";
        this.ColArray[2][1]= "   ";
        this.ColArray[2][2]= "   ";
        this.wonO = 0;
        this.wonX = 0;
    }

//    Rendering Playboard

    public void renderBoard() {
        System.out.println("+"+"---+"+"---+"+"---+");
        System.out.println("|"+this.ColArray[0][0]+"|"+this.ColArray[0][1]+"|"+this.ColArray[0][2]+"|");
        System.out.println("+"+"---+"+"---+"+"---+");
        System.out.println("|"+this.ColArray[1][0]+"|"+this.ColArray[1][1]+"|"+this.ColArray[1][2]+"|");
        System.out.println("+"+"---+"+"---+"+"---+");
        System.out.println("|"+this.ColArray[2][0]+"|"+this.ColArray[2][1]+"|"+this.ColArray[2][2]+"|");
        System.out.println("+"+"---+"+"---+"+"---+");
    }


//    Inserting values to Column

    public int insertCol() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your Location. Eg. 2,3");
        String[] input = sc.next().split(",");
        int colX,colY;
        colX = colY = 0;

        if (input.length < 2) {
            System.out.println("Input must be Numbers separated by ',' Eg. 2,3\n");
            return 1;
        }

        if (!input[0].trim().equals("") && !input[1].trim().equals("")) {
            colX = Integer.parseInt(input[0]) - 1;
            colY = Integer.parseInt(input[1]) - 1;
        } else {
            System.out.println("Input must be Numbers separated by ',' Eg. 2,3\n");
            return 1;
        }

        if ((colX >= 3) || (colY >= 3)) {
            System.out.println("Invalid Location : Board is 3 x 3\n");
            return 1;
        } else {
            if (this.ColArray[colX][colY].equals("   ")) {
                this.ColArray[colX][colY] = " " + this.xTurn + " ";
            } else {
                System.out.println("LOCATION TAKEN!\n");
                return 1;
            }
        }
        return 0;
    }

//    Insert Value Function Caller

    public void callInsertCol() {
        if(this.insertCol() == 0) {
            this.renderBoard();
            System.out.println("\n");
        } else {
            callInsertCol();
        }
    }


//    Switching Players Turns

    public void switchPlayer() {
        if (this.xTurn == "X") {
            this.xTurn = "O";
        } else {
            this.xTurn = "X";
        }
        System.out.println("It's player " + xTurn + " turn.");
    }


//    Load The Game

    public void initializeGame() {
        System.out.println("Welcome to Jic-Jac-Joe\n");
        renderBoard();
        Random rnd = new Random();
        int rndNum = rnd.nextInt(2);
        if (rndNum == 1) {
            this.xTurn = "X";
        } else {
            this.xTurn = "O";
        }
    }

//    Winning Condition Checker

    public int winConditionChecker() {
        for (int i = 0; i < this.ColArray.length; i++) {
            if (!this.ColArray[i][0].equals("   ") && !this.ColArray[i][1].equals("   ") && !this.ColArray[i][2].equals("   ")) {
                if (this.ColArray[i][0].equals(this.ColArray[i][1]) && this.ColArray[i][0].equals(this.ColArray[i][2])) {
                    System.out.println(this.xTurn + " Won The Game!!!!!!");
                    if (this.xTurn.equals("X")) {
                        this.wonX++;
                    } else {
                        this.wonO++;
                    }
                    return 0;
                }
            }
            if (!this.ColArray[0][i].equals("   ") && !this.ColArray[1][i].equals("   ") && !this.ColArray[2][i].equals("   ")) {
                if (this.ColArray[0][i].equals(this.ColArray[1][i]) && this.ColArray[0][i].equals(this.ColArray[2][i])){
                    System.out.println(this.xTurn + " Won The Game!!!!!!");
                    if (this.xTurn.equals("X")) {
                        this.wonX++;
                    } else {
                        this.wonO++;
                    }
                    return 0;
                }
            }
            if (!this.ColArray[0][0].equals("   ") && !this.ColArray[2][2].equals("   ") || !this.ColArray[0][2].equals("   ") && !this.ColArray[2][0].equals("   ")) {
                if (this.ColArray[0][0].equals(this.ColArray[1][1]) && this.ColArray[0][0].equals(this.ColArray[2][2])){
                    System.out.println(this.xTurn + " Won The Game!!!!!!");
                    if (this.xTurn.equals("X")) {
                        this.wonX++;
                    } else {
                        this.wonO++;
                    }
                    return 0;
                }
                if (this.ColArray[0][2].equals(this.ColArray[1][1]) && this.ColArray[0][2].equals(this.ColArray[2][0])){
                    System.out.println(this.xTurn + " Won The Game!!!!!!");
                    if (this.xTurn.equals("X")) {
                        this.wonX++;
                    } else {
                        this.wonO++;
                    }
                    return 0;
                }
            }
        }
        return 1;
    }

//    Reset The Game

    public void reset() {
        this.ColArray[0][0]= "   ";
        this.ColArray[0][1]= "   ";
        this.ColArray[0][2]= "   ";
        this.ColArray[1][0]= "   ";
        this.ColArray[1][1]= "   ";
        this.ColArray[1][2]= "   ";
        this.ColArray[2][0]= "   ";
        this.ColArray[2][1]= "   ";
        this.ColArray[2][2]= "   ";
    }

//    Replay Function

    public int playAgain() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you wanna play again? y/n");
        String choice = sc.next();
        if (choice.toUpperCase().equals("Y")) {
            this.reset();
            System.out.println("+-----------+");
            System.out.println("+---Score---+");
            System.out.println("+-----------+");
            System.out.println("+--X--|--O--+");
            System.out.println("+-----------+");
            System.out.println("|--"+this.wonX+"--|--"+this.wonO+"--|");
            System.out.println("+-----------+\n");
            initializeGame();
            System.out.println("Player " + this.xTurn + " goes first.\n");
            return 0;
        } else {
            System.out.println("Thank you for playing!");
            System.exit(0);
        }
        return 0;
    }

//    Start A Game

    public void startGame() {
        boolean play = true;
        boolean game = true;

        System.out.println("+-----------+");
        System.out.println("+---Score---+");
        System.out.println("+-----------+");
        System.out.println("+--X--|--O--+");
        System.out.println("+-----------+");
        System.out.println("|--"+this.wonX+"--|--"+this.wonO+"--|");
        System.out.println("+-----------+\n");

        this.initializeGame();
        System.out.println("Player " + this.xTurn + " goes first.\n");
        while(play){
            while(game) {
                callInsertCol();
                if (winConditionChecker() == 0) {
                    game = false;
                } else {
                    switchPlayer();
                }
            }
            playAgain();
            game = true;
        }
    }


//    Special Function to Test the Winning Cases

    public void testWinCoditions() {
        System.out.println("H2 Winning Case\n");
        this.xTurn = "X";
        this.ColArray[0][0]= " X ";
        this.ColArray[0][1]= " X ";
        this.ColArray[0][2]= " X ";
        this.renderBoard();
        this.winConditionChecker();
        this.reset();

        System.out.println("------------------------------");

        System.out.println("H2 Winning Case\n");
        this.xTurn = "X";
        this.ColArray[1][0]= " X ";
        this.ColArray[1][1]= " X ";
        this.ColArray[1][2]= " X ";
        this.renderBoard();
        this.winConditionChecker();
        this.reset();

        System.out.println("------------------------------");

        System.out.println("H3 Winning Case\n");
        this.xTurn = "X";
        this.ColArray[2][0]= " X ";
        this.ColArray[2][1]= " X ";
        this.ColArray[2][2]= " X ";
        this.renderBoard();
        this.winConditionChecker();
        this.reset();

        System.out.println("------------------------------");

        System.out.println("V1 Winning Case\n");
        this.xTurn = "X";
        this.ColArray[0][0]= " X ";
        this.ColArray[1][0]= " X ";
        this.ColArray[2][0]= " X ";
        this.renderBoard();
        this.winConditionChecker();
        this.reset();

        System.out.println("------------------------------");

        System.out.println("V2 Winning Case\n");
        this.xTurn = "X";
        this.ColArray[0][1]= " X ";
        this.ColArray[1][1]= " X ";
        this.ColArray[2][1]= " X ";
        this.renderBoard();
        this.winConditionChecker();
        this.reset();

        System.out.println("------------------------------");

        System.out.println("V3 Winning Case\n");
        this.xTurn = "X";
        this.ColArray[0][2]= " X ";
        this.ColArray[1][2]= " X ";
        this.ColArray[2][2]= " X ";
        this.renderBoard();
        this.winConditionChecker();
        this.reset();

        System.out.println("------------------------------");

        System.out.println("D1 Winning Case\n");
        this.xTurn = "X";
        this.ColArray[0][0]= " X ";
        this.ColArray[1][1]= " X ";
        this.ColArray[2][2]= " X ";
        this.renderBoard();
        this.winConditionChecker();
        this.reset();

        System.out.println("------------------------------");

        System.out.println("D2 Winning Case\n");
        this.xTurn = "X";
        this.ColArray[0][2]= " X ";
        this.ColArray[1][1]= " X ";
        this.ColArray[2][0]= " X ";
        this.renderBoard();
        this.winConditionChecker();
        this.reset();

    }

}
