package aquarium;

import java.util.ArrayList;
import java.util.Iterator;

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

	// Füge den Fisch f in Tiefe fdepth dem Aquarium hinzu
	public void addFish(int fdepth, Fish f) {

		if (f != null) {
			int xpositionHead;
			int minHeadXPos, maxHeadXPos;

			if (f.getSwimDir() == Direction.left) {
				minHeadXPos = 1;
				maxHeadXPos = this.getWidth() - f.getLength() - 1;
			} else {
				maxHeadXPos = this.getWidth() - 2;
				minHeadXPos = f.getLength();
			}

			xpositionHead = (int) (Math.random() * (maxHeadXPos - minHeadXPos + 1) + minHeadXPos);

			f.addToAquarium(xpositionHead, fdepth, this);

			// Füge den Fisch der Fisch-ArrayList hinzu
			this.getFishes().add(f);
		}
	}

	// Zeichne das Aquarium im aktuellen Zustand
	public void showStatus() throws InterruptedException {

		// Baue einen String für den Boden
		String aqBottomLine = "";
		for (int x = 0; x < this.getWidth(); x++) {
			if (x == 0 || x == this.getWidth() - 1) {
				aqBottomLine += "+";
			} else {
				aqBottomLine += "-";
			}
		}

		// Baue einen String für die Zeilen oberhalb des Bodens
		String aqFishLine = "";
		for (int x = 0; x < this.getWidth(); x++) {
			if (x == 0 || x == this.getWidth() - 1) {
				aqFishLine += "|";
			} else {
				aqFishLine += " ";
			}
		}

		// Zeichne ein leeres Aquarium bestehend aus StringBuilder-Zeilen
		StringBuilder[] aqState = new StringBuilder[this.depth];
		for (int y = 0; y < aqState.length; y++) {
			if (y < this.getDepth() - 1) {
				aqState[y] = new StringBuilder(aqFishLine);
			} else {
				aqState[y] = new StringBuilder(aqBottomLine);
			}
		}

		// Integriere die Fische in das leere Aquarium
		Iterator<Fish> it = this.getFishes().iterator();
		while (it.hasNext()) {
			Fish fish = it.next();
			if (fish.getSwimDir() == Direction.left) {
				// Zeichne den Fisch nach links schwimmend
				aqState[fish.getDepth()].replace(fish.getXPosHead(), fish.getXPosTail()+1, fish.getRepresentationLeft());
			} else {
				// Zeichne den Fisch nach rechts schwimmend
				aqState[fish.getDepth()].replace(fish.getXPosTail(), fish.getXPosHead()+1, fish.getRepresentationRight());			
			}
		}
		
		// Zeichne das Aquarium auf den Bildschirm
		for (int y = 0; y < this.getDepth(); y++ ) {
			System.out.println(aqState[y]);
		}

		// Warte nach Zeichnen des Aquariums eine Sekunde
		Thread.sleep(500, 0);

	}

	public void calcNextStatus() {

		for (Fish currentFish : this.getFishes()) {

			// Soll der Fisch die Tiefe wechseln?
			// Generiere eine Zufallszahl zwischen 0 und 19
			int zZahl = (int) (Math.random() * 20);
			boolean fischWechseltTiefe = false;
			// Wenn Fisch ein Hai ist und zZahl <= 4 
			if ((currentFish instanceof Shark) && zZahl <= 4) {
				fischWechseltTiefe = true;
			} else 
				// Wenn Fisch ein Kugelfisch ist und zZahl <= 1
				if ((currentFish instanceof BlowFish) && zZahl <= 1) {
				fischWechseltTiefe = true;
			} else 
				// Wenn Fisch ein Schwertfisch ist und die Zufallszahl <= 3
				if ((currentFish instanceof SwordFish) && zZahl <= 3) {
				fischWechseltTiefe = true;
			}

			if (fischWechseltTiefe) {
				// Wenn Fisch oben schwimmt, muss er nach unten wechseln
				if (currentFish.getDepth() == 0) {
					currentFish.setDepth(currentFish.getDepth() + 1);
				} else 
					// Wenn Fisch unten schwimmt, muss er nach oben wechseln
					if (currentFish.getDepth() == this.getDepth() - 2) { // wenn Fisch unten schwimmt
					currentFish.setDepth(currentFish.getDepth() - 1);
				} else {
					// generiere zHal zwischen 0 und 1, um Wechsel nach oben oder unten zu ermitteln
					zZahl = (int) (Math.random() * 2);
					// Wenn zZahl == 0, wechsele nach oben
					if (zZahl == 0) {
						currentFish.setDepth(currentFish.getDepth() - 1);
					} else 
						// Sonst wechsele nach unten
						if (currentFish.getDepth() >= 2) {
						currentFish.setDepth(currentFish.getDepth() + 1);
					}
				}
			} else {

				// Bewege den Fisch innerhalb der aktuellen Aquariumbreite
				if (currentFish.getSwimDir() == Direction.left) {
					// Wenn Fisch nicht am linken Rand
					if (currentFish.getXPosHead() >= 2) {
						currentFish.setXPosHead(currentFish.getXPosHead() - 1);
					} else {
						currentFish.changeSwimDir();
					}
				} else {
					// Wenn Fisch nicht am rechten Rand
					if (currentFish.getXPosHead() <= this.getWidth() - 3) {
						currentFish.setXPosHead(currentFish.getXPosHead() + 1);
					} else {
						currentFish.changeSwimDir();
					}
				}
			}
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
