package aquarium;

public class Fish {
	
	enum Direction { left, right};
	
	private Direction swimmingTo;
	private int xPos;
	private int depth;
	
	// Konstruktor
	public Fish (Direction swimmingTo, int x, int d) {
		this.swimmingTo = swimmingTo;
		this.setxPos(x);
		this.setDepth(d);
	}
	
	// Bewege den Fisch und drehe ihn um, wenn nötig
	public void move(int aqWidth) {
		
		// Wenn der Fisch aktuell nach links schwimmt 
		if (this.isSwimmingTo() == Direction.left) {
			// Prüfe, ob der Fisch am linken Beckrand ist
			if (this.getxPos() == 2) {
				// change direction
				this.setSwimmingTo(Direction.right);
			} else {
				this.setxPos(this.getxPos()-1);
			}
		} else {
			// Prüfe, ob der Fisch am rechten 
			if (this.getxPos() == aqWidth - 3) {
				// change direction
				this.setSwimmingTo(Direction.left);
			} else {
				this.setxPos(this.getxPos()+1);
			}
		}
		
		
	}
	
	public Direction isSwimmingTo() {
		return swimmingTo;
	}

	public void setSwimmingTo(Direction swimmingTo) {
		this.swimmingTo = swimmingTo;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth (int d) {
		this.depth = d;
	}

	

}
