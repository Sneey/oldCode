package projekt;

import java.util.ArrayList;

public class SwietoSt {

	public String nazwa; 
	public String opis;
	public int iledni;
	public String praktyka;
	
	private ArrayList<DanyDzien> dzien = new ArrayList<DanyDzien>(); //imp.asosjacji, liczno�� * z DanyDzien
	public SwietoSt(String nazwa, String opis, int iledni, String praktyka){
		this.nazwa = nazwa;
		this.opis = opis;
		this.iledni = iledni;
		this.praktyka = praktyka;
			}
	
	//sprawdzenie czy nie ma juz takie info wpisane o dniu 
public void dodajDzien(DanyDzien danyDzien){
	
	if(!dzien.contains(danyDzien)){
		dzien.add(danyDzien);
		//Dodaj info zwrotn�
		danyDzien.dodajSwietoSt(this);
	}
	}
public String toString(){
	String info = "Swieto sta�e:" + nazwa + "\n" + "Opis: " + opis + "\n" + "Ile trwa dni: " + iledni +  "\n";
	//dodanie informacji o danym dniu �wi�ta sta�ego 
	for (DanyDzien dd : dzien){
		info += "w tym dniu: " + dd.nazwaD +  "\n";
	}
	return info;
	}
}

