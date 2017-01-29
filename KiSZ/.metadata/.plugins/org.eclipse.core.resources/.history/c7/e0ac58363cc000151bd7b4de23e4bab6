package projekt;

public class CykliczneW extends Wydarzenie{
	
	public String podtytul;
	public float oplataD;
	public int ileDni; 
	//public float kosztC;
	public int czesc;
	public String wymagania;
	
	public CykliczneW(String nazwa, String opis, String harmonogram, String podtytul, float oplataD, int ileDni, int czesc, String wymagania){
		super(nazwa, opis, harmonogram);
		this.podtytul = podtytul;
		this.oplataD = oplataD;
		this.ileDni = ileDni;
		this.czesc = czesc;
		this.wymagania = wymagania;
		}

	public float getKosztCalkowity(){
		return oplataD * ileDni;
	}
	
}
