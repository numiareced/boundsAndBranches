public interface IATSPParser {
	
	public String getName();
	public String getType();
	public String getComment();
	public int getDimension();
	public String getEdgeWeightType();
	public String getEdgeWeightFormat();
	public double getEdgeWeight(int row, int col);
	public double[][] getCostMatrix();
}
