
public class Queen {
	int xPos;
	int yPos;
	
	public Queen(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void setX(int x){
		xPos = x;
	}
	
	public void setY(int y){
		yPos = y;
	}
	
	public int getX(){
		return xPos;
	}
	
	public int getY(){
		return yPos;
	}
	
	public void move(int currentyPos){
		int randNum = (int)(Math.random() * (25 + 1));
		
		if (randNum == currentyPos){
			yPos++;
		}
		else{
			yPos = randNum;
		}
	}
	

	
}
