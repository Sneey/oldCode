package kiszProject;

import java.util.ArrayList;
import java.util.HashMap;

public class Dzielenie_naszego {

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
        
        int degreeOfPolynomial = (hashmap.get(0).size() - 1); //stopień wielomianów minimalnych
        CodeBCH codeBCH = new CodeBCH();
      
        //Zamiana na tekstu na binarkę 
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
		  //binary_s = "00000011";
		  //System.out.println("ZMIENILEM NA INNE" + binary_s);
		  ArrayList <Integer> m_x = new ArrayList<Integer>();
		  for(int i=0; i<binary_s.length();i++)
			  m_x.add(Character.getNumericValue((binary_s.charAt(i))));
		  System.out.println(m_x);
		  System.out.println(m_x.size());
		  
		  int count = m_x.size();
		  for(int i=0; i < (199 - count);i++) // dopelnienie zerami 1 
			  m_x.add(0);
		  System.out.println("m(x) : " + m_x);
		  System.out.println(m_x.size());
		  
		  //poprzez mnożenie wielomianów minimalnych uzyskujemy g(x)
		  ArrayList <Integer> g_x = new ArrayList<Integer>();
		  g_x = codeBCH.multiplicationPolynomials(hashmap, degreeOfPolynomial);
		  System.out.println("g(x) : " + g_x);
		  System.out.println(g_x.size());
		  
		  for(int i=0; i < (g_x.size() - 1);i++) // dopelnienie zerami 2 
			  m_x.add(0);
		  System.out.println("m(x)*x^(n-k)= m(x)*x^56 : " + m_x);
		  System.out.println(m_x.size());
		  Dzielenie_naszego dzielenie_naszego = new Dzielenie_naszego();
		  System.out.println("r(x) = " + dzielenie_naszego.dzielenie(m_x, g_x));
		  
	}
	
	ArrayList<Integer> dzielenie(ArrayList<Integer> wielomian_1A,ArrayList<Integer> dzielnikA){
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
    	System.out.println(liczba_wywalonych_zer_przed);
    	//DODANIE DO RESZTY 4 LICZB Z WIELOMIANU m(x)
    	for(int i=0; i<dzielnikA.size(); i++){	
    		reszta.add(wielomian_1A.get(i));
    	}
    	System.out.println("resztaPRZEd" + reszta);
    	int licznik_wywalonych_zer_w_tym_okrazeniu=0;
    	
    	while(true){
        	//System.out.println(wielomian_1A.size()-dzielnikA.size());
	    	if((liczba_wywalonych_zer-2)>=(wielomian_1A.size()-dzielnikA.size())){
	    		break;
	    	}
	    	//DODAWANIE MODULO do zmiennej reszta_2
	    	for(int i=0; i<reszta.size(); i++){
				  if((reszta.get(i) + dzielnikA.get(i))!=1) reszta_2.add(0);
				  else reszta_2.add(1);
			}
	    	//USUWAMY ZERA Z POCZĄTKU
	    	while(true){
				  if(reszta_2.get(0) == 1) break;
				  reszta_2.remove(0);
				  licznik_wywalonych_zer_w_tym_okrazeniu++;
			}
	    	//DODAJEMY KOLEJNĄ WARTOŚĆ Z WIELOMIANU  ---------------------------POPRAWIĆ
	    	for(int i=1; i<=licznik_wywalonych_zer_w_tym_okrazeniu; i++){
	    		if((liczba_wywalonych_zer-2)>=(wielomian_1A.size()-dzielnikA.size())) continue;
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
		return reszta;
	}

}