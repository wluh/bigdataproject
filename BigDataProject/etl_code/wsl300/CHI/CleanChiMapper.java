import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CleanChiMapper extends Mapper<LongWritable, Text, Text, Text> {

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        line = line.replaceAll(",(?!(?:[^\"]*\"[^\"]*\")*[^\"]*$)", " ");
        String []arr = line.split(",");
        String dept_name = arr[2];
        String job_title = arr[1];
        String total_comp = arr[6];
        dept_name = dept_name.replaceAll("^\"|\"$", "");
        job_title = job_title.replaceAll("^\"|\"$", "");
        total_comp = total_comp.replaceAll("^\"|\"$", "");

	if (total_comp.equals("") && !arr[7].equals("") && !arr[5].equals("") ) {
            total_comp = String.valueOf(Double.parseDouble(arr[7]) * Double.parseDouble(arr[5]) * 52);
        }

        context.write(new Text(dept_name + "," + job_title + "," + total_comp), new Text(""));
    }

}
