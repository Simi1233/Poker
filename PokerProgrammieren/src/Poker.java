import java.util.Arrays;
import java.util.Random;

public class Poker {

	static int[] Kartendeck = new int[52];
	static final int KARTEN_PRO_FARBE = 13;
	static final int ANZAHL_SYMBOLE = 4;
	
	static int[] Handkarten = new int[5];
	
	static Random rn = new Random();
	
	static int anzVersuche = 1000000;
	static int royalFlushZaehler = 0;
	static int straightFlushZaehler = 0;
	static int vierlingZaehler = 0;
	static int fullHouseZaehler = 0;
	static int flushZaehler = 0;
	static int straightZaehler = 0;
	static int drillingZaehler = 0;
	static int zweiPaarezaehler = 0;
	static int paarZaehler = 0;
	static int höchsteKarteZaehler = 0;
	
	static double prozent = 100;
	
	static void FillKartendeck() {
		for(int i = 0; i < Kartendeck.length; i++)
		{
			Kartendeck[i] = i;
		}
		Arrays.toString(Kartendeck);
	}
	
	static void Mischen() {
		for (int i = 0; i < Kartendeck.length; i++)
		{
			int zufall = rn.nextInt(Kartendeck.length);
			int ziehen = Kartendeck[i];
			Kartendeck[i] = Kartendeck[zufall];
			Kartendeck[zufall] = ziehen;
		}
		Arrays.toString(Kartendeck);
	}
	
	static void Kartenziehen() {
		for (int i = 0; i < Handkarten.length; i++)
		{	
			Handkarten[i] = Kartendeck[i];
		}
		Arrays.sort(Handkarten);
		Arrays.toString(Handkarten); 

	}	
	
	public static int symbolBerechnen(int Karte) {
		return Karte % KARTEN_PRO_FARBE;
	}
	
	public static int farbeBerechnen(int Karte) {
		return Karte / KARTEN_PRO_FARBE;
	}
		
	public static boolean Paar(int gleich) {
		int anzahlGleich = 0;
		for (int i = 0; i < Handkarten.length; i++)
		{
			for (int j = i + 1; j < Handkarten.length; j++)
			{
				if(symbolBerechnen(Handkarten[i]) == symbolBerechnen(Handkarten[j]))
				{
					anzahlGleich++;
				}
			}
		}
		if (anzahlGleich == gleich) {
			return true;
		}
		return false;
	}
	

	public static boolean Straight() {
		for(int i = 0; i < Handkarten.length-1; i++)
		{
			if(symbolBerechnen(Handkarten[i]) != (symbolBerechnen(Handkarten[i+1])))
			{
				return false;
			}
		} return true;
	} 
	
	
	public static boolean Flush() {
		for (int i = 0; i < Handkarten.length -1; i++)
		{
			if (farbeBerechnen(Handkarten[i]) != farbeBerechnen(Handkarten[i+1]))
			{
				return false;
			}
		}
		return true;
	}
	
	
	
	public static void auswertung()
		{
			
			if (Paar(1))
			{
			paarZaehler++;
			}
			else if (Paar(2))
			{
				zweiPaarezaehler++;
			} 	
			else if (Paar(3))
			{
				drillingZaehler++;
			}
			else if (Paar(4))
			{
				fullHouseZaehler++;
			} 
			else if (Paar(6))
			{
				vierlingZaehler++;
			}
			else if (Flush())
			{
				flushZaehler++;
			} 
			else if (Straight())
			{
				straightZaehler++;
			}
			else if (Straight() && Flush())
			{
				straightFlushZaehler++;
			}
			else if(Straight() &&Flush() && symbolBerechnen(Handkarten[4]) == 13)
			{
				royalFlushZaehler++;
			}
			else 
			{
				höchsteKarteZaehler++;
			}
		}
	
	public static void wahrscheinlichkeitsBerechnung() {
		
		double paarWahrscheinlichkeit = paarZaehler * prozent / anzVersuche;
		double zweiPaareWahrscheinlichkeit = zweiPaarezaehler * prozent / anzVersuche;
		double drillingWahrscheinlichkeit = drillingZaehler * prozent / anzVersuche;
		double straightWahrscheinlichkeit = straightZaehler * prozent / anzVersuche;
		double flushWahrscheinlichkeit = flushZaehler * prozent / anzVersuche;
		double fullhouseWahrscheinlichkeit = fullHouseZaehler * prozent / anzVersuche;
		double vierlingWahrscheinlichkeit = vierlingZaehler * prozent / anzVersuche;
		double straightFlushWahrscheinlichkeit = straightFlushZaehler * prozent / anzVersuche;
		double royalFlushWahrscheinlichkeit = royalFlushZaehler * prozent / anzVersuche;
		double höchsteKarteWahrscheinlichkeit = höchsteKarteZaehler * prozent / anzVersuche;
		
		System.out.println(paarZaehler+" Paar(e) bei "+anzVersuche+ " Versuchen ist die Wahrscheinlichkeit ein Paar zu ziehen = "+paarWahrscheinlichkeit);
		System.out.println(zweiPaarezaehler+" mal Zwei Paar(e) bei "+anzVersuche+ " Versuchen ist die Wahrscheinlichkeit Zwei Paare zu ziehen = "+zweiPaareWahrscheinlichkeit);
		System.out.println(drillingZaehler+" Drilling(e) bei "+anzVersuche+ " Versuchen ist die Wahrscheinlichkeit einen Drilling zu ziehen = "+drillingWahrscheinlichkeit);
		System.out.println(straightZaehler+" Straße(n) bei "+anzVersuche+ " Versuchen ist die Wahrscheinlichkeit eine Straße zu ziehen = "+straightWahrscheinlichkeit);
		System.out.println(flushZaehler+" Flush bei "+anzVersuche+ " Versuchen ist die Wahrscheinlichkeit einen Flush zu ziehen = "+flushWahrscheinlichkeit);
		System.out.println(fullHouseZaehler+" Full House bei "+anzVersuche+ " Versuchen ist die Wahrscheinlichkeit ein Full House zu ziehen = "+fullhouseWahrscheinlichkeit);
		System.out.println(vierlingZaehler+" Vierling(e) bei "+anzVersuche+ " Versuchen ist die Wahrscheinlichkeit einen Vierling zu ziehen = "+vierlingWahrscheinlichkeit);
		System.out.println(straightFlushZaehler+" Straight Flush bei "+anzVersuche+ " Versuchen ist die Wahrscheinlichkeit einen Straight Flush zu ziehen = "+straightFlushWahrscheinlichkeit);
		System.out.println(royalFlushZaehler+" Royal Flush bei "+anzVersuche+ " Versuchen ist die Wahrscheinlichkeit einen Royal Flush zu ziehen = "+royalFlushWahrscheinlichkeit);
		System.out.println(höchsteKarteZaehler+" mal keine Kartenkombination ist die Wahrscheinlichkeit, dass die höchse Karte die entscheidung trifft = "+höchsteKarteWahrscheinlichkeit);
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < anzVersuche; i++) {
			FillKartendeck();
			Mischen();
			Kartenziehen();
			auswertung();
		}	
			wahrscheinlichkeitsBerechnung();
	}	
}	