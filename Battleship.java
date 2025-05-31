import java.util.Scanner;

public class Battleship {
    public static void main(String[] args) {
        char [][] player1location = new char[5][5];
        char [][] player2location = new char[5][5];
        initLocBoard(player1location);
        initLocBoard(player2location);     
        System.out.println("Welcome to Battleship!\n");
        System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
        enterShips(player1location);
        printBattleShip(player1location);
        print100Lines();
        System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES.");
        enterShips(player2location);
        printBattleShip(player2location);
        print100Lines();
        char [][] player1targethistory = new char [5][5];
        char [][] player2targethistory = new char [5][5];
        initLocBoard(player1targethistory);
        initLocBoard(player2targethistory);
        //game play
        int p1 = 0;
        int p2 = 0;
        do {
            p1 += playSequence(player1targethistory, player2location, 1);
            printBattleShip(player1targethistory);
            if (p1 == 5)
                break;
            System.out.println();
            p2 += playSequence(player2targethistory, player1location, 2);
            printBattleShip(player2targethistory);
            if (p2 == 5)
                break;
            System.out.println();
        } while (p1 < 5 || p2 < 5);
        if (p1 == 5)
            System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!\n");
        else
            System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT'S SHIPS!\n");
        //print final boards
        System.out.println("Final boards:\n");
        printBattleShip(player1location);
        System.out.println();
        printBattleShip(player2location);
   
        }

       
    
        //method to initialize location boards of each player
        public static void initLocBoard(char[][]player){
            for (int row = 0; row < 5; row++){
                for (int col = 0; col < 5; col++){
                    player[row][col] = '-';
                }
            }
        }
    
        //method to enter ships' coordinates
        public static void enterShips(char[][]player){
            Scanner input = new Scanner(System.in);
            int row = 0; 
            int col = 0;
            int counter = 1;
            while (counter <= 5){         
                System.out.println("Enter ship " + counter + " location:");
                if (input.hasNextInt()){
                        row = input.nextInt();               
                    if (input.hasNextInt()){
                        col = input.nextInt();
                        if ((row >= 0 && row < 5) && (col >= 0 && col < 5)){
                            if (player[row][col] != '@'){
                                player[row][col] = '@';
                                counter += 1;
                            }
                            else               
                                System.out.println("You already have a ship there. Choose different coordinates.");
                        }
                        else               
                            System.out.println("Invalid coordinates. Choose different coordinates.");                   
                    }
                    else{
                        System.out.println("Invalid coordinates. Choose different coordinates.");
                        input.next();
                    }                                            
                }
                else{
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                    input.next();
                }          
            }
        }
    
        //method to print 100 lines
        public static void print100Lines(){
            for (int i = 0; i < 100; i++){
                System.out.println();
            }
    
        }
    
        //method of play sequence
        public static int playSequence(char[][]playertarhis, char [][] playerloc, int n){
            int result = 0;
            int row, col;
            Scanner input = new Scanner(System.in);
            boolean goodInput = false;
            while (!goodInput){
                System.out.println("Player " + n +", enter hit row/column:");
                row = input.nextInt();
                col = input.nextInt();
                if ((row >= 0 && row < 5) && (col >= 0 && col < 5)){
                    if (playertarhis[row][col] == '-'){
                        goodInput = true;
                        if (playerloc[row][col] == '@'){
                            if (n == 1)  
                                System.out.println("PLAYER 1 HIT PLAYER 2's SHIP!");
                            else
                                System.out.println("PLAYER 2 HIT PLAYER 1's SHIP!");
                            playerloc[row][col] = 'X';
                            playertarhis[row][col] = 'X';
                            result += 1;                          
                        }
                        else{
                            System.out.println("PLAYER " + n + " MISSED!");
                            playerloc[row][col] = 'O';
                            playertarhis[row][col] = 'O';
                        }
                    }
                    else
                        System.out.println("You already fired on this spot. Choose different coordinates.");
                }
                else{
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                    input.next();
                    }
            }
            return result;
        }

    // Use this method to print game boards to the console.
	private static void printBattleShip(char[][] player) {
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(player[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}
}
    
    

