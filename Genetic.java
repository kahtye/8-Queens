import java.util.ArrayList;
public class Genetic {
	Board initial;
	int populationSize = 10;
	double mutateProb = 0.5;
	ArrayList<Board> population;
	boolean printSolution;
	
	public Genetic(Board initial, boolean printSolution){
		this.initial = initial;
		this.printSolution = printSolution;
		population = new ArrayList<Board>();
		getInitialPopulation();
		sortPopulation();
	}
	
	public int solve(){
		for(int j = 0; j < 5000; j++){
			ArrayList<Board> newPopulation = new ArrayList<Board>();
		
			for (int i = 0; i < populationSize/2+1; i++){
				double rand = Math.random();
				int randIndex = (int)(populationSize * Math.pow(rand, 5));
				Board parent1 = population.get(randIndex);
		
				rand = Math.random();
				randIndex = (int)(populationSize * Math.pow(rand, 5));
				Board parent2 = population.get(randIndex);
		
				int crossIndex = (int)(Math.random()*24);
				Board child1 = crossover1(parent1,parent2,crossIndex);
				Board child2 = crossover2(parent1,parent2,crossIndex);
				if (Math.random() < mutateProb){
					child1 = mutate(child1);
				}
				if (Math.random() < mutateProb){
					child2 = mutate(child2);
				}
				newPopulation.add(child1);
				newPopulation.add(child2);
			}
			population = newPopulation;
			sortPopulation();
			if (population.get(0).getCost() == 0){
				if (printSolution)
					population.get(0).printBoard();
				return j;
			}
		}
		return -1;
		
	}
	
	public Board crossover1(Board p1, Board p2, int crossIndex){
		Board child;
		int[] childQueens= new int[25];
		
		
		int[] p1Qpos = p1.getQpos();
		int[] p2Qpos = p2.getQpos();
		
		for (int i = 0; i < 25; i++){
			if (i <= crossIndex){
				childQueens[i] = p1Qpos[i];
			}
			else{
				childQueens[i] = p2Qpos[i];
			}
		}
		child = new Board(childQueens);
		return child;
	}
	
	public Board mutate(Board b){
		int mutate = (int)(Math.random() * ((25) + 1));
		int mutatePos = (int)(Math.random() * ((25) + 1));
		Board mut;
		int[] mutQueens = new int[25];
		int[] queens = b.getQpos();
		for (int i = 0; i < 25; i++){
			if (i == mutatePos){
				mutQueens[i] = mutate;
			}
			else{
				mutQueens[i] = queens[i];
			}
		}
		mut = new Board(mutQueens);
		return mut;
	}
	
	public Board crossover2(Board p1, Board p2, int crossIndex){
		Board child;
		int[] childQueens= new int[25];
		
		
		int[] p1Qpos = p1.getQpos();
		int[] p2Qpos = p2.getQpos();
		
		for (int i = 0; i < 25; i++){
			if (i <= crossIndex){
				childQueens[i] = p2Qpos[i];
			}
			else{
				childQueens[i] = p1Qpos[i];
			}
		}
		child = new Board(childQueens);
		return child;
	}
	
	public void getInitialPopulation(){
		for (int i = 0; i < populationSize; i++){
			population.add(new Board(getRandQueens()));
		}
	}
	
	public void sortPopulation() 
    { 
        int n = population.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (population.get(j).getCost() > population.get(j+1).getCost()) 
                { 
                    // swap arr[j+1] and arr[i] 
                    Board temp = population.get(j); 
                    population.set(j, population.get(j+1)); 
                    population.set(j+1, temp);
                } 
    } 
	
	public int[] getRandQueens(){
		int[] queens = new int[25];
		for (int i = 0; i < 25; i++){
			queens[i] = (int)(Math.random() * (25));
		}
		
		return queens;
	}
	
}
