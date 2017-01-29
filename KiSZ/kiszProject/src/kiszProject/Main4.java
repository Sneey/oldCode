package kiszProject;

import java.util.ArrayList;
import java.util.HashMap;

public class Main4 {

	public static void main(String[] args) {
		
			ArrayList<Integer> wynik = new ArrayList<Integer>();
			ArrayList<String> wielomianyMinimalne = new ArrayList<String>();
			
			//for(int i=0; i<9;i++) wynik.add(0);
			
			String m1 = "100011101";
			String m3 = "101110111";
			String m5 = "111110011";
			String m7 = "101101001";
			String m9 = "110111101";
			String m11 = "111100111";
			String m13 = "100101011";
			
			wielomianyMinimalne.add(m1);
			wielomianyMinimalne.add(m3);
			wielomianyMinimalne.add(m5);
			wielomianyMinimalne.add(m7);
			wielomianyMinimalne.add(m9);
			wielomianyMinimalne.add(m11);
			wielomianyMinimalne.add(m13);
			
			HashMap<Integer, ArrayList<Integer>> hashmap = new HashMap<Integer,ArrayList<Integer>>();
			//Wrzucenie wielomianów minimalnych do hashmapy 
	        for(int i=0;i<wielomianyMinimalne.size();i++){
				ArrayList<Integer> arrayList = new ArrayList<Integer>();
				for(int j=0;j<m1.length();j++)
				arrayList.add(Character.getNumericValue((wielomianyMinimalne.get(i).charAt(j))));
	        	
				hashmap.put(i, arrayList);
	        }
	        
	        ArrayList<Integer> arrayList_1 = new ArrayList<Integer>();
        	ArrayList<Integer> arrayList_2 = new ArrayList<Integer>();
	        
        	//mnożenie 2 pierwszych
	        for(int i=0;i<m1.length();i++){
	        	arrayList_1 = hashmap.get(0);
	        	arrayList_2 = hashmap.get(1);
	        	if(arrayList_1.get(i)!=0){
		        	for(int j=0;j<m1.length();j++){
		        		if ((arrayList_1.get(i)+arrayList_2.get(j))!=1){ 
		        			if(wynik.contains(((arrayList_1.size()-1)-i)+((arrayList_2.size()-1)-j))) wynik.remove(new Integer(((arrayList_1.size()-1)-i)+((arrayList_2.size()-1)-j)));
		        			else wynik.add(((arrayList_1.size()-1)-i)+((arrayList_2.size()-1)-j));
			        	}
			        	//System.out.println(wynik);
			        }
	        	}
	        }
	        
	        ArrayList<Integer> wynik_2 = new ArrayList<Integer>();
	        for(int i=16;i>=0;i--){
	        	if(wynik.contains(i)) wynik_2.add(1);
	        	else wynik_2.add(0);
	        }
	        System.out.println(wynik_2);
	        System.out.println(wynik_2.size());
	        System.out.println(wynik);
        	System.out.println(hashmap);
        	System.out.println(hashmap.size());
	      	wynik.clear();
	      //mnożenie 2 wynik_2 z następnym
        	arrayList_1 = wynik_2;
        	arrayList_2 = hashmap.get(2);  
        	for(int i=0;i<wynik_2.size();i++){
	        	if(arrayList_1.get(i)!=0){
		        	for(int j=0;j<m1.length();j++){
		        		if ((arrayList_1.get(i)+arrayList_2.get(j))!=1){ 
		        			if(wynik.contains(((arrayList_1.size()-1)-i)+((arrayList_2.size()-1)-j))) wynik.remove(new Integer(((arrayList_1.size()-1)-i)+((arrayList_2.size()-1)-j)));
		        			else wynik.add(((arrayList_1.size()-1)-i)+((arrayList_2.size()-1)-j));
			        	}
			        	//System.out.println(wynik);
			        }
	        	}
	        }
	          
	        
	        wynik_2.clear();

	        hashmap.put(hashmap.size(), wynik);
	        for(int i=24;i>=0;i--){
	        	if(wynik.contains(i)) wynik_2.add(1);
	        	else wynik_2.add(0);
	        }
	        System.out.println(wynik_2);
	        System.out.println(wynik);
        	System.out.println(hashmap);
        	System.out.println(hashmap.size());
	        /*while(true){
	        	for(int i=2;i<m1.length();i++){
		        	arrayList_1 = hashmap.get(hashmap.size());
		        	arrayList_2 = hashmap.get(i);
		        	if(arrayList_1.get(i)!=0){
			        	for(int j=0;j<m1.length();j++){
			        		if ((arrayList_1.get(i)+arrayList_2.get(j))!=1){ 
			        			if(wynik.contains((8-i)+(8-j))) wynik.remove(new Integer((8-i)+(8-j)));
			        			else wynik.add((8-i)+(8-j));
				        	}
				        	//System.out.println(wynik);
				        }
		        	}
		        }
		        hashmap.put(hashmap.size(), wynik);    	
	        }*/
	        
	 }

}