import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanIncomeMapper extends Mapper<Object, Text, Text, Text> {

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] line = value.toString().split(",");

		String metroArea = line[1].replace("\"", "");
		try {
			int medianIncome = Integer.parseInt(line[47].replace("\"", ""));
			context.write(new Text(metroArea + ","), new Text(Integer.toString(medianIncome)));
		} catch (Exception ignored) {
			return;
		}
	}
}