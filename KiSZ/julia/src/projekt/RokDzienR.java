package projekt;

public class RokDzienR {
////
	
	//atrybut przechowujacy rok,  zwiazane z szczeg�lnym dniem(ruchomym) w danym dniu
	public int rok;
	public SzczegolnyDzienR szczegolnyDzien;  //impl.licznosci 1 w celu implementacji asocjacji z atrybutem - szcze�lny dzie�
	public DanyDzien danyDzien; //impl.licznosci 1 w celu implementacji asocjacji z atrybutem
	
	public RokDzienR(int rok, SzczegolnyDzienR szczegolnyDzien, DanyDzien danyDzien){
		this.rok = rok; 
		this.szczegolnyDzien = szczegolnyDzien;
		this.danyDzien = danyDzien; 
	}
	

}

