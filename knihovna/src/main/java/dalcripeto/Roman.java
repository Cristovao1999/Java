package dalcripeto;

public class Roman extends Kniha{
	private String zanr;
	
	public Roman(String nazev, String autor, int rok_vydani, String zanr) {
		super(nazev, autor, rok_vydani);
		this.zanr = zanr;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
}
