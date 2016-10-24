public interface IATSPParser {
	
	public String getName();
	public String getType();
	public String getComment();
	public int getDimension();
	public String getEdgeWeightType();
	public String getEdgeWeightFormat();
	public int getEdgeWeight(double row, double col);
	public double[][] getCostMatrix();
}
