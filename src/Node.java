public class Node {

	public Node parent;
	private double parentCost;

	private double[][] distances;
	private int[] activeSet;

	public int index;

	public Node(Node parent, double parentCost, double[][] distances, int[] activeSet, int index) {
		this.parent = parent;
		this.parentCost = parentCost;
		this.distances = distances;
		this.activeSet = activeSet;
		this.index = index;
	}

	public boolean isTerminal() {
		return activeSet.length == 1;
	}

	public Node[] generateChildren() {
		Node[] children = new Node[activeSet.length - 1];

		int[] newSet = new int[activeSet.length - 1];
		int i = 0;
		for (int location : activeSet) {
			if (location == index)
				continue;

			newSet[i] = location;
			i++;
		}

		for (int j = 0; j < children.length; j++)
			children[j] = new Node(this, distances[index][newSet[j]], distances, newSet, newSet[j]);

		return children;
	}

	public int[] getPath() {
		int depth = distances.length - activeSet.length + 1;
		int[] path = new int[depth];
		getPathIndex(path, depth - 1);
		return path;
	}

	public void getPathIndex(int[] path, int i) {
		path[i] = index;
		if (parent != null)
			parent.getPathIndex(path, i - 1);
	}

	public double getLowerBound() {
		double value = 0;

		if (activeSet.length == 2)
			return getPathCost() + distances[activeSet[0]][activeSet[1]];

		for (int row : activeSet) {
			double low1 = Double.MAX_VALUE;
			double low2 = Double.MAX_VALUE;

			for (int column : activeSet) {
				if (column == row)
					continue;

				double cost = distances[row][column];
				if (cost < low1) {
					low2 = low1;
					low1 = cost;
				} else if (cost < low2) {
					low2 = cost;
				}
			}

			value += low1 + low2;
		}

		return getParentCost() + value / 2;
	}

	public double getPathCost() {
		return distances[index][0] + getParentCost();
	}

	public double getParentCost() {
		if (parent == null)
			return 0;

		return parentCost + parent.getParentCost();
	}
}
