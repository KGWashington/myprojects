
public class Main {

	public static void main(String[] args) {
		
		int [] array = {98,58,2,106,35,4,41,10,5};
		int temp = 0;
		int max = 0;
		int maxi = 0;
		int maxj = 0;
		
		for (int i = 0; i <= array.length -2; i ++) {
			for(int j = i +1; j <= array.length -1; j++) {
				
				temp = array[i] * array[j];
				
				if(temp > max) {
					max = temp;
					maxi = array[i];
					maxj = array[j];
				}
			}
		}
		
		System.out.print("The maximum Product is : "+ max+ "\n" + "The numbers for this character are : "+ maxi + " & "+ maxj);
	}

}
