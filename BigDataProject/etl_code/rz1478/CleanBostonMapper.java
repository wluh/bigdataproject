import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanBostonMapper extends Mapper<Object, Text, Text, Text> {

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();

		Pattern pattern = Pattern.compile("\"(\\d+),(\\d+)\\.\\d+\"");
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			String salary = matcher.group(1) + matcher.group(2);

			try {
				String agency = line.split(",")[0];
				String role = line.split(",")[1];

				context.write(new Text(agency + ","), new Text(role + "," + salary));
			} catch (Exception ignored) {
				return;
			}
		}
	}
}