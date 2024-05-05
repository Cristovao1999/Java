package dalcripeto;

// import java.util.List;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.Comparator;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.*;
import java.util.Scanner;

public class Knihovna {
	private HashMap<String, Kniha> knihovna;
	public HashMap<String, Kniha> getKnihovna() {
		return knihovna;
	}
	private File file;
	private Scanner sc;
	private String cesta = "C:\\Users\\crist\\Downloads\\";

	public Knihovna() {
		knihovna = new HashMap<String, Kniha>();
		sc = new Scanner(System.in);
		this.sql_nacist();
	}
	public void pridat(String nazev, Kniha kniha) {
		this.knihovna.put(nazev, kniha);
	}
	public void upravit(String nazev) {
		boolean run = true;
		int volba;
		while (run) {
			String jmeno;
			System.out.println("Vyberte opravu");
			System.out.println("[1] - Autor");
			System.out.println("[2] - Rok vydani");
			System.out.println("[3] - Dostupnost");
			System.out.println("[4] - Exit");
			volba = Main.pouzeCelaCisla(sc);
			sc.nextLine();
			switch(volba) {
				case 1:
					System.out.print("Zadejte noveho jmena autora knihy: ");
					jmeno = sc.nextLine();
					this.knihovna.get(nazev).setAutor(jmeno);
					System.out.println("Upraveno!");
					break;		
				case 2:
					sc.nextLine();
					System.out.print("Zadejte novy rok vydani knihy: ");
					this.knihovna.get(nazev).setRok_vydani(Main.pouzeCelaCisla(sc));
					System.out.println("Upraveno!");
					break;
				case 3:
					System.out.print("Zmente dostupnost knihy: ");
					this.knihovna.get(nazev).setDostup(sc.nextBoolean());
					System.out.println("Upraveno!");
					break;
				case 4: run = false;
					break;
			}
		}
	}
	public void dostupnost(String nazev) {
		try{
			knihovna.containsKey(nazev);
			if(knihovna.get(nazev).isDostup()) {
				System.out.println("Ano");
			}else {
				System.out.println("Ne");
			}
		}catch(NullPointerException e){
			System.out.println("Kniha nenalezena!");
		}
	}
	public void smazat(String nazev) {
		try{
				knihovna.remove(nazev);
				System.out.println("kniha uspesne smazana!");
		}catch(NullPointerException e){
			System.out.println("Kniha nenalezena!");
		}
	}
	public void vypujcit(String nazev) {
		try{
			if (this.knihovna.get(nazev).isDostup()) {
			this.knihovna.get(nazev).setDostup(false);
			System.out.println("Uspesne vypujceno!");
			}else {
				System.out.println("Kniha je uz vypujcena, bohuzel!");
			}
		}catch(NullPointerException e){
			System.out.println("Kniha nenalezena!");
		}
	}
	public void vyhledat(String nazev) {
		try{
			Kniha kniha = knihovna.get(nazev);
			System.out.println(String.format("%-20s %-20s %-15s %-15s %-15s %-20s %n", "Nazev", "Autor", "Rok vydani", "Dostupnost", "Zanr", "Rocnik"));
			if (kniha instanceof Ucebnice) {
	            Ucebnice u = (Ucebnice) kniha;
	            System.out.println(String.format("%-20s %-20s %-15d %-15s %-15s %-20d %n", 
	                    u.getNazev(), u.getAutor(), u.getRok_vydani(), u.isDostup() ? "Ano" : "Ne", "N/A", u.getRocnik()));
	            
	        }if (kniha instanceof Roman) {
	            	Roman r = (Roman) kniha;
	        		System.out.println(String.format("%-20s %-20s %-15d %-15s %-20s %-20s %n", 
		                    r.getNazev(), r.getAutor(), r.getRok_vydani(), r.isDostup() ? "Ano" : "Ne", r.getZanr(), "N/A"));
	        }
		}catch(NullPointerException e){
			System.out.println("Kniha nenalezena");
		}		
	}
	public void vypis() {
		System.out.println(String.format("%-20s %-20s %-15s %-15s %-15s %-20s %n", "Nazev", "Autor", "Rok vydani", "Dostupnost", "Zanr", "Rocnik"));
		for (Kniha i : knihovna.values()) { 
		    if (i instanceof Ucebnice) {
		        Ucebnice u = (Ucebnice) i;
		        System.out.println(String.format("%-20s %-20s %-15d %-15s %-15s %-20d %n", 
		                u.getNazev(), u.getAutor(), u.getRok_vydani(), u.isDostup() ? "Ano" : "Ne", "N/A", u.getRocnik()));
		    }if (i instanceof Roman) {
		            Roman r = (Roman) i;
		            System.out.println(String.format("%-20s %-20s %-15d %-15s %-15s %-15s %n", 
		                    r.getNazev(), r.getAutor(), r.getRok_vydani(), r.isDostup() ? "Ano" : "Ne", r.getZanr(), "N/A"));
		    }
		}
	}
	public void vypis_podle_autora(String autor) {
		System.out.println(String.format("%-20s %-20s %-15s %-15s %-15s %-20s %n", "Nazev", "Autor", "Rok vydani", "Dostupnost", "Zanr", "Rocnik"));
		for (Kniha i : knihovna.values()) {
			if(i.getAutor().equals(autor)){
				if (i instanceof Ucebnice) {
				Ucebnice u = (Ucebnice) i;
				System.out.println(String.format("%-20s %-20s %-15d %-15s %-15s %-20d %n", 
					u.getNazev(), u.getAutor(), u.getRok_vydani(), u.isDostup() ? "Ano" : "Ne", "N/A", u.getRocnik()));

				}if (i instanceof Roman) {
					Roman r = (Roman) i;
					System.out.println(String.format("%-20s %-20s %-15d %-15s %-20s %n", 
						r.getNazev(), r.getAutor(), r.getRok_vydani(), r.isDostup() ? "Ano" : "Ne", r.getZanr(), "N/A"));
				}
			}
		}
	}
	public void vypis_podle_zanru(String zanr) {
		System.out.println(String.format("%-20s %-20s %-15s %-15s %-15s %-20s %n", "Nazev", "Autor", "Rok vydani", "Dostupnost", "Zanr", "Rocnik"));
		for (Kniha i : knihovna.values()) {
		    if (i instanceof Roman) {
		        Roman r = (Roman) i;
		        if(r.getZanr().equals(zanr)) {
		            System.out.println(String.format("%-20s %-20s %-15d %-15s %-20s %-20s %n", 
				        r.getNazev(), r.getAutor(), r.getRok_vydani(), r.isDostup() ? "Ano" : "Ne", r.getZanr(), "N/A"));
		        }
		    }
		}
	}
	public void vypis_vypujceno() {
		System.out.println(String.format("%-20s %-20s %-15s %-15s %-15s %-20s %n", "Nazev", "Autor", "Rok vydani", "Dostupnost", "Zanr", "Rocnik"));
		for (Kniha i : knihovna.values()) {
			if (!i.isDostup()) {
				if (i instanceof Ucebnice) {
					Ucebnice u = (Ucebnice) i;
						System.out.println(String.format("%-20s %-20s %-15d %-15s %-15s %-20d %n", 
							u.getNazev(), u.getAutor(), u.getRok_vydani(), u.isDostup() ? "Ano" : "Ne", "N/A", u.getRocnik()));
						
				}if (i instanceof Roman) {
					Roman r = (Roman) i;
					System.out.println(String.format("%-20s %-20s %-15d %-15s %-20s %-20s %n", 
						r.getNazev(), r.getAutor(), r.getRok_vydani(), r.isDostup() ? "Ano" : "Ne", r.getZanr(), "N/A"));
				}
			}
		}
	}
	public void ulozit(String nazev) {
		String cestaSouboru = this.cesta + nazev + ".txt";
		file = new File(cestaSouboru);

		try(FileWriter writer = new FileWriter(cestaSouboru, file.exists())) {
			if(file.createNewFile() || file.exists()) {
				System.out.print("Zedejte nazev knihy: ");
				Kniha i = knihovna.get(sc.nextLine());			
				if (i instanceof Ucebnice) {
					Ucebnice u = (Ucebnice) i;
					writer.write("Ucebnice," + String.format("%-20s, %-20s, %-15d, %-15s, %-15s, %-20d %n", 
						u.getNazev(), u.getAutor(), u.getRok_vydani(), u.isDostup() ? "Ano" : "Ne", "N/A", u.getRocnik()));
				}if (i instanceof Roman) {
					Roman r = (Roman) i;
					writer.write(String.format("Roman," + "%-20s, %-20s, %-15d, %-15s, %-20s, %-20s %n", 
						r.getNazev(), r.getAutor(), r.getRok_vydani(), r.isDostup() ? "Ano" : "Ne", r.getZanr(), "N/A"));
				}
				System.out.println("Uspesne ulozeno!");
			}
		}catch(FileNotFoundException e) {
			System.out.println("Soubor nenalezen!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void nacist(String nazev) {
		knihovna = new HashMap<String, Kniha>();
		String cestaSouboru = this.cesta + nazev + ".txt";
		file = new File(cestaSouboru);

		try(Scanner reader = new Scanner(file)) {
			while(reader.hasNext()) {
				String data = reader.nextLine();
								
			}
			System.out.println("Data uspesne ulozeno!");
		}catch(FileNotFoundException e) {
			System.out.println("Soubor neexistuje!");
		}
	}
	public void sql_ulozit()throws SQLException {
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:knihovna.db");
			PreparedStatement pstm = conn.prepareStatement("INSERT INTO kniha (Nazev, Autor, Rok_vydani, Dostupnost, Zanr, Rocnik) VALUES (?, ?, ?, ?, ?, ?)");	
			for (Kniha i : knihovna.values()) { 
				if (i instanceof Ucebnice) {
					Ucebnice u = (Ucebnice) i;
					pstm.setString(1, u.getNazev());
					pstm.setString(2, u.getAutor());
					pstm.setInt(3, u.getRok_vydani());
					pstm.setString(4, u.isDostup() ? "Ano" : "Ne");
					pstm.setString(5, "N/A");
					pstm.setInt(6, u.getRocnik());
				}if (i instanceof Roman) {
						Roman r = (Roman) i;
						pstm.setString(1, r.getNazev());
						pstm.setString(2, r.getAutor());
						pstm.setInt(3, r.getRok_vydani());
						pstm.setString(4, r.isDostup() ? "Ano" : "Ne");
						pstm.setString(5, r.getZanr());
						pstm.setString(6, "N/A");						
				}
			}
			int rowsInserted = pstm.executeUpdate();
			if (rowsInserted > 0) {
			  System.out.println("kniha(y) byl(a)y uspesne ulozen(a)y do databaze!");
		  } else {
			System.out.println("Doslo k chybe procesu!");
		  }
		  conn.close();
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("Stala se vyjimka toho typu: " + e.getMessage());
		}
	}
	public void sql_nacist() {
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:knihovna.db");
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM kniha");
			while(rs.next()){
				if("N/A".equals(rs.getString("rocnik"))){
					Kniha roman = new Roman(rs.getString("nazev"), rs.getString("autor"), rs.getInt("rok_vydani"), rs.getString("zanr"));
					roman.setDostup("Ano".equals(rs.getString("dostupnost"))? true:false);
					knihovna.put(rs.getString("nazev"), roman);
				}else{
					Kniha ucebnice = new Ucebnice(rs.getString("nazev"), rs.getString("autor"), rs.getInt("rok_vydani"), Integer.valueOf(rs.getString("rocnik")));
					ucebnice.setDostup("Ano".equals(rs.getString("dostupnost"))? true:false);
					knihovna.put(rs.getString("nazev"), ucebnice);
				}
			}
			System.out.println("kniha(y) byl(a)y uspesne nacten(a)y z databaze!");
		  	conn.close();
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
			System.out.println("Stala se vyjimka toho typu: " + e.getMessage());
		}
	}
}

