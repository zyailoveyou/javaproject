package testmain;

import java.util.ArrayList;
import java.util.Iterator;

public class test {
	
	public static void main(String[] args) {
		
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		
			arrayList.add(1);
					
		Iterator<Integer> iterator = arrayList.iterator();
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			
			
			
		}
		
	}

}
