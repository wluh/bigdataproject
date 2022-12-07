import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanDCMapper extends Mapper<Object, Text, Text, Text> {

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] line = value.toString().split(",");

		try {
			String agency = line[0];
			String role = line[4];
			int salary = (int) Double.parseDouble(line[7]);

			if (salary > 25100) {
				context.write(new Text(agency + ","), new Text(role + "," + Integer.toString(salary)));
			}
		} catch (Exception ignored) {
			return;
		}
	}
}