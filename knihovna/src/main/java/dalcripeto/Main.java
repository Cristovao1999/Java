
package dalcripeto;

import java.sql.SQLException;
import java.util.Scanner;
public class Main {
	
	public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Knihovna knihovna = new Knihovna();
		Scanner sc = new Scanner(System.in);
		int volba, rok_vydani, rocnik;
		String nazev, autor, zanr;
		boolean run = true;
		while(run) {
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println("|\t\t\tVitame vas ve Dalripeto knihovne     		              |");
			System.out.println("---------------------------------------------------------------------------------------");
			System.out.println("[1] - Pridat \t\t[6] - Vyhledat\t\t\t[11] - Ulozit do souboru");
			System.out.println("[2] - Upravit \t\t[7] - Vypis knihovnu\t\t[12] - Nacist ze souboru");
			System.out.println("[3] - Dostupnost \t[8] - Vypis_podle_autora\t[13] - Ulozit do sql db a Exit");
			System.out.println("[4] - Smazat \t\t[9] - Vypis_podle_zanru");
			System.out.println("[5] - Vypucit \t\t[10] - Vypis_vypujceno");
			volba = pouzeCelaCisla(sc);
			switch(volba) {
				case 1:
					int v;
					System.out.println("Vyberte typ knihy");
					System.out.println("[1] - Roman");
					System.out.println("[2] - Ucebnice");
					v = pouzeCelaCisla(sc);//volba
					sc.nextLine();
					switch(v) {
						case 1:
							System.out.println("Zadejte dostupne parametry knihy");
							System.out.print("Nazev: ");
							nazev = sc.nextLine();
							System.out.print("Autor: ");
							autor = sc.nextLine(); 
							System.out.print("Rok vydani: ");
							rok_vydani = pouzeCelaCisla(sc);
							sc.nextLine();
							System.out.print("Zanr: ");
							zanr = sc.nextLine();
							knihovna.pridat(nazev, new Roman(nazev, autor, rok_vydani, zanr));
							break;
						case 2:
							System.out.println("Zadejte dostupne parametry knihy");
							System.out.print("Nazev: ");
							nazev = sc.nextLine(); 
							System.out.print("Autor: ");
							autor = sc.nextLine(); 
							System.out.print("Rok vydani: ");
							rok_vydani = pouzeCelaCisla(sc);
							System.out.print("Rocnik: ");
							rocnik = pouzeCelaCisla(sc);
							knihovna.pridat(nazev, new Ucebnice(nazev, autor, rok_vydani, rocnik));
							break;
					}
					break;
				case 2:
					sc.nextLine();
					if(!knihovna.getKnihovna().isEmpty()){
						System.out.print("Zadejte nazev knihy: ");
						nazev = sc.nextLine();
						knihovna.upravit(nazev);
						break;
					}else{
						System.out.println("Knihovna je prazdna");
						break;
					}
				case 3:
					sc.nextLine();					
					if(!knihovna.getKnihovna().isEmpty()){
						System.out.print("Zadejte nazev knihy: ");
						nazev = sc.nextLine();
						System.out.print("Dostupnost: ");
						knihovna.dostupnost(nazev);
						break;
					}else{
						System.out.println("Knihovna je prazdna");
						break;
					}
				case 4:
					sc.nextLine();
					if(!knihovna.getKnihovna().isEmpty()){
						System.out.print("Zadejte nazev knihy: ");
						nazev = sc.nextLine();
						knihovna.smazat(nazev);
						break;
					}else{
						System.out.println("Knihovna je prazdna");
						break;
					}
				case 5:
					sc.nextLine();
					if(!knihovna.getKnihovna().isEmpty()){
						System.out.print("Zadejte nazev knihy: ");
						nazev = sc.nextLine();
						knihovna.vypujcit(nazev);
						break;
					}else{
						System.out.println("Knihovna je prazdna");
						break;
					}
				case 6:
					sc.nextLine();
					if(!knihovna.getKnihovna().isEmpty()){
						System.out.print("Zadejte nazev knihy: ");
						nazev = sc.nextLine();
						knihovna.vyhledat(nazev);
						break;
					}else{
						System.out.println("Knihovna je prazdna");
						break;
					}
				case 7:
					sc.nextLine();
					if(!knihovna.getKnihovna().isEmpty()){
						knihovna.vypis();
						break;
					}else{
						System.out.println("Knihovna je prazdna");
						break;
					}
				case 8:
					sc.nextLine();
					if(!knihovna.getKnihovna().isEmpty()){
						System.out.print("Zadejte jmeno autora: ");
						autor = sc.nextLine();
						knihovna.vypis_podle_autora(autor);
						break;
					}else{
						System.out.println("Knihovna je prazdna");
						break;
					}
				case 9:
					sc.nextLine();
					if(!knihovna.getKnihovna().isEmpty()){
						System.out.print("Zadejte nazev zanr: ");
						zanr = sc.nextLine();
						knihovna.vypis_podle_zanru(zanr);
						break;
					}else{
						System.out.println("Knihovna je prazdna");
						break;
					}
				case 10:
					sc.nextLine();
					if(!knihovna.getKnihovna().isEmpty()){
						knihovna.vypis_vypujceno();
						break;
					}else{
						System.out.println("Knihovna je prazdna");
						break;
					}
				case 11:
					sc.nextLine();
					if(!knihovna.getKnihovna().isEmpty()){
						System.out.print("Zadejte nazev souboru: ");
						nazev = sc.nextLine();
						knihovna.ulozit(nazev);
						break;
					}else{
						System.out.println("Knihovna je prazdna");
						break;
					}
				case 12:
					sc.nextLine();
					if(!knihovna.getKnihovna().isEmpty()){
						System.out.print("Zadejte nazev souboru: ");
						nazev = sc.nextLine();
						knihovna.nacist(nazev);
						break;
					}else{
						System.out.println("Knihovna je prazdna");
						break;
					}
				case 13:
				try {
					knihovna.sql_ulozit();
					run = false;
					break;
				} catch (SQLException e) {
					e.printStackTrace();	
				}		
			}	
		}
	}
}
