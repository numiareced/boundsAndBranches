import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

			int i = 0;
			int j = 0;
			costMatrix = new double[dimension][dimension];
			br.readLine();
			temp = br.readLine();
			Pattern pattern = Pattern.compile("(\\d+)\\s*");
			while (temp != null) {
				Matcher matcher = pattern.matcher(temp);
				while (matcher.find()) {
					if (j >= dimension) {
						i++;
						j = 0;
					}
					costMatrix[i][j] = Double.parseDouble(matcher.group(1));
					j++;
				}
				temp = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("File reading error");
		} catch (Exception e) {
			System.out.println("Something went wrong");
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

	@Override
	public double getEdgeWeight(int row, int col) {
		return costMatrix[row][col];
	}

	@Override
	public double[][] getCostMatrix() {
		return costMatrix;
	}
}
