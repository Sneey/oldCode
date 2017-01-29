import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		HashMap<Integer, String> dictionary = new HashMap<Integer, String>(256);
		
		//Wpisanie pierwszych podstawowych 9 wyraz�w
		dictionary.put(0, "00000000");
		dictionary.put(1, "00000001");
		dictionary.put(2, "00000010");
		dictionary.put(3, "00000100");
		dictionary.put(4, "00001000");
		dictionary.put(5, "00010000");
		dictionary.put(6, "00100000");
		dictionary.put(7, "01000000");
		dictionary.put(8, "10000000");
		
		//wygenerowanie wszystkich s��w: S(j+8) = S(j) + S(j+2) + S(j+3) + S(j + 4)
		for(int j = 1; j < 248; j++){
			String word = addStrings(dictionary.get(j), dictionary.get(j + 2));
			word = addStrings(word, dictionary.get(j + 3));
			word = addStrings(word, dictionary.get(j + 4));
			dictionary.put((j + 8), word);
		}
		
		//Wypisanie s��w
		for(int i = 0; i < 256; i ++){
			System.out.println("S�owo " + i + ": " + dictionary.get(i));			
		}
		
		//Sprawdzenie unikalno�ci s��w
		/*HashSet<String> set = new HashSet<String>(dictionary.values());
		for(String word : set){
			int i = Collections.frequency(dictionary.values(), word);
			System.out.println("S�owo: " + word + " wyst�puje " + i + " razy.");			
		} */
		
		//Wyznaczanie warstw cyklotomicznych
		HashMap<Integer, ArrayList<Integer>> layers = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int i = 1;
		while(temp.size() < 254){
			boolean done;
			int factor = i * 2;
			ArrayList<Integer> indexes = new ArrayList<Integer>();
			if(!temp.contains(i)){
				indexes.add(i);
				temp.add(i);
				done = false;
			} else{
				done = true;
				i = i + 2;
			}
			while(!done){
				if(factor < 255){
					if(!indexes.contains(factor) && !temp.contains(factor)){
						indexes.add(factor);
						temp.add(factor);
						factor *= 2;
					}else {
						done = true;
						layers.put(i, indexes);
						i = i + 2;
					}
				} else {
					factor -= 255;
				}
			}
		}
		
		//Wy�wietlenie warstw cyklotomicznych
		SortedSet<Integer> sortedLayers = new TreeSet<Integer>(layers.keySet());
		for (Integer key : sortedLayers) { 
		   System.out.println(layers.get(key).toString());
		}
		
		//Wyliczenie wielomian�w minimalnych
		HashMap<Integer, Integer> minimalPoly = new HashMap<Integer,Integer>();
		HashMap<Integer, Integer> poly1 = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> poly2 = new HashMap<Integer, Integer>();
		for(int k = 8; k > 1; k--){
			poly1.put(k, 0);
			poly2.put(k, 0);
		}
		poly1.put(1, -1);
		poly1.put(0, layers.get(1).get(0));
		poly2.put(1, -1);
		poly2.put(0, layers.get(1).get(1));
		minimalPoly = polyAlphaMultiply(poly1, poly2, dictionary);
		
		System.out.println("Result - pierwsze dwa\t" + minimalPoly.toString());
		
		for(int j = 2; j < layers.get(1).size(); j++){
			HashMap<Integer, Integer> poly = new HashMap<Integer, Integer>();
			for(int k = 8; k > 1; k--){
				poly.put(k, 0);
				poly.put(k, 0);
			}
			poly.put(1, -1);
			poly.put(0, layers.get(1).get(j));
			minimalPoly = polyAlphaMultiply(minimalPoly, poly, dictionary);
			System.out.println("Result - j=" + j + "\t" + minimalPoly.toString());
		}
		
		System.out.println(minimalPoly.toString());
	} 
	
	private static HashMap<Integer,Integer> polyAlphaMultiply(HashMap<Integer,Integer> poly1, HashMap<Integer, Integer> poly2, 
			HashMap<Integer, String> dictionary){
		HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
		
		for(int i = 8; i >-1; i--){
			if(poly1.get(i) != 0){
				int tmp = 0;
				for(int j = 8; j > -1; j--){
					if(poly2.get(j) != 0){
						if(poly1.get(i) == -1 && poly1.get(j) == -1) tmp = -1; 
						else if (poly1.get(i) == -1) tmp = poly2.get(j);
						else if (poly2.get(j) == -1) tmp = poly1.get(i);
						else tmp = poly1.get(i) + poly2.get(j);
						//if(tmp != -1) tmp -= 1;
						if(tmp > 254) tmp -= 254;
						
						int index = i + j;
						if(tmp != -1){
							if(result.containsKey(index)){
								if(result.get(index) == tmp) result.remove(index);
								else{
									String tmpString = addStrings(dictionary.get(result.get(index)), dictionary.get(tmp));
									int indexOf = getKeyByValue(dictionary, tmpString);
									if(indexOf == 1) indexOf = -1;
									result.put(index, indexOf);
								}
							}else{
								result.put(index, tmp);
							}
						}else{
							result.put(index, tmp);
						}
					}
				}
			}
		}
		
		for(int i = 0; i < 9; i++){
			if(!result.containsKey(i)) result.put(i, 0);
		}
		
		return result;
	}
	
	public static <T, E> T getKeyByValue(HashMap<T, E> map, E value) {
	    for (HashMap.Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	private static String addStrings(String word1, String word2){
		String result = "";
		
		for(int i = 0; i < 8; i++){
			if(word1.charAt(i) == word2.charAt(i)) result += "0";
			else result += "1";
		}
		
		return result;
	}
}