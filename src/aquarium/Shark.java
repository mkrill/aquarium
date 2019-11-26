package aquarium;

public class Shark extends Fish {
	
	public Shark(Direction swimDir) {
		super(swimDir);
		representationLeft = "<///====><";
		representationRight = "><====///>";
	}

}
