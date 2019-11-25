package aquarium;

import java.util.Scanner;

import aquarium.Fish.Direction;

public class TestPrg {

	public static void main(String[] args) {

		// Breite und Tiefe des Aquariums eingeben
		System.out.println("Bitte geben Sie die Breite des Aquariums ein: ");
		Scanner sc = new Scanner(System.in);
		int aqWidth = sc.nextInt();

		System.out.println("Bitte geben Sie die Tiefe des Aquariums ein: ");

		int aqDepth = sc.nextInt();

		sc.close();
		
		// Aquarium initialisieren
		Aquarium aq = new Aquarium(aqWidth, aqDepth);

		Direction rDir;
		
		// Füge in jeder Tiefenebene einen Fisch hinzu (mit Ausnahme der Bodenebene)
		for (int i = 0; i < aqDepth - 1; i++) {
			
			// Generiere per Zufall die Richtung, in die der Fisch schwimmen soll
			if (Math.random() > 0.5) {
				rDir = Direction.left;
			} else {
				rDir = Direction.right;
			}
			aq.addFish(i, rDir);
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