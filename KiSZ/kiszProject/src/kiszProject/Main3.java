package kiszProject;

import java.util.ArrayList;
import java.util.HashMap;

public class Main3 {

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
	        HashMap<Integer, ArrayList<Integer>> wyniki = new HashMap<Integer,ArrayList<Integer>>();
			
	        ArrayList<Integer> arrayList_1 = new ArrayList<Integer>();
        	ArrayList<Integer> arrayList_2 = new ArrayList<Integer>();
	        
        	//mnożenie
	        for(int k=0;k<(wielomianyMinimalne.size()-1);k++){
	        	if(k==0) arrayList_1 = hashmap.get(0);
	        	else arrayList_1 = hashmap.get(7);
	        	for(int i=0;i<arrayList_1.size();i++){
		        	arrayList_2 = hashmap.get(k+1);
		        	if(arrayList_1.get(i)!=0){
			        	for(int j=0;j<arrayList_2.size();j++){
			        		if ((wyniki.get(k-1).get(i)+arrayList_2.get(j))!=1){ 
			        			if(wynik.contains(((arrayList_1.size()-1)-i)+((arrayList_2.size()-1)-j))) wynik.remove(new Integer(((arrayList_1.size()-1)-i)+((arrayList_2.size()-1)-j)));
			        			else wynik.add(((arrayList_1.size()-1)-i)+((arrayList_2.size()-1)-j));
				        	}
				        }
		        	}
		        }
	        	wyniki.put(k,wynik);
	        	System.out.println(hashmap);
	        	hashmap.put(hashmap.size()-k, wyniki.get(k));

	        	System.out.println(wyniki);
	        	System.out.println(hashmap);
	        	System.out.println(hashmap.size());
	        }
	        
	        System.out.println(wynik);
        	System.out.println(hashmap);
        	System.out.println(hashmap.size());
	}

}
