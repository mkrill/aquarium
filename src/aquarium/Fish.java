package aquarium;

public abstract class Fish {

	private Direction swimDir;
	private int xPosHead;
	private int depth;
	protected String representationLeft;
	protected String representationRight;
	
	public String getRepresentationLeft() {
		return representationLeft;
	}

	public String getRepresentationRight() {
		return representationRight;
	}

	// Zeichne einen Fisch an aktuellen System.out-Position
	public void paintFish() {

		if (this.getSwimDir() == Direction.left) {
			System.out.print(this.getRepresentationLeft());
		} else {
			// Zeichne den Fisch in Ausrichtung nach rechts
			System.out.print(this.getRepresentationRight());
		}
	}

	// Konstruktor
	public Fish(Direction swimDir) {
		this.swimDir = swimDir;
	}

	public void addToAquarium(int xPosHead, int d, Aquarium aq) {
		this.setXPosHead(xPosHead);
		this.setDepth(d);
	}

	public int getLength() {
		return this.getRepresentationLeft().length();
	}

	public Direction getSwimDir() {
		return swimDir;
	}

	public void changeSwimDir() {
		int posTail = this.getXPosTail();
		this.setXPosHead(posTail);
		this.swimDir = (this.getSwimDir() == Direction.left) ? Direction.right : Direction.left;
	}

	public int getXPosHead() {
		return xPosHead;
	}

	public int getXPosTail() {
		if (this.getSwimDir() == Direction.left) {
			return this.getXPosHead() + (this.getRepresentationLeft().length() - 1);
		} else {
			return this.getXPosHead() - (this.getRepresentationLeft().length() - 1);
		}
	}

	public void setXPosHead(int xPos) {
		this.xPosHead = xPos;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int d) {
		this.depth = d;
	}
}
