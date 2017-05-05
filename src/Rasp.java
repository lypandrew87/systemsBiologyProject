import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class Rasp {
	
	 static double Tym; 
	 static double Gop; 
	 static double Haa; 
	 static double Pri;
	static double hiThresh; 
	static double loThresh; 
	static double foodNeeded; 
	static double waterNeeded; 
	static PrintWriter writer; 
	static double[] temps = new double[360];
	static double[] foodAvail = new double[360];
	static double[] waterAvail = new double[360];;


	
	
	public Rasp(double Tym, double Gop, double Haa, double Pri ){
		
		this.Tym = Tym; 
		this.Gop = Gop; 
		this.Haa= Haa; 
		this.Pri= Pri;
		
		populateTemps(); 
		
	}
	
	private static void populateTemps()  {
		
		int counterTemp = 0; 
		int counterFood = 0; 
		int counterWater = 0 ; 
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("/home/amlyp/Desktop/rasp1.dat"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int [] tall = new int [100];
		
		while(scanner.hasNext()){

		if(counterTemp < 360){
			
			temps[counterTemp] = scanner.nextDouble(); 
			counterTemp++; 	
		}else if(counterFood <12){
			
			double current = scanner.nextDouble(); 
			
			for(int i = 0; i< 30; i++){
				
				
				foodAvail[counterFood *30 + i] = current; 

			}
			

			counterFood++; 
			
		}else if (counterWater < 12){
			
			double current = scanner.nextDouble(); 
			
			for(int i = 0; i<30; i++){
				
				waterAvail[counterWater *30 + i] = current; 

			}
			
			

			counterWater++; 
		}
		
		}
		

		for(int i = 0; i>360; i++){
			System.out.println(temps[i]); 
		}
		   
		}



	

	public static void getDoubles(){
		
		  Scanner s = new Scanner(System.in);

	    System.out.println("Number for Tym: ");
	    
	    Tym = s.nextDouble();
	    System.out.println("Number for Gop: ");

	    Gop = s.nextDouble();
	    System.out.println("Number for Haa: ");

	    Haa = s.nextDouble();
	    System.out.println("Number for Pri: ");

	    Pri = s.nextDouble();

		
		
	}
	
	public static void makeStep(double theta) {

		hiThresh = 20 * Tym + 10;
		loThresh = 20 * Tym - 10;

		if (theta > hiThresh) {

			Haa = 1.0 - Gop * (theta - hiThresh) / (50 - hiThresh);

		} else if (theta < loThresh) {
			Haa = 1.0 - Gop * (loThresh - theta) / (loThresh + 10);
		}
		foodNeeded = Haa * Math.abs(theta - 30) / 40.0;
		if (theta < loThresh) {

			foodNeeded += 0.02 * (loThresh - theta);
		}
		waterNeeded = Pri * foodNeeded;

		if (theta > hiThresh) {
			waterNeeded += 0.03 * (theta - hiThresh);

		}

	}
	
	
	
	
	public static double food(){
		return foodNeeded; 
		
	}

	public static double water(){
		
		return waterNeeded; 
	}


	}


