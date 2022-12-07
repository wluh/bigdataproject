echo "Creating Project Directory"
hdfs dfs -mkdir project

echo "Creating Raw Data Directory"
hdfs dfs -mkdir project/rawdata

hdfs dfs -put original_income.csv project/rawdata
echo "Loading Original SF Data into HDFS"
hdfs dfs -put original_sf.csv project/rawdata
echo "Loading Original Chicago Data into HDFS"
hdfs dfs -put original_chi.csv project/rawdata
echo "Loading Original LA Data into HDFS"
hdfs dfs -put original_la.csv project/rawdata