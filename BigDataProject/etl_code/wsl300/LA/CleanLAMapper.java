import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CleanLAMapper extends Mapper<LongWritable, Text, Text, Text> {

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String []arr = line.split(",");
        String dept_name = arr[3];
        String job_title = arr[5];
        String total_comp = arr[13];
        dept_name = dept_name.replaceAll("^\"|\"$", "");
        job_title = job_title.replaceAll("^\"|\"$", "");
        total_comp = total_comp.replaceAll("^\"|\"$", "");
        if (!total_comp.equals("TOTAL_PAY")) {
            int total_comp_num = Integer.parseInt(Integer.toString(Math.round(Float.parseFloat(total_comp))));
            total_comp = Integer.toString(total_comp_num);
            context.write(new Text(dept_name + "," + job_title + "," + total_comp), new Text(""));
        }
    }

}
