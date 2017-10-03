import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class RandomGuessingGame {

	private int low;
	private int high;
	private int numOfGuesses;
	private int guess;
	private boolean continuePlaying = true;

	public boolean isContinuePlaying() {
		return continuePlaying;
	}

	public void setContinuePlaying(boolean continuePlaying) {
		this.continuePlaying = continuePlaying;
	}

	public boolean PlayRandomGuessingGame() {
		@SuppressWarnings("resource")
		Scanner randomGameInput = new Scanner(System.in);

		System.out.println("T�nk p� ett heltal i ett intervall.");
		System.out.println("Ange var intervallet slutar och startar: ");

		Random randomNumber = new Random();
		low = randomGameInput.nextInt();
		high = randomGameInput.nextInt();
		numOfGuesses = 0;

		System.out.println("Du har valt intervallet " + low + "-" + high);

		// Skapar ett set f�r att stoppa in integers i f�r att garantera att gissningarna �r unika.
		Set<Integer> validate = new HashSet<>(); 

		// S�tter winCondition till false och k�rs s� l�nge wincondition �r false
		boolean winCondition = false; 
		while(!winCondition) {
			
			// Genererer f�rsta gissningen som ett slumpat tal mellan spelarens satta intervall
			guess = randomNumber.nextInt(high-low) + low; 
			
			// Om "validate" redan inneh�ller integern i "guess", slumpa fram ett nytt tal. G�r detta tills det slumpade talet �r ett tal som inte finns i "validate"
			while(validate.contains(guess)) { 
				guess = randomNumber.nextInt(high-low) + low;
			}
			// L�gger till "guess" i v�rt set "validate"
			validate.add(guess);
			System.out.println("�r talet du t�nker p� " + guess + "? [Ja/H�gre/L�gre]");
			
			// �kar "numOfGuesses" med ett, eftersom vi gissar en g�ng
			numOfGuesses++; 
			String playerInput = randomGameInput.next().toLowerCase();

			// Om spelaren s�ger att deras tal �r h�gre �n det gissade talet s� s�tter vi den nya l�gstaniv�n till samma niv� som "guess" och slumpar
			// fram ett nytt tal i den nya intervallen. Talet stoppas sedan in i set, f�rutsatt att det inte redan finns d�r, d� slumpas ett nytt tal fram.
			if (playerInput.equals("h�gre")) { 
				low = guess;
				guess = randomNumber.nextInt(high-low) + low;
				while (validate.contains(guess)) {
					guess = randomNumber.nextInt(high-low) + low;
				}
				validate.add(guess);

				// Samma tanke som ovan, fast om spelaren v�ljer l�gre. "high" s�tts d� till samma tal som "guess" och blir det �vre taket p� intervallen.
			} if (playerInput.equals("l�gre")) {
				high = guess;
				guess = randomNumber.nextInt(high-low) + low;
				while(validate.contains(guess)) {
					guess = randomNumber.nextInt(high-low) + low;
				}
				validate.add(guess);

				// Om spelaren s�ger att gissningen �r r�tt, ber�tta hur m�nga f�rs�k det tog och fr�ga om spelaren vill spela igen och starta om fr�n b�rjan i s� fall.	
			} if (playerInput.equals("ja")) {
				System.out.println("Det tog mig " + numOfGuesses + " f�rs�k att gissa r�tt!");
				winCondition = true;
				System.out.println("Vill du spela igen? [Ja/Nej]");
				playerInput = randomGameInput.next().toLowerCase();

				if(playerInput.equals("nej")) {
					continuePlaying = false;
					System.out.println("Ok, tack f�r idag!\n");
				}
			} else {
				System.out.println("Felaktig input, ange 'Ja', 'H�gre' eller 'L�gre'");

			}
//			randomGameInput.close();
		}
		return continuePlaying;
	}
}
