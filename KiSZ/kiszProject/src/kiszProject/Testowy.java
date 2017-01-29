package kiszProject;

import java.util.ArrayList;
import java.util.HashMap;

public class Testowy {

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
        //mnożenie
        System.out.println(codeBCH.multiplicationPolynomials(hashmap, degreeOfPolynomial));
		// TODO Auto-generated method stub
		// DLUGOSC SLOWA KODOWEGO 255 ZDOLNOŚĆ KOREKCYJNA 7
		String asd="001111000000010101001110010111010101001001100010010011010111011001110111011001010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		String asdd="100001101111000110111111001111100110010110010001000001100110100001100101011011000110110001101111000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		System.out.println("Dane wejściowe maja dlugość " + asd.length());
		System.out.println("ZAszyfrowane dane maja dlugość " + asdd.length());
	
		//Zamiana na tekstu na binarkę 
		String s = "hello";
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
		  System.out.println(m_x);
		  System.out.println(m_x.size());
		  
		  int count = m_x.size();
		  for(int i=0; i < (199 - count);i++) // dopelnienie zerami 1 
			  m_x.add(0);
		  System.out.println("m(x) : " + m_x);
		  System.out.println(m_x.size());
		  
		  ArrayList <Integer> g_x = new ArrayList<Integer>();
		  g_x = codeBCH.multiplicationPolynomials(hashmap, degreeOfPolynomial);
		  System.out.println("g(x) : " + g_x);
		  System.out.println(g_x.size());
		  
		  for(int i=0; i < (g_x.size() - 1);i++) // dopelnienie zerami 2 
			  m_x.add(0);
		  System.out.println("m(x)*x^(n-k)= m(x)*x^56 : " + m_x);
		  System.out.println(m_x.size());
		  System.out.println("-------------_DZIELENIE-----------------");
		  
		  
		  ArrayList <Integer> wynik = new ArrayList<Integer>();
		  ArrayList <Integer> wynik_2 = new ArrayList<Integer>();
		  ArrayList <Integer> reszta = new ArrayList<Integer>();
		  ArrayList <Integer> potegi = new ArrayList<Integer>();
		  
		  for(int i=0;i<m_x.size();i++){
			  if (m_x.get(i)!=0) potegi.add(Math.abs(i-254));
			  
		  }
		  
		  for(int i=56;i>=0;i--){
	        	if(potegi.contains(i+197)) wynik.add(1);
	        	else wynik.add(0);
	        }
		  
		  System.out.println(wynik);
		  System.out.println(wynik.size());
		  for(int i=0; i<wynik.size(); i++){
			  if((wynik.get(i) + g_x.get(i))!=1) wynik_2.add(0);
			  else wynik_2.add(1);
		  }
		  
		  int licznik_kasowanych_zer = 0;
		  System.out.println(wynik_2);
		  System.out.println(wynik_2.size());
		  for(int i=0; i< wynik_2.size(); i++){
			  if(wynik_2.get(0) == 1) break;
			  wynik_2.remove(0);
			  licznik_kasowanych_zer+=1;
		  }
		  System.out.println(wynik_2);
		  System.out.println(wynik_2.size());
		  System.out.println(licznik_kasowanych_zer);
		  
		  for(int i=1; i<=licznik_kasowanych_zer; i++) wynik_2.add(m_x.get(wynik_2.size()+i)); //WZIĄŁEM 58 i 59
		  
		  System.out.println(wynik_2);
		  System.out.println(wynik_2.size());
		  
		  wynik.clear();
		  for(int i=0; i<wynik_2.size(); i++){
			  if((wynik_2.get(i) + g_x.get(i))!=1) wynik.add(0);
			  else wynik.add(1);
		  }
		  
		  System.out.println(wynik);
		  System.out.println(wynik.size());
		  //KIEDY mi się skończą żeczy do brania ??? --> WTEDY kiedy usunę 255 - 57 - 57 = 141 ????  
	}
	
}
