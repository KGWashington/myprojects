
public class Main {

	public static void main(String[] args) {
		
		int [] array1 = {2,6,4,2,4,65,85,25,48,63,96};
		int [] array2 = {20,79,103,59};
		int temp = 0;
		int max = 0;
		int maxA1 = 0;
		int maxA2 = 0;
		
		for(int i = 0; i <= array1.length-1; i++ ) {
			for(int j = 0 ; j <= array2.length-1; j++) {
				
				temp = array1[i] * array2[j];
				
				if(temp > max) {
					max = temp;
					maxA1 = array1[i];
					maxA2 = array2[j];
				}
				//System.out.println("array 1= "+array1[i]+ " array 2 = "+array2[j] +" temp= "+ temp + " max= "+ max+ " A1= "+ maxA1 + " A2= "+ maxA2);
			}
		}
		
		System.out.print("The maximum Product is : "+ max+ "\n" + "The number from array 1 is: "+ maxA1 + "\n"+"The number from array 2 is: "+ maxA2);
	}

}
