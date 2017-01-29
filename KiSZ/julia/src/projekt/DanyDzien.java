package projekt;

import java.util.ArrayList;


public class DanyDzien {

	public String nazwaD;
	public String miesiac;
	public Kalendarz kalendarz; 
	public ArrayList<RokDzienR> rokDzienR; //impl.asocjacji * z RokDzienR
	public ArrayList<RokWyd> rokWyd; //impl.asocjacji * z RokWyd
	
	public DanyDzien(Kalendarz kalendarz, String nazwaD, String miesiac){
		this.nazwaD = nazwaD;
		this.miesiac = miesiac;
		this.kalendarz = kalendarz;
		this.rokDzienR = new ArrayList<RokDzienR>();
		this.rokWyd = new ArrayList<RokWyd>();
	}
	
	 public static DanyDzien utworzDanyDzien(Kalendarz kalendarz, String nazwaD, String miesiac) throws Exception{
			if(kalendarz == null){
			throw new Exception("Kalendarz nie istnieje");	
			}
			//tworzy nowy dzien
			DanyDzien dd = new DanyDzien(kalendarz, nazwaD, miesiac);
			//dodaj do kalendarza
			kalendarz.dodajDzien(dd);
			
			return dd;
}
	 public String toString(){
			String info = "Dany dzie�: " + nazwaD + ' ' + miesiac + "\n";
			
			return info;  
			}
	 
	//impementacja asoscjacji dla licznosci * ze �wi�tem Sta�ym 
		
		private ArrayList<SwietoSt> swietoSt = new ArrayList<SwietoSt>();
		
		//sprawdzenie czy mamy juz info o tym swiecie
		public void dodajSwietoSt(SwietoSt noweSwieto){
			if(!swietoSt.contains(noweSwieto)){
				swietoSt.add(noweSwieto);
				//Dodaj info zwrotn�
				
				noweSwieto.dodajDzien(this);
			}
		}
		
		public String toStringSwietoSt(){
			String info = "Dany dzien: " + nazwaD + "\n";
			//Dodaj info o swietach w danym dniu
			for (SwietoSt sst : swietoSt){
				info += "swieto stale" + sst.nazwa + "\n";
			}
			return info;
		}
		
		//implementacaja asocjacji z klas� po�rednicz�c�/atrubutem ze Szczeg�lnym Dniem (atrybut Rok)
		
		
}

