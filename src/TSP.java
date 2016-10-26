public class TSP {

	public static Timer timer;
	public static long time;

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("Bad args!!!");
			System.exit(1);
		}

		try {
			time = Long.parseLong(args[1]) * 1000;
		} catch (NumberFormatException e) {
			System.out.println("Wrong time format");
		}

		ATSPParser atspParser = new ATSPParser(args[0]);
		double[][] distances = atspParser.getCostMatrix();

		Solver solver = new Solver(distances);
		timer = new Timer();
		timer.start();
		int[] path = solver.calculate();

		for (int node : path) {
			System.out.print((node + 1) + " -> ");
		}
		System.out.println(path[0] + 1);
		System.out.println("best cost is: " + solver.getCost());
	}
}
