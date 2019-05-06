
public class Board {
	String[][] board;
	Queen[] queens;
	int cost;
	int[] queenX;
	
	public Board(Queen[] queens){
		board = new String[25][25];
		this.queens = queens;
		calcCost();
	}
	
	public Board(int[] qPos){
		board = new String[25][25];
		queens = new Queen[25];
		for (int i = 0; i < 25; i++){
			queens[i] = new Queen(qPos[i],i);
		}
		calcCost();
	}
	
	
	public void printBoard(){
		for (int i = 0; i < 25; i++){
			for (int j = 0; j < 25; j++){
				if (isQueen(j,i)){
					System.out.print("Q  ");
				}
				else{
					System.out.print("-  ");
				}
			}
			System.out.println();
		}
	}
	
	
	public boolean isQueen(int x, int y){
		for (int i = 0; i < 25; i++){
			if (queens[i].getX() == x && queens[i].getY() == y){
				return true;
			}
		}
		return false;
	}
	
	
	public void calcCost() {
        int i, j;
        cost = 0;

        for (i = 0; i < board.length; i++) {
            for (j = 0; j < board.length; j++) {
                if (i==j) continue;
                if (queens[i].getX() == queens[j].getX() // same row
                        || queens[i].getY() == queens[j].getY() //same column
                        || (queens[i].getX() - queens[j].getX() == queens[i].getY() - queens[j].getY()) // same diagonal
                        || (queens[i].getX() - queens[j].getX() == queens[j].getY() - queens[i].getY())) { //same counter diagonal
                    cost++;
                }
            }
        }
        //this is due to double counting
        cost = cost / 2;

    }
	
	public int getCost(){
		return cost;
	}
	
	public int[] getQpos(){
		int[] qPos = new int[25];
		
		for (int i = 0; i < 25; i++){
			qPos[i] = queens[i].getX();
		}
		return qPos;
	}
	
	public Board getNext(){
		Board next;
		Queen movedQueen;
		Queen[] newQueens = new Queen[25];
		
		//copy queens over
		for (int i = 0; i < 25; i++){
			newQueens[i] = new Queen(queens[i].getX(), queens[i].getY());
		}
		
		int queenIndex = (int)(Math.random() * (25));
		Queen chosen = queens[queenIndex];
		
		movedQueen = new Queen(chosen.getX(), chosen.getY());
		movedQueen.move(chosen.getY());
		
		newQueens[queenIndex] = movedQueen;
		
		next = new Board(newQueens);
		
		return next;

	}
	
	
	
	public Queen[] getQueens(){
		return queens;
	}
	
}
