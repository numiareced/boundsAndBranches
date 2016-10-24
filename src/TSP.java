import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class TSP {
	public static void main(String[] args) {
		File file;
		int time = 0; 
		if(args.length > 0) {
			file = new File(args[0]);
			time = Integer.parseInt(args[1]);
		}
		else {
			System.out.print("no file!!!");
			//try catch? :)
		}
		
		ATSPParser atspParser = new ATSPParser(args[0]);
		double[][] distances = atspParser.getCostMatrix();
		Timer timer = new Timer();
		Solver solver = new Solver(distances);
		timer.start();
		int[] path = solver.calculate();
		timer.stop();
		for (int node: path){
			System.out.println(node + " + " );
		}
		
		//path is ok, but cost is wrong.. mb we should calculate it manually..
		System.out.println("best cost is:" + solver.getCost());
	}
}
