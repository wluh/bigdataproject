import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CleanSFMapper extends Mapper<LongWritable, Text, Text, Text> {

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        line = line.replaceAll(",(?!(?:[^\"]*\"[^\"]*\")*[^\"]*$)", " ");
        String []arr = line.split(",");
        String dept_name = arr[5];
        String job_title = arr[11];
        String total_comp = arr[16];
        dept_name = dept_name.replaceAll("^\"|\"$", "");
        job_title = job_title.replaceAll("^\"|\"$", "");
        total_comp = total_comp.replaceAll("^\"|\"$", "");

        if (total_comp.equals("")) {
            total_comp = String.valueOf(Integer.parseInt(arr[8]) * 40 * 52);
        }

        if (!total_comp.equals("Total Salary")) {
            context.write(new Text(dept_name + "," + job_title + "," + total_comp), new Text(""));
        }
    }

}
