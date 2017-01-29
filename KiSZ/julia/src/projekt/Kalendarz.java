package projekt;

import java.util.HashSet;
import java.util.Vector;

public class Kalendarz {

	public String nazwaK; 
    private Vector<DanyDzien> dni = new Vector<DanyDzien>();
    private static HashSet<DanyDzien> wszystkiedni = new HashSet<DanyDzien>();
    
	public Kalendarz(String nazwaK){
		this.nazwaK = nazwaK;
	}
	
	public void dodajDzien(DanyDzien danyDzien) throws Exception{
		if(!wszystkiedni.contains(danyDzien)){
			//sprawdzanie czy dany dzien nie zostal dodany do innego kalendarza
			if(wszystkiedni.contains(danyDzien)){
				throw new Exception ("ten dzie� zosta� ju� powi�zany z danym kalendarzem!");
			}
			//Zapami�tuje na li�cie "wszystkich dni" czyli przeciwdzia�a wsp�dzieleniu cz�ci 
			wszystkiedni.add(danyDzien);
		    dni.add(danyDzien);
			}
	}
	public String toString(){
		String info = "Kalendarz: " + nazwaK + "\n";
		for(DanyDzien dd : dni){
			info += " Dane Dni w kalendarzu: " + dd.nazwaD + "\n";
		}
		return info; 
		}
	
}

