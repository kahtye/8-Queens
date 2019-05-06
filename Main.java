import java.util.Scanner;
public class Main {
	public static void main(String[] args){
		Scanner reader = new Scanner(System.in);
		
		System.out.println("1) Simulated Annealing analysis");
		System.out.println("2) Genetic algorithm analysis");
		System.out.println("3) 3 Simulated Annealing solutions");
		System.out.println("4) 3 Genetic algorithm solutions");
		
		int input = reader.nextInt();
		
		double totalSolved = 0;
		int iterations = 0;
		double start;
		double elapsed = 0;
		int numBoards = 1000;
		Queen[] initial = getInitialQueens();
		Board b = new Board(initial);
		if (input == 1){
			start = System.currentTimeMillis();
			for (int i = 0; i < numBoards; i++){
				SimulatedAnnealing s = new SimulatedAnnealing(b,false);
				int out = s.solve();
				if (out != -1){
					System.out.println(i+1 + ") Solved in " + out + " iterations");
					totalSolved++;
					iterations += out;
				}
				else{
					System.out.println(i+1 + ") Not solved");
				}
			}
			elapsed = System.currentTimeMillis() - start;
		}
		
		else if (input == 2){
			start = System.currentTimeMillis();
			for (int i = 0; i < numBoards; i++){
				Genetic g = new Genetic(b,false);
				int out = g.solve();
				if (out == -1){
					System.out.println(i+1 + ") Not solved");
				}
				else{
					totalSolved++;
					iterations += out;
					System.out.println(i+1 + ") Solved in " + out + " iterations");
				}
			}
			elapsed = System.currentTimeMillis() - start;
		}
		
		else if (input == 3){
			System.out.println("3 Possible solutions: ");
			for (int i = 0; i < 3; i++){
				SimulatedAnnealing s = new SimulatedAnnealing(b,true);
				if (s.solve() == -1){
					i--;
				}
				System.out.println();
			}
		}
		
		else if (input == 4){
			System.out.println("3 Possible solutions: ");
			for (int i = 0; i < 3; i++){
				Genetic g = new Genetic(b,true);
				if (g.solve() == -1){
					i--;
				}
				System.out.println();
			}
		}
		
		else{
			System.out.println("Invalid input!!");
			System.exit(0);
		}
		
		if (input == 1 || input == 2){
			System.out.println();
			System.out.println((int)totalSolved + " boards solved out of " + numBoards);
			double percentage = totalSolved/numBoards *100;
			System.out.println(percentage + "% of boards successfully solved");
			System.out.println("Average iterations: " + iterations/numBoards);
			System.out.println("Average running time: " + elapsed/numBoards + " milliseconds");
		}
		
		reader.close();
		
	}
	
	public static Queen[] getInitialQueens(){
		Queen[] initial = new Queen[25];
		for (int i = 0; i < 25; i++){
			initial[i] = new Queen(i,i);
		}
		return initial;
	}
}
