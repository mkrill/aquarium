package aquarium;

import java.util.ArrayList;
import java.util.Iterator;

import aquarium.Fish.Direction;

public class Aquarium {

	private final int width;
	private final int depth;
	private final ArrayList<Fish> fishes = new ArrayList<Fish>();

	// Konstruktor
	public Aquarium(int width, int depth) throws IllegalArgumentException {

		// Wände brauchen jeweils eine Breiteneinheit, ein Fisch braucht 3
		// Breiteneinheiten
		// => Mindestbreite 5
		// Der Boden braucht eine Höheneinheit => Mindesttiefe 2
		if ((width < 5) || depth < 2) {
			throw new IllegalArgumentException("Aquarium zu klein für Fische!!!");
		}
		this.width = width;
		this.depth = depth;

	}

	// Füge einen Fisch hinzu
	public void addFish(int fdepth, Direction dir) {

		// Die Seitenwände brauchen 1 Breiteneinheit,
		// Die Position eines Fisches wird durch seine Mitte beschrieben
		// Linksäußere Position liegt bei "2", rechtsäußere bei width - 3
		// Generiere eine zufällige xposition in diesem Rahmen
		int xposition = (int) (Math.random() * (this.getWidth() - 4)) + 2;

		// Erzeuge einen Fisch an dieser Position in vorgegebener Tiefe mit gegeb.
		// Richtung
		Fish f = new Fish(dir, xposition, fdepth);

		// Füge den Fisch dem Feld hinzu
		this.getFishes().add(f);
	}

	// Zeichne einen Fisch an aktuellen System.out-Position
	private void paintFish(Fish f) {

		if (f.isSwimmingTo() == Direction.left) {
			System.out.print("<><");
		} else {
			System.out.print("><>");
		}

	}

	// Zeichne das Aquarium im aktuellen Zustand
	public void showStatus() throws InterruptedException {

		// Für jede Tiefenzeile
		for (int y = 0; y < this.getDepth(); y++) {

			Fish currentFish = null;

			// Identifiziere den Fisch in der aktuellen Tiefe aus der ArrayList
			Iterator<Fish> it = this.getFishes().iterator();
			while (it.hasNext() && currentFish == null) {
				Fish fish = it.next();
				if (fish.getDepth() == y) {
					currentFish = fish;
				}
			}

			// Wenn y die aktuelle Aquarienzeile der Glasboden ist
			if (y == this.getDepth() - 1) {
				// Zeichne eine durchgehende Bodenzeile
				System.out.print("+");
				for (int x = 1; x < this.getWidth() - 1; x++) {
					System.out.print("-");
				}
				System.out.println("+");
			} else {
				// y steht auf Zeile oberhalb des Bodens
				// Gehe jedes Feld der aktuellen Zeile durch
				for (int x = 0; x < this.getWidth(); x++) {
					// x == 0 => linke Wand
					if (x == 0) {
						// draw left side
						System.out.print("|");
					} else
					// wenn in aktueller Tiefe ein Fisch gefunden wurde und seine
					// Position an x+1 liegt, sind Kopf oder Schwanz jetzt zu zeichnen
					if ((currentFish != null) && (x + 1 == currentFish.getxPos())) {
						this.paintFish(currentFish);
						// x um 2 erhöhen, da gezeichneter Fisch bis x+2 geht
						x += 2;
					} else
					// x maximal => rechte Wand zeichnen
					if (x == this.getWidth() - 1) {
						System.out.println("|");
					} else {
						// Gib für Wasser ein Leerzeichen aus
						System.out.print(" ");
					}
				}
			}
		}

		// Warte nach Zeichnen des Aquariums eine Sekunde
		Thread.sleep(1000, 0);

	}

	// Bewege jeden Fisch
	public void calcNextStatus() {

		for (Fish currentFish : this.getFishes()) {
			// Bewege den Fisch innerhalb der aktuellen Aquariumbreite
			currentFish.move(this.getWidth());
		}

	}

	// Getter und Setter
	public ArrayList<Fish> getFishes() {
		return fishes;
	}

	public int getWidth() {
		return width;
	}

	public int getDepth() {
		return depth;
	}

}
