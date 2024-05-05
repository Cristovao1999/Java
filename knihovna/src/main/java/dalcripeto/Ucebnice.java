package dalcripeto;

public class Ucebnice extends Kniha{
	int rocnik;
	
	public Ucebnice(String nazev, String autor, int rok_vydani, int rocnik) {
		super(nazev, autor, rok_vydani);
		this.rocnik = rocnik;
	}
	public int getRocnik() {
		return rocnik;
	}
	public void setRocnik(int rocnik) {
		this.rocnik = rocnik;
	}
}
