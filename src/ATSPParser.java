import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ATSPParser implements IATSPParser {
	
	private String name;
	private String type;
	private String comment;
	private int dimension;
	private String edgeWeightType;
	private String edgeWeightFormat;
	private double[][] costMatrix;
	//private short[][] costMatrix;
	
	private int separator;
	
	public ATSPParser(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String temp = br.readLine();
			String regexp = "^NAME:\\s+(.*)$";
			name = find(regexp, temp);
			temp = br.readLine();
			regexp = "^TYPE:\\s+(.*)$";
			type = find(regexp, temp);
			temp = br.readLine();
			regexp = "^COMMENT:\\s+(.*)$";
			comment = find(regexp, temp);
			temp = br.readLine();
			regexp = "^DIMENSION:\\s+(.*)$";
			dimension = Integer.parseInt(find(regexp, temp));
			temp = br.readLine();
			regexp = "^EDGE_WEIGHT_TYPE:\\s+(.*)$";
			edgeWeightType = find(regexp, temp);
			temp = br.readLine();
			regexp = "^EDGE_WEIGHT_FORMAT:\\s+(.*)$";
			edgeWeightFormat = find(regexp, temp);
			
			int i = 1;
			int j = 1;
			costMatrix = new double[dimension + 1][dimension + 1];
			br.readLine();
			temp = br.readLine();
			Pattern pattern = Pattern.compile("(\\d+)\\s*");
			while (temp != null) {
				if (j >= dimension + 1) {
					i++;
					j = 1;
				}
				
				Matcher matcher = pattern.matcher(temp);
				while (matcher.find()) {
					int edgeWeight = Integer.parseInt(matcher.group(1));
					if (i == 1 && j == 1) {
						separator = edgeWeight;
					}
					if (edgeWeight == separator) edgeWeight = 0;
					costMatrix[i][j] = (short) edgeWeight;
					j++;
				}
				temp = br.readLine();
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String find(String regexp, String temp) {
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(temp);
		matcher.find();
		return matcher.group(1);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public int getDimension() {
		return dimension;
	}

	@Override
	public String getEdgeWeightType() {
		return edgeWeightType;
	}

	@Override
	public String getEdgeWeightFormat() {
		return edgeWeightFormat;
	}

/*	@Override
	public int getEdgeWeight(double row, double col) {
		return costMatrix[row][col];
	}*/

	@Override
	public double[][] getCostMatrix() {
		return costMatrix;
	}

	@Override
	public int getEdgeWeight(double row, double col) {
		// TODO Auto-generated method stub
		return 0;
	}
}
