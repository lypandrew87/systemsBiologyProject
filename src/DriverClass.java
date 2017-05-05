import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class DriverClass {

	static double Tym;
	static double Gop;
	static double Haa;
	static double Pri;
	static double hiThresh;
	static double loThresh;
	static double foodNeeded;
	static double waterNeeded;


	public static void main(String[] args) throws IOException {

		//getDoubles();

		Rasp r = new Rasp(.5, .9, .5, .5);

		System.out.println(fitness(r));

	}

	

	public static double fitness(Rasp r) {

		double fit = 0;
		for (int i = 0; i < 360; i++) {

			r.makeStep(r.temps[i]);
			fit -= .03;

			
			if (r.food() < r.foodAvail[i]) {
				fit += 0.25;

			}
			if (r.water() < r.waterAvail[i]) {
				fit += 0.25;

			}

		}
		return fit;
	}

	

	public static void getDoubles() {

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

	public static double food() {
		return foodNeeded;

	}

	public static double water() {

		return waterNeeded;
	}

}
