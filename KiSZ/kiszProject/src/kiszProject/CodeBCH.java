package kiszProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CodeBCH {

	public static void main(String[] args) {
		ArrayList<String> wielomianyMinimalne = new ArrayList<String>();
		String m1 = "100011101";
		String m3 = "101110111";
		String m5 = "111110011";
		String m7 = "101101001";
		String m9 = "110111101";
		String m11 = "111100111";
		String m13 = "100101011";
		//String m15 = "111010111";
		
		wielomianyMinimalne.add(m1);
		wielomianyMinimalne.add(m3);
		wielomianyMinimalne.add(m5);
		wielomianyMinimalne.add(m7);
		wielomianyMinimalne.add(m9);
		wielomianyMinimalne.add(m11);
		wielomianyMinimalne.add(m13);
		//wielomianyMinimalne.add(m15);
		
		HashMap<Integer, ArrayList<Integer>> hashmap = new HashMap<Integer,ArrayList<Integer>>();
		//Wrzucenie wielomianów minimalnych do hashmapy 
        for(int i=0;i<wielomianyMinimalne.size();i++){
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			for(int j=0;j<m1.length();j++)
			arrayList.add(Character.getNumericValue((wielomianyMinimalne.get(i).charAt(j))));
        	
			hashmap.put(i, arrayList);
        }
        
        ArrayList <Integer> m_x = new ArrayList<Integer>();
        ArrayList <Integer> m_xX = new ArrayList<Integer>(); // m(x) * x^(n-k)
        ArrayList <Integer> g_x = new ArrayList<Integer>();
        ArrayList <Integer> r_x = new ArrayList<Integer>();
        ArrayList <Integer> c_x = new ArrayList<Integer>();
        ArrayList <Integer> c_x_e = new ArrayList<Integer>(); // c(x) z błędem
        ArrayList <Integer> s_x = new ArrayList<Integer>(); //Syndrom
        
        
        int degreeOfPolynomial = (hashmap.get(0).size() - 1); //stopień wielomianów minimalnych
        CodeBCH codeBCH = new CodeBCH();
        //mnożenie
        g_x = codeBCH.multiplicationPolynomials(hashmap, degreeOfPolynomial);
        m_x = codeBCH.get_m_x(g_x.size());
        for(int i=0; i < m_x.size();i++) m_xX.add(m_x.get(i));
        for(int i=0; i < (g_x.size() - 1);i++) m_xX.add(0);  // dopelnienie zerami 2 
        r_x = codeBCH.dzielenie(m_xX, g_x);
        System.out.println("Wielomian generacyjny: " + g_x);
        System.out.println("m(x)*x^(n-k): " + m_xX);
        System.out.println("Reszta z dzielenia m_x przez g_x: " + r_x);
        //c(x)
        for(int i=0; i < m_x.size();i++) c_x.add(m_x.get(i)); // skopiowanie m_x do c_x
        for(int i=0; i < 255 - r_x.size() - m_x.size(); i++) c_x.add(0); // dopelnienie 0 ile trzeba
        for(int i=0; i < r_x.size();i++) c_x.add(r_x.get(i)); // dorzucenie wartości z r_x
        System.out.println("Nasz c_x: " + c_x + "\n" + c_x.size());
        //Wrzucenie błędów
        c_x_e = codeBCH.enterErrors(1, c_x);
        System.out.println("NAsz c_x z błędem: " + c_x_e);
        //|c(x)|g(x)
        s_x = codeBCH.dzielenie(c_x_e, g_x);
        System.out.println("s_x: "+s_x); //NASZ SYNDROM
        int t = 7;
        int w_s=0; // WAGA HAMMINGA DLA SYNDROMU
        for(int i=0;i<s_x.size();i++) if(s_x.get(i)==1) w_s++;
        System.out.println("WAGA HAMMINGA: " + w_s);
        
        int count = 1;
        if(w_s == 1 ) count = 0;
        //ALGORYTM DEKODOWANIA ---------------------------------------------DOKOŃCZYĆ I POPRAWIĆ
        while(true){
	        if(w_s>t){
	        	w_s = 0;
	        	if(c_x_e.get(0)==0){
					c_x_e.remove(0);
					c_x_e.add(c_x_e.size()-1, 0);
				} else {
					c_x_e.remove(0);
					c_x_e.add(c_x_e.size()-1, 1);
				}
	        	//System.out.println("OK : " + c_x_e);
	        	//System.out.println("SIZE: " + c_x_e.size());
	        	s_x = codeBCH.dzielenie(c_x_e, g_x);
	        	for(int i=0;i<s_x.size();i++) if(s_x.get(i)==1) w_s++; //liczenie wagi hamminga

	        	if(count==260) {
	        		System.out.println("zle");
	        		break;
	        	}
	        	count++;
	        	
	        } else{
	        	for(int i=0; i<count;i++){
	    			if(c_x_e.get(c_x_e.size()-1)==0){
	    				c_x_e.remove(c_x_e.size()-1);
	    				c_x_e.add(0,0);
	    			} else {
	    				c_x_e.remove(c_x_e.size()-1);
	    				c_x_e.add(0,1);
	    			}
	    		}
	        	System.out.println("Przed c_x_e: " + c_x_e);
	        	System.out.println("Przed s_x: " + s_x);
	        	ArrayList <Integer> nowe_c_x = new ArrayList <Integer>();
	            ArrayList <Integer> do_dodania = new ArrayList <Integer>();
	        	for(int i=0; i<c_x_e.size()-s_x.size(); i++) do_dodania.add(0);
	        	for(int i=0; i<s_x.size(); i++) do_dodania.add(s_x.get(i));
	        	System.out.println(do_dodania);
	        	for(int i=0; i<c_x_e.size();i++){
	        		if((c_x_e.get(i) + do_dodania.get(i))!=1) nowe_c_x.add(0);
	        		else nowe_c_x.add(1);
	        	}
	        	System.out.println("po skorygowaniu : " + nowe_c_x);
	        	break;
	        }
        }
	}
	// FUNKCJA MNOŻENIA WIELOMIANÓW
	public ArrayList<Integer> multiplicationPolynomials(HashMap<Integer, ArrayList<Integer>> hashmap, int degreeOfPolynomial){
		ArrayList<Integer> arrayList_1 = new ArrayList<Integer>();
     	ArrayList<Integer> arrayList_2 = new ArrayList<Integer>();
		ArrayList<Integer> wynik = new ArrayList<Integer>();
	    ArrayList<Integer> wynik_2 = new ArrayList<Integer>(); // wynik w postaci dwójkowej
	    wynik_2 = hashmap.get(0);
	    int count=degreeOfPolynomial*2;
	    for(int k=0; k<(hashmap.size()-1); k++){
	        	for(int i=0;i<wynik_2.size();i++){
		        	if(k==0) arrayList_1 = hashmap.get(0);
		        	else arrayList_1 = wynik_2;
		        	arrayList_2 = hashmap.get(k+1);
		        	if(arrayList_1.get(i)!=0){
			        	for(int j=0;j<arrayList_2.size();j++){
			        		if ((arrayList_1.get(i)+arrayList_2.get(j))!=1){ 
			        			if(wynik.contains(((arrayList_1.size()-1)-i)+((arrayList_2.size()-1)-j))) wynik.remove(new Integer(((arrayList_1.size()-1)-i)+((arrayList_2.size()-1)-j)));
			        			else wynik.add(((arrayList_1.size()-1)-i)+((arrayList_2.size()-1)-j));
				        	}
				        }
		        	}
		        }
		        wynik_2.clear();
		        for(int i=count;i>=0;i--){
		        	if(wynik.contains(i)) wynik_2.add(1);
		        	else wynik_2.add(0);
		        }
		        wynik.clear();
		        count+=degreeOfPolynomial;
     	}
	    return wynik_2;
	}
	//FUNKCJA DZIELENIA WIELOMIANÓW
	public ArrayList<Integer> dzielenie(ArrayList<Integer> wielomian_1A,ArrayList<Integer> dzielnikA){
		ArrayList<Integer> reszta = new ArrayList<Integer>();
    	ArrayList<Integer> reszta_2 = new ArrayList<Integer>();
    	int liczba_wywalonych_zer=0;
    	int liczba_wywalonych_zer_przed=0;
    	//Przekopiowanie wielomianu 1 po to aby miec go w calosci do brania 0
    	ArrayList<Integer> wielomian_1AA = new ArrayList<Integer>();
    	for(int i=0;i<wielomian_1A.size();i++) wielomian_1AA.add(wielomian_1A.get(i));
    	//WYWALENIE ZER
    	for(int i=0; i < wielomian_1A.size(); i++){
			  if(wielomian_1A.get(0) == 1) break;
			  wielomian_1A.remove(0);
			  liczba_wywalonych_zer++;  
		}
    	liczba_wywalonych_zer_przed = liczba_wywalonych_zer;
    	//DODANIE DO RESZTY 4 LICZB Z WIELOMIANU m(x)
    	for(int i=0; i<dzielnikA.size(); i++){	
    		reszta.add(wielomian_1A.get(i));
    	}
    	int licznik_wywalonych_zer_w_tym_okrazeniu=0;
    	
    	while(true){
        	//System.out.println(wielomian_1A.size()-dzielnikA.size());
	    	if((liczba_wywalonych_zer-2*liczba_wywalonych_zer_przed)>=(wielomian_1A.size()-dzielnikA.size())){
	    		break;
	    	}
	    	//DODAWANIE MODULO do zmiennej reszta_2
	    	for(int i=0; i<reszta.size(); i++){
				  if((reszta.get(i) + dzielnikA.get(i))!=1) reszta_2.add(0);
				  else reszta_2.add(1);
			}
	    	//USUWAMY ZERA Z POCZĄTKU
	    	while(true){
	    		if(reszta_2.isEmpty()) break;
				if(reszta_2.get(0) == 1) break;
				reszta_2.remove(0);
				licznik_wywalonych_zer_w_tym_okrazeniu++;
			}
	    	//DODAJEMY KOLEJNĄ WARTOŚĆ Z WIELOMIANU  ---------------------------POPRAWIĆ
	    	for(int i=1; i<=licznik_wywalonych_zer_w_tym_okrazeniu; i++){
	    		if((liczba_wywalonych_zer-2*liczba_wywalonych_zer_przed)>=(wielomian_1A.size()-dzielnikA.size())) continue;
	    		//System.out.println(dzielnikA.size()+(liczba_wywalonych_zer-liczba_wywalonych_zer_przed));
	    		reszta_2.add(wielomian_1AA.get(dzielnikA.size()+(liczba_wywalonych_zer-liczba_wywalonych_zer_przed)));
	    		liczba_wywalonych_zer++;
	    	}
	    	//Przekopiowanie do reszty 
	    	licznik_wywalonych_zer_w_tym_okrazeniu=0;
			reszta.clear();
			for(int i=0;i<reszta_2.size();i++) reszta.add(reszta_2.get(i));
			reszta_2.clear();			
			//System.out.println(reszta);
		}
    	if(wielomian_1AA.size()<255) wielomian_1AA.add(0, 0); // dodanie 0 do WIELOMIANU1AA jesli mniejsze niz 255
    	while(true){
    		if(wielomian_1A.size()<255) wielomian_1A.add(0, 0); // dodanie 0 do WIELOMIANU1A jesli mniejsze niz 255	
    		else break;
    	}
    	return reszta;
	}
	public ArrayList<Integer> get_m_x(int size_of_g_x){
		String s = "vwe";
		  byte[] bytes = s.getBytes();
		  StringBuilder binary = new StringBuilder();
		  for (byte b : bytes)
		  {
		     int val = b;
		     for (int i = 0; i < 8; i++)
		     {
		        binary.append((val & 128) == 0 ? 0 : 1);
		        val <<= 1;
		     }
		  }
		  System.out.println("'" + s + "' to binary: " + binary);
		  String binary_s = binary.toString();
		  ArrayList <Integer> m_x = new ArrayList<Integer>();
		  for(int i=0; i<binary_s.length();i++)
			  m_x.add(Character.getNumericValue((binary_s.charAt(i))));
		  
		  int count = m_x.size();
		  for(int i=0; i < (199 - count);i++) // dopelnienie zerami 1 
			  m_x.add(0);
		return m_x;
	}
	public ArrayList<Integer> enterErrors(int ile, ArrayList<Integer> c_x){
		ArrayList <Integer> c_x_e = new ArrayList<Integer>();
		for(int i=0; i < c_x.size(); i++) c_x_e.add(c_x.get(i));
		System.out.println(c_x_e);
		Random random = new Random();
		for(int i=0; i < ile; i++){
			int element;
			int position = random.nextInt(c_x.size() - 147 - 1) + 147;
			System.out.println(position);
			if(c_x_e.get(position) == 0) element = 1;
			else element = 0;
			c_x_e.set(position, element);
		}
		return c_x_e;
	}
}