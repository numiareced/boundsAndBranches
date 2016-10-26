public class TSP {
	
	public static Timer timer;
	public static long time;
	
	public static void main(String[] args) {

		if (args.length == 2) {
			time = Long.parseLong(args[1]);
		} else {
			System.out.println("Bad args!!!");
			System.exit(1);
		}
		
		ATSPParser atspParser = new ATSPParser(args[0]);
		double[][] distances = atspParser.getCostMatrix();
		timer = new Timer();
		Solver solver = new Solver(distances);
		timer.start();
		int[] path = solver.calculate();
		timer.stop();
		for (int node: path){
			System.out.print(node + " -> " );
		}
		System.out.println(path[0]);
		
		System.out.println("best cost is: " + solver.getCost());
	}
}
