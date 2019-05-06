
public class SimulatedAnnealing {
	public Board current;
	double T = 1;
	boolean printSolution;
	
	public SimulatedAnnealing(Board initial, boolean printSolution){
		current = initial;
		this.printSolution = printSolution;
	}
	
	public int solve(){
		Board next;
		next = current.getNext();

		for (int i = 0; i < 10000; i++){
			next = current.getNext();
			if (current.getCost() == 0){
				if (printSolution){
					current.printBoard();
				}
				return i;
			}
			int costChange = current.getCost() - next.getCost();
			if (costChange > 0){
				current = next;
			}
			else if (chooseUphillMove(i,costChange)){
				current = next;
			}
			
			
		}
		return -1;
		
		
	}
	
	public boolean chooseUphillMove(int t,int costChange){
		T = getT(t);
		double p = Math.pow(Math.E, costChange/T);

		if (p > Math.random()){
			return true;
		}
		return false;
	}

	
	public double getT(int t){
		T = T*=.9;
		return T;
	}
}
