import java.util.HashSet;

public class Solver {

	double[][] distances;
	double bestCost;
	int[] bestPath;

	public Solver(double[][] distance) {
		distances = distance;
	}

	public int[] calculate() {
		HashSet<Integer> locationSet = new HashSet<Integer>(distances.length);
		for (int i = 0; i < distances.length; i++)
			locationSet.add(i);

		bestCost = findGreedyCost(0, locationSet, distances);

		int[] activeSet = new int[distances.length];
		for (int i = 0; i < activeSet.length; i++)
			activeSet[i] = i;

		Node root = new Node(null, 0, distances, activeSet, 0);
		traverse(root);

		return bestPath;
	}

	public double getCost() {
		return bestCost;
	}

	private double findGreedyCost(int i, HashSet<Integer> locationSet, double[][] distances) {
		locationSet.remove(i);
		double lowest = Double.MAX_VALUE;
		int closest = 0;
		for (int location : locationSet) {
			double cost = distances[i][location];
			if (cost < lowest) {
				lowest = cost;
				closest = location;
			}
		}

		if (locationSet.isEmpty())
			return distances[i][0];

		return lowest + findGreedyCost(closest, locationSet, distances);
	}

	private void traverse(Node parent) {
		if (Samoylov_Martirosyan_TSP.timer.getTime() >= Samoylov_Martirosyan_TSP.time) {
			bestCost = -1; 
			return;
		}
		Node[] children = parent.generateChildren();

		for (Node child : children) {
			if (child.isTerminal()) {
				double cost = child.getPathCost();
				if (cost < bestCost) {
					bestCost = cost;
					bestPath = child.getPath();
				}
			} else if (child.getLowerBound() <= bestCost) {
				traverse(child);
			}
		}
	}
}
