package aquarium;

public class SwordFish extends Fish {
	
	public SwordFish(Direction swimDir) {
		super(swimDir);
		representationLeft = "-<><";
		representationRight = "><>-";
	}

}
