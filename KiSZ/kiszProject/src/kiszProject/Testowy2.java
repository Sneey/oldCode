package kiszProject;

import java.util.ArrayList;
import java.util.HashMap;

public class Testowy2 {

	public static void main(String[] args) {
		ArrayList<Integer> do_przesuniecia = new ArrayList<Integer>();
		do_przesuniecia.add(0);
		do_przesuniecia.add(0);
		do_przesuniecia.add(0);
		do_przesuniecia.add(0);
		do_przesuniecia.add(0);
		do_przesuniecia.add(1);
		do_przesuniecia.add(0);

		System.out.println(do_przesuniecia);
		int count = 0;
		for(int i=0; i<4; i++){
			if(do_przesuniecia.get(0)==0){
				do_przesuniecia.remove(0);
				do_przesuniecia.add(0);
			} else {
				do_przesuniecia.remove(0);
				do_przesuniecia.add(1);
			}
			count++;
		}

		System.out.println(do_przesuniecia);
		for(int i=0; i<count;i++){
			if(do_przesuniecia.get(do_przesuniecia.size()-1)==0){
				do_przesuniecia.remove(do_przesuniecia.size()-1);
				do_przesuniecia.add(0,0);
			} else {
				do_przesuniecia.remove(do_przesuniecia.size()-1);
				do_przesuniecia.add(0,1);
			}
		}

		System.out.println(do_przesuniecia);
		
	}
}
