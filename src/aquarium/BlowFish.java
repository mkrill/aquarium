package aquarium;

public class BlowFish extends Fish {

	public BlowFish(Direction swimDir) {
		super(swimDir);
		representationLeft = "<()><";
		representationRight = "><()>";
	}

}
