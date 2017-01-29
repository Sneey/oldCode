package kiszProject;

import java.util.ArrayList;

public class Dzielenie_mniejszego {

	public static void main(String[] args) {
		String wielomian_1 ="1101000";
		String dzielnik = "1011";
		
		ArrayList<Integer> wielomian_1A = new ArrayList<Integer>();
		ArrayList<Integer> dzielnikA = new ArrayList<Integer>();
		for(int i=0;i<wielomian_1.length();i++) wielomian_1A.add(Character.getNumericValue((wielomian_1.charAt(i))));
		for(int i=0;i<dzielnik.length();i++) dzielnikA.add(Character.getNumericValue((dzielnik.charAt(i))));
		
		System.out.println(wielomian_1A);
    	System.out.println(dzielnikA);
    	System.out.println(dzielnikA.size());
    	
    	ArrayList<Integer> reszta = new ArrayList<Integer>();
    	ArrayList<Integer> reszta_2 = new ArrayList<Integer>();
    	
    	//WYWALENIE ZER
    	for(int i=0; i < wielomian_1A.size(); i++){
			  if(wielomian_1A.get(0) == 1) break;
			  wielomian_1A.remove(0);
		}
    	//DODANIE DO RESZTY 4 LICZB Z WIELOMIANU m(x)
    	for(int i=0; i<dzielnikA.size(); i++){	
    		reszta.add(wielomian_1A.get(i));
    	}
    	System.out.println(reszta);
    	
    	//DODAWANIE MODULO do zmiennej reszta_2
    	for(int i=0; i<reszta.size(); i++){
			  if((reszta.get(i) + dzielnikA.get(i))!=1) reszta_2.add(0);
			  else reszta_2.add(1);
		}
    	System.out.println(reszta_2);
    	
    	//USUWAMY ZERA Z POCZĄTKU
    	int liczba_wywalonych_zer=0;
    	int licznik_wywalonych_zer_w_tym_okrazeniu=0;
    	for(int i=0; i< reszta_2.size(); i++){
			  if(reszta_2.get(0) == 1) break;
			  reszta_2.remove(0);
			  licznik_wywalonych_zer_w_tym_okrazeniu++;
		}
    	System.out.println(reszta_2);
    	
    	//DODAJEMY KOLEJNĄ WARTOŚĆ Z WIELOMIANU
    	for(int i=1; i<=licznik_wywalonych_zer_w_tym_okrazeniu; i++){
    		reszta_2.add(wielomian_1A.get(dzielnikA.size()+i));
    		liczba_wywalonych_zer++;
    	}
		licznik_wywalonych_zer_w_tym_okrazeniu=0;
    	
		System.out.println(reszta_2);
    	reszta.clear();
    	
    	//DODAWANIE MODULO
    	for(int i=0; i<reszta_2.size(); i++){
			  if((reszta_2.get(i) + dzielnikA.get(i))!=1) reszta.add(0);
			  else reszta.add(1);
		}
    	System.out.println(reszta);
    	
    	//USUWAMY ZERA Z POCZĄTKU
    	for(int i=0; i< reszta.size(); i++){
			  if(reszta.get(0) == 1) break;
			  reszta.remove(0);
			  licznik_wywalonych_zer_w_tym_okrazeniu++;
		}
    	System.out.println(reszta);
    	
    	//DODAJEMY KOLEJNĄ WARTOŚĆ Z WIELOMIANU
    	for(int i=1; i<=licznik_wywalonych_zer_w_tym_okrazeniu; i++){
    		reszta.add(wielomian_1A.get(dzielnikA.size()+liczba_wywalonych_zer+i));
    		liczba_wywalonych_zer++;
    	}
    	System.out.println(reszta);
    	licznik_wywalonych_zer_w_tym_okrazeniu=0;
    	reszta_2.clear();
    	
    	//DODAWANIE MODULO
    	for(int i=0; i<reszta.size(); i++){
			  if((reszta.get(i) + dzielnikA.get(i))!=1) reszta_2.add(0);
			  else reszta_2.add(1);
		}
    	System.out.println(reszta_2);
    	
    	//USUWAMY ZERA Z POCZĄTKU
    	for(int i=0; i< reszta_2.size(); i++){
			  if(reszta_2.get(0) == 1) break;
			  reszta_2.remove(0);
			  licznik_wywalonych_zer_w_tym_okrazeniu++;
		}
    	System.out.println(reszta_2);
    	System.out.println(liczba_wywalonych_zer);
    	System.out.println(dzielnikA.size());
    	//DODAJEMY KOLEJNĄ WARTOŚĆ Z WIELOMIANU
    	for(int i=1; i<=licznik_wywalonych_zer_w_tym_okrazeniu; i++){
    		reszta_2.add(wielomian_1A.get(dzielnikA.size()+liczba_wywalonych_zer));
    		liczba_wywalonych_zer++;
    	}
    	System.out.println(reszta_2);

    	licznik_wywalonych_zer_w_tym_okrazeniu=0;
    	reszta.clear();
    	
    	//DODAWANIE MODULO
    	for(int i=0; i<reszta_2.size(); i++){
			  if((reszta_2.get(i) + dzielnikA.get(i))!=1) reszta.add(0);
			  else reszta.add(1);
		}
    	System.out.println(reszta);
    	
    	//USUWAMY ZERA Z POCZĄTKU
    	while(true){
			  if(reszta.get(0) == 1) break;
			  reszta.remove(0);
			  licznik_wywalonych_zer_w_tym_okrazeniu++;
		}
    	System.out.println(reszta);
    	System.out.println(liczba_wywalonych_zer);
	}

}
