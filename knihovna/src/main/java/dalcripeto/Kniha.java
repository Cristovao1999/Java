package dalcripeto;

abstract class Kniha {
	private String nazev;
	private String autor;
	private int rok_vydani;
	private boolean dostup;

	public Kniha(String nazev, String autor, int rok_vydani) {
		this.nazev = nazev;
		this.autor = autor;
		this.rok_vydani = rok_vydani;
		this.dostup = true;
	}

	public String getNazev() {
		return nazev;
	}

	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getRok_vydani() {
		return rok_vydani;
	}

	public void setRok_vydani(int rok_vydani) {
		this.rok_vydani = rok_vydani;
	}
	public boolean isDostup() {
		return dostup;
	}

	public void setDostup(boolean dostup) {
		this.dostup = dostup;
	}
}
