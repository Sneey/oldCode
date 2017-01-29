package kiszProject;

import java.awt.List;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
	
		ArrayList<Integer> lista = new ArrayList<Integer>();
		//ArrayList<List> lista2 = new ArrayList<List>();
 
		String wielomian = new String();
        wielomian = "1011";
        String ciagPoczatkowy = "100";
        
        //Wrzucenie do ArrayListy znaków ciąguPoczątkowego
        for(int i=0;i<ciagPoczatkowy.length();i++){
        	lista.add(Character.getNumericValue((ciagPoczatkowy.charAt(i))));
        }
        
        // sj+3 = sj + sj+1 (str22-Mochnacki)
        for(int i=0;i<30;i++){
        	if((lista.get(i)+lista.get(i+1))==1)
        	lista.add(1);
        	else
        	lista.add(0);
        }
        
        System.out.println(wielomian.charAt(2));
        System.out.println(lista);
        for (int i=0;i<8;i++)
        {
        	System.out.println(lista.subList(i, i+3));
        	//lista2.add((List) lista.subList(i, i+3));
        }
        

	}

}
