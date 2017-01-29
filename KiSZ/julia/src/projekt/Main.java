package projekt;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

	
	//public static void say(String s) { System.out.println(s);}
	
	public static void main(String[] args) throws Exception {
		
		
		Kalendarz kalendarz1 = new Kalendarz("kalendarz gregoria�ski");
		Kalendarz kalendarz2 = new Kalendarz("kalendarz lunarny");
		
	    String[] nazwaD = {"1", "2", "3", "4", "5", "6"};
	    String[] miesiac = {".01", ".02", ".03"};
	    
	    
	    // ------------TWORZYMY STYCZEN Z 31 DNIAMI---------------------
	    ArrayList<Integer> styczen = new ArrayList<Integer>();
	    for(int i=0;i<31;i++) styczen.add(i+1); // i+1 aby mieć od 1 do 31
	    // ------------TWORZYMY LUTY Z 29 DNIAMI---------------------
	    ArrayList<Integer> luty = new ArrayList<Integer>();
	    for(int i=0;i<29;i++) luty.add(i+1);
	    
	    
	    HashMap<Integer, ArrayList<Integer>> dd = new HashMap<Integer, ArrayList<Integer>>();
	    // -----------1 odpowiada Styczniowi(który ma 31 dni)-----------
	    dd.put(1, styczen);
	    // -----------2 odpowiada Lutemu(który ma 29 dni)-----------
	    dd.put(2, luty);
	    
	    System.out.println(dd.get(1).get(3));
	    System.out.println(dd);
	    
	    //DanyDzien ddMOJ = DanyDzien.utworzDanyDzien(kalendarz1, dd, miesiac)
	    DanyDzien dd1 = DanyDzien.utworzDanyDzien(kalendarz1, nazwaD[1], miesiac[1]);
	    DanyDzien dd2 = DanyDzien.utworzDanyDzien(kalendarz1, nazwaD[5], miesiac[1]);
	    
	    
	    System.out.println(dd1);
	    System.out.println(dd2);
	        
	}        
}       

