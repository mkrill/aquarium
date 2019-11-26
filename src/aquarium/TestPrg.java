package aquarium;

import java.util.Scanner;

public class TestPrg {

	public static void main(String[] args) {

		// Inititalisiere Aquariumwerte
		System.out.println("Bitte geben Sie die Breite des Aquariums ein: ");
		Scanner sc = new Scanner(System.in);
		int aqWidth = sc.nextInt();

		System.out.println("Bitte geben Sie die Tiefe des Aquariums ein: ");
		int aqDepth = sc.nextInt();

		System.out.println("Bitte geben Sie die Anzahl der Fische ein: ");
		int aqNoFish = sc.nextInt();
		
		sc.close();

		// Aquarium erzeugen
		Aquarium aq = new Aquarium(aqWidth, aqDepth);

		Direction rDir;

		// Füge in jeder Tiefenebene einen Fisch hinzu (mit Ausnahme der Bodenebene)
		Fish f = null;
		int fType;
		int randDepth;
		
		for (int i = 0; i < aqNoFish; i++) {

			// Generiere per Zufall die Richtung, in die der Fisch schwimmen soll
			if (Math.random() > 0.5) {
				rDir = Direction.left;
			} else {
				rDir = Direction.right;
			}

			// Generiere per Zufall die Fischart
			fType = (int) (Math.random() * 4);
			switch (fType) {
			case 0:
				f = new Shark(rDir);
				break;
			case 1:
				f = new SwordFish(rDir);
				break;
			case 2:
				f = new StdFish(rDir);
				break;
			case 3:
				f = new BlowFish(rDir);
				break;
			}

			// Generiere die Schwimmtiefe
			randDepth = (int) (Math.random() * (aqDepth - 1));
			aq.addFish(randDepth, f);
		}

		while (true) {
			try {
				// Zeichne den aktuellen Zustand des Aquariums
				aq.showStatus();
			} catch (InterruptedException e) {
				System.out.println("Ablauf unterbrochen!");
			}
			System.out.println();
			// Berechne den nächsten Status
			aq.calcNextStatus();
		}

	}
}