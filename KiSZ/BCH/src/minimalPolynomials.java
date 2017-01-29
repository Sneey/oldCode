import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class minimalPolynomials {

	public static void main(String[] args) {
		int m = 6;
		int ilosc_elementow = (int) Math.pow(2, m);
		
		ArrayList<Integer> pseudolosowy = new ArrayList<Integer>();
		pseudolosowy.add(1);
		for (int i=0; i<m-1; i++) pseudolosowy.add(0);
		
		//s(4) = s(0) + s(1)
		if(m==4){
			for (int i=0; i<ilosc_elementow-1;i++) {
				if((pseudolosowy.get(i)+pseudolosowy.get(i+1))==1) pseudolosowy.add(1);
				else pseudolosowy.add(0);
			}
		}
		//s(5) = s(0) + s(2)
		if(m==5){
			for (int i=0; i<ilosc_elementow-1;i++) {
				if((pseudolosowy.get(i)+pseudolosowy.get(i+2))==1) pseudolosowy.add(1);
				else pseudolosowy.add(0);
			}
		}
		//s(6) = s(0) + s(1)
		if(m==6){
			for (int i=0; i<ilosc_elementow-1;i++) {
				if((pseudolosowy.get(i)+pseudolosowy.get(i+1))==1) pseudolosowy.add(1);
				else pseudolosowy.add(0);
			}
		}
		//s(7) = s(0) + s(3)
		if(m==7){
			for (int i=0; i<ilosc_elementow-1;i++) {
				if((pseudolosowy.get(i)+pseudolosowy.get(i+3))==1) pseudolosowy.add(1);
				else pseudolosowy.add(0);
			}
		}
		//s(8) = s(0) + s(3)
		if(m==8){
			for (int i=0; i<ilosc_elementow-1;i++) {
				if((pseudolosowy.get(i)+pseudolosowy.get(i+2)+pseudolosowy.get(i+3)+pseudolosowy.get(i+4))%2==1) pseudolosowy.add(1);
				else pseudolosowy.add(0);
			}
		}
		
		System.out.println(pseudolosowy);
		HashMap <Integer, ArrayList<Integer>> kolejne_slowa = new HashMap <Integer, ArrayList<Integer>>();
		for(int i=0;i<ilosc_elementow-1;i++){
			ArrayList<Integer> na_chwile = new ArrayList<Integer>();
			for(int j=0;j<m;j++) na_chwile.add(pseudolosowy.get(i+j));
			kolejne_slowa.put(i, na_chwile);
		}
		TreeMap <Integer, ArrayList<Integer>> warstwy_cyklotonomiczne = new TreeMap <Integer, ArrayList<Integer>>();
		HashMap <Integer, ArrayList<Integer>> wynik = new HashMap <Integer, ArrayList<Integer>>();
		ArrayList<Integer> jakie_mam_elementy = new ArrayList<Integer>();
		System.out.println("KOlejne slowa: " + kolejne_slowa);
		int count=1;
		int size=0;
		while(size<ilosc_elementow-2){
			ArrayList<Integer> na_chwile = new ArrayList<Integer>();
			int j = count;
			int mod = 0;
			while(true){
				mod=j%(ilosc_elementow-1);
				if(na_chwile.contains(mod)) break;
				if(jakie_mam_elementy.contains(mod)) break;
				na_chwile.add(mod);
				jakie_mam_elementy.add(mod);
				j=j*2;
			}
			warstwy_cyklotonomiczne.put(count, na_chwile);
			count+=2;
			while(jakie_mam_elementy.contains(count)) count+=2;
			for(int n=0;n<na_chwile.size();n++) size+=1;
		}
		
		//System.out.println("POsortowane??" + keys);
		
		System.out.println("Warstwy cyklotonomiczne" + warstwy_cyklotonomiczne);
		System.out.println("Jakie mam elementy" + jakie_mam_elementy);
		
		
		//------------------------------- MNOZENIE WSZYSTKICH WARSTW--------------------------------
		for(int iterator_glowny=0;iterator_glowny<warstwy_cyklotonomiczne.size();iterator_glowny++){
			//WYBRANIE Z WARSTW JAKIE MAMY : (zmiana na tabllice aby brac wartosci)
			Collection<Integer> zmienna = warstwy_cyklotonomiczne.keySet();
			Object[] zmienna_a = zmienna.toArray();
			
			HashMap<Integer, ArrayList<Integer>> forMultiplication = new HashMap<Integer,ArrayList<Integer>>();
			ArrayList<Integer> result = new ArrayList<Integer>();
			
			// BIERZEMY warstwe i rozbijamy na 4 wielomiany:
			for(int i=0;i<warstwy_cyklotonomiczne.get(zmienna_a[iterator_glowny]).size();i++){
				ArrayList<Integer> arraylist = new ArrayList<Integer>();
				arraylist.add(0);
				arraylist.add(warstwy_cyklotonomiczne.get(zmienna_a[iterator_glowny]).get(i));
				forMultiplication.put(i, arraylist);
			}
			//System.out.println(forMultiplication);
			//--------------------------------------------------
			
			// MNOŻENIE(DZIAŁA, ALE TRZEBA ZROBIĆ COŚ Z -1)
			for(int iterator=0; iterator<forMultiplication.size()-1;iterator++){
				if(iterator==0) for(int i=0;i<forMultiplication.get(0).size();i++) result.add(forMultiplication.get(0).get(i));
				HashMap<Integer, ArrayList<Integer>> multiplication = new HashMap<Integer,ArrayList<Integer>>();
				for(int i=0;i<result.size();i++){
					if(result.get(i)!=-1){
						for(int j=0;j<forMultiplication.get(1).size();j++){
							ArrayList<Integer> arraylist = new ArrayList<Integer>();
							arraylist.add((result.get(i)+forMultiplication.get(iterator+1).get(j))%(ilosc_elementow-1));
							if(multiplication.containsKey(i+j)) multiplication.get(i+j).add(arraylist.get(0)); 
							else multiplication.put(i+j, arraylist);
						}
					}
				
				}
				
				result.clear();
				
				System.out.println("Hashmap " + multiplication);
				//XORuje elementy hashmapy które zawierają więcej niż jeden element
				for(int i=0;i<multiplication.size();i++){
					result.add(i, multiplication.get(i).get(0));
					if(multiplication.get(i).size()>1){
						
						ArrayList<Integer> result1 = new ArrayList<Integer>();
						for(int j=0; j<kolejne_slowa.get(0).size(); j++){
							int licznik = 0;
							for(int k=0; k<multiplication.get(i).size();k++){
								licznik+=kolejne_slowa.get(multiplication.get(i).get(k)).get(j); // WOW, dodawnie XOR
							}
							result1.add(licznik%2);
							//System.out.println(result1);
						}
						
						if(result1.contains(1)){
							//znajdujemy zxorowane slowo w alfabecie
							for(int j=0;j<kolejne_slowa.size();j++){
								if(kolejne_slowa.get(j).equals(result1)) {
									result.set(i, j%(ilosc_elementow-1));
								}
							}
						} else {
							result.set(i, -1);
						}
					}
					//System.out.println(result);
					wynik.put(iterator_glowny, result);
				}	
			}
			//--------------------------------------------------
		}
		for(int i=0;i<wynik.size();i++){
			for(int j=0;j<wynik.get(i).size();j++){
				if(wynik.get(i).get(j)==0) wynik.get(i).set(j, 1);
				else wynik.get(i).set(j, 0);
			}
		}
		System.out.println(wynik);
	}
	
}