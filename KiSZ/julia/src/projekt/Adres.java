package projekt;

import java.util.ArrayList;

public class Adres {

	public String ulica;
	public int nrDomu;
	public String nrMieszkania;
	public String miasto;
	public int kod;
	public String panstwo;
	public ArrayList<Wydarzenie> wydarzenie; //impl asocjacji * z Wydarzenie
	
	public Adres(String ulica, int nrDomu, String nrMieszkania, String miasto, int kod, String panstwo){
		this.ulica = ulica;
		this.nrDomu = nrDomu;
		this.nrMieszkania = nrMieszkania;
		this.miasto = miasto;
		this.kod = kod;
		this.panstwo = panstwo;
		this.wydarzenie = new ArrayList<Wydarzenie>();
	}
	
	//sprawdzenie czy nie ma juz takie info wpisane o wydarzeniu 
public void dodajWydarzenie(Wydarzenie dodajWydarzenie){
	
	if(!wydarzenie.contains(dodajWydarzenie)){
		wydarzenie.add(dodajWydarzenie);
		//Dodaj info zwrotn�
		dodajWydarzenie.dodajAdres(this);
	}
	}
public String toString(){
	String info = "Adres:" + ulica + " " + nrDomu + "\n" + " Pa�stwo: " + panstwo + "\n";
	//dodanie informacji o wydarzeniu w danym adresie
	for (Wydarzenie w : wydarzenie){
		info += "w tym dniu: " + w.nazwa +  "\n";
	}
	return info;
	}
}
