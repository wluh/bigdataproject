import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanNYCMapper extends Mapper<Object, Text, Text, Text> {

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] line = value.toString().split(",");
		String role;
		String agency;
		String payBasis;
		double hours;

		try {
			role = line[8];
			agency = line[2];
			payBasis = line[11];
			hours = Double.parseDouble(line[12]);
		} catch (Exception ignored) {
			return;
		}

		int salary = 0;
		if (payBasis.equalsIgnoreCase("per Annum")) {
			salary = (int) Double.parseDouble(line[10]);
		} else if (payBasis.equalsIgnoreCase("per Day")) {
			hours /= 8;
			salary = (int) (hours * Double.parseDouble(line[10]));
		} else if (payBasis.equalsIgnoreCase("per Hour")) {
			salary = (int) (hours * Double.parseDouble(line[10]));
		}

		if (salary > 0) {
			context.write(new Text(agency + ","), new Text(role + "," + Integer.toString(salary)));
		}
	}
}