import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

public class Operations {
	
	/*public HashMap<Integer, ArrayList<Integer>> getMinimalsPolynomial(){
		HashMap<Integer, ArrayList<Integer>> hashmap = new HashMap<Integer,ArrayList<Integer>>();
		ArrayList<String> minimalPolynomials = new ArrayList<String>();
		String m1 = "100011101";
		String m3 = "101110111";
		String m5 = "111110011";
		String m7 = "101101001";
		String m9 = "110111101";
		String m11 = "111100111";
		String m13 = "100101011";
		//String m15 = "111010111";
		
		minimalPolynomials.add(m1);
		minimalPolynomials.add(m3);
		minimalPolynomials.add(m5);
		minimalPolynomials.add(m7);
		minimalPolynomials.add(m9);
		minimalPolynomials.add(m11);
		minimalPolynomials.add(m13);
		//minimalPolynomials.add(m15);
		
		for(int i=0;i<minimalPolynomials.size();i++){
			ArrayList<Integer> arrayList = new ArrayList<Integer>();
			for(int j=0;j<m1.length();j++)
			arrayList.add(Character.getNumericValue((minimalPolynomials.get(i).charAt(j))));
			hashmap.put(i, arrayList);
        }
		return hashmap;
	}*/
	public HashMap<Integer, ArrayList<Integer>> getMinimalsPolynomial(int m){
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
				//System.out.println("Hashmap " + multiplication);
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
		return wynik;
	}
	public HashMap<Integer, ArrayList<Integer>> nww(int t, HashMap<Integer, ArrayList<Integer>> minimalPolynomials){
		HashMap<Integer, ArrayList<Integer>> hashmap = new HashMap<Integer,ArrayList<Integer>>();
		for(int i=0;i<t;i++){
			hashmap.put(i, minimalPolynomials.get(i));
		}
		/*for(int i=0;i<hashmap.size();i++){
			if(hashmap.containsValue(minimalPolynomials.get(i))) ;
		}	*/	
		return hashmap;
	}
	public ArrayList<Integer> multiplication(HashMap<Integer, ArrayList<Integer>> hashmap, int degreeOfPolynomial){
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
	    while(true){
			if(wynik_2.get(0) == 1) break;
			wynik_2.remove(0);
		}
	    return wynik_2;
	}
	public ArrayList<Integer> get_m_x( String message, int size_of_g_x, int k){
		  byte[] bytes = message.getBytes();
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
		  System.out.println("'" + message + "' to binary: " + binary);
		  String binary_s = binary.toString();
		  ArrayList <Integer> m_x = new ArrayList<Integer>();
		  for(int i=0; i<binary_s.length();i++) m_x.add(Character.getNumericValue((binary_s.charAt(i))));
		  System.out.println(m_x);
		  System.out.println(m_x.size()+" "+k);
		  int count = m_x.size(); 
		  for(int i=0; i < (k - count);i++) m_x.add(0); // dopelnienie zerami 1
		  System.out.println(m_x);
		  return m_x;
	}
	public ArrayList<Integer> get_m_xX(ArrayList<Integer> m_x, ArrayList<Integer> g_x){
		ArrayList <Integer> m_xX = new ArrayList<Integer>(); // m(x) * x^(n-k)
		for(int i=0; i < m_x.size();i++) m_xX.add(m_x.get(i));
	    for(int i=0; i < (g_x.size() - 1);i++) m_xX.add(0);  // dopelnienie zerami
	    return m_xX;
	}
	public ArrayList<Integer> division(ArrayList<Integer> m_x, ArrayList<Integer> g_x){
		ArrayList<Integer> r_x = new ArrayList<Integer>();
    	ArrayList<Integer> r_x_2 = new ArrayList<Integer>();
    	int liczba_wywalonych_zer = 0;
    	int liczba_wywalonych_zer_przed=0;
    	//Przekopiowanie wielomianu 1 po to aby miec go w calosci do brania 0
    	ArrayList<Integer> wielomian_1AA = new ArrayList<Integer>();
    	for(int i=0;i<m_x.size();i++) wielomian_1AA.add(m_x.get(i));
    	//WYWALENIE ZER
    	for(int i=0; i < wielomian_1AA.size(); i++){
			  if(wielomian_1AA.get(0) == 1) break;
			  wielomian_1AA.remove(0);
			  liczba_wywalonych_zer++;  
		}
    	liczba_wywalonych_zer_przed = liczba_wywalonych_zer;
    	//DODANIE DO RESZTY 4 LICZB Z WIELOMIANU m(x)
    	for(int i=0; i<g_x.size(); i++){	
    		r_x.add(wielomian_1AA.get(i));
    	}
    	int licznik_wywalonych_zer_w_tym_okrazeniu=0;
    	int end = 0;
    	while(true){
	    	//DODAWANIE MODULO do zmiennej r_x_2
	    	for(int i=0; i<r_x.size(); i++){
				  if((r_x.get(i) + g_x.get(i))!=1) r_x_2.add(0);
				  else r_x_2.add(1);
			}	    	
	    	//USUWAMY ZERA Z POCZĄTKU
	    	while(true){
	    		if(r_x_2.isEmpty()) break;
				if(r_x_2.get(0) == 1) break;
				r_x_2.remove(0);
				licznik_wywalonych_zer_w_tym_okrazeniu++;
			}
	    	//DODAJEMY KOLEJNĄ WARTOŚĆ Z WIELOMIANU  ---------------------------POPRAWIĆ
	    	for(int i=1; i<=licznik_wywalonych_zer_w_tym_okrazeniu; i++){
	    		try{
	    			r_x_2.add(m_x.get(g_x.size()+liczba_wywalonych_zer));
	    		} catch (IndexOutOfBoundsException e){
	    			end = 1;
	    			break;
	    		}
	    		liczba_wywalonych_zer++;
	    	}
    		//Przekopiowanie do reszty 
	    	licznik_wywalonych_zer_w_tym_okrazeniu=0;
			r_x.clear();
			for(int i=0;i<r_x_2.size();i++) r_x.add(r_x_2.get(i));
			r_x_2.clear();
			if(end==1) break;
		}
    	return r_x;
	}
	public ArrayList<Integer> get_c_x(ArrayList<Integer> m_x, ArrayList<Integer> r_x, int n){
		ArrayList<Integer> c_x = new ArrayList<Integer>();
		for(int i=0; i < m_x.size();i++) c_x.add(m_x.get(i)); // skopiowanie m_x do c_x
        for(int i=0; i < n - r_x.size() - m_x.size(); i++) c_x.add(0); // dopelnienie 0 ile trzeba
        for(int i=0; i < r_x.size();i++) c_x.add(r_x.get(i)); // dorzucenie wartości z r_x
		return c_x;
	}
	public ArrayList<Integer> enterErrors(int number, ArrayList<Integer> c_x, PrintWriter zapis,int zmienna){
		ArrayList <Integer> c_x_e = new ArrayList<Integer>();
		for(int i=0; i < c_x.size(); i++) c_x_e.add(c_x.get(i));
		//System.out.println(c_x_e);
		Random random = new Random();
		//int position=10;
		int noi=0;
		ArrayList<Integer> error_position = new ArrayList<Integer>();
		for(int i=0; i < number; i++){
			int element;
			//position++;
			noi = c_x.size()-zmienna;
			int position = random.nextInt(noi);
			//System.out.println("bląd na pozycji" + position);
			error_position.add(position);
			if(c_x_e.get(position) == 0) element = 1;
			else element = 0;
			c_x_e.set(position, element);
		}
		//zapis.print(error_position.get(0) + "\t" + error_position.get(1) + "\t" + noi + "\t");
		return c_x_e;
	}
	public ArrayList<Integer> repair(ArrayList<Integer> c_x_e, ArrayList<Integer> g_x, int t){
		ArrayList<Integer> y_x = new ArrayList<Integer>();
		ArrayList<Integer> s_x = new ArrayList<Integer>();
		
		Operations operation = new Operations();
		s_x = operation.division(c_x_e, g_x);
		
		int w_s=0; // WAGA HAMMINGA DLA SYNDROMU
        for(int i=0;i<s_x.size();i++) if(s_x.get(i)==1) w_s++;
        //System.out.println("WAGA HAMMINGA: " + w_s);
        int count = 0;
        //if(w_s == 1 ) count = 0;
        //ALGORYTM DEKODOWANIA ---------------------------------------------DOKOŃCZYĆ I POPRAWIĆ
        while(true){
	        if(w_s>t){
	        	w_s = 0;
	        	if(c_x_e.get(0)==0){
					c_x_e.remove(0);
					c_x_e.add(0);
				} else {
					c_x_e.remove(0);
					c_x_e.add(1);
				}
	        	
	        	s_x = operation.division(c_x_e, g_x);
	        	//System.out.println("OK : " + c_x_e);
	        	//System.out.println("RESZTA : " + s_x);
	        	
	        	for(int i=0;i<s_x.size();i++) if(s_x.get(i)==1) w_s++; //liczenie wagi hamminga
	        	if(count==260) {
	        		//System.out.println("zle");
	        		y_x.add(0);
	        		//zapis.print("0\n");
	        		return y_x;
	        	}
	        	count++;
	        	
	        } else{
	        	
	            ArrayList <Integer> do_dodania = new ArrayList <Integer>();
	        	for(int i=0; i<c_x_e.size()-s_x.size(); i++) do_dodania.add(0);
	        	for(int i=0; i<s_x.size(); i++) do_dodania.add(s_x.get(i));
	        	//System.out.println(do_dodania);
	        	for(int i=0; i<c_x_e.size();i++){
	        		if((c_x_e.get(i) + do_dodania.get(i))!=1) y_x.add(0);
	        		else y_x.add(1);
	        	}
	        	
	        	for(int i=0; i<count;i++){
	    			if(y_x.get(y_x.size()-1)==0){
	    				y_x.remove(y_x.size()-1);
	    				y_x.add(0,0);
	    			} else {
	    				y_x.remove(y_x.size()-1);
	    				y_x.add(0,1);
	    			}
	    		}
	        	//System.out.println("Przed c_x_e: " + c_x_e);
	        	//System.out.println("Przed s_x: " + s_x);

	        	//System.out.println("y_x: " + y_x);
	        	break;
	        }
        }
		return y_x;
	}
	public void testing(ArrayList <Integer> c_x, ArrayList <Integer> c_x_e, ArrayList <Integer> y_x, PrintWriter zapis, ArrayList<Integer> wyniki) throws FileNotFoundException{
		int check = 1;
		if(c_x.size()!=y_x.size()) check=0;
		//zapis.print(check + "\n");
		wyniki.add(check);
	}
}