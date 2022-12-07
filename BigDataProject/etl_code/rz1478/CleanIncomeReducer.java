import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CleanIncomeReducer extends Reducer<Text, Text, Text, Text> {

	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		int medianIncome = 0;
		for (Text value : values) {
			if (medianIncome < Integer.parseInt(value.toString())) {
				medianIncome = Integer.parseInt(value.toString());
			}
		}
		context.write(key, new Text(Integer.toString(medianIncome)));
	}
}