-- Create LA table in Hive

drop table if exists cleaned_la;
create table cleaned_la (department STRING, position STRING, salary 
STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;
load data inpath 
'hdfs://horton.hpc.nyu.edu:8020/user/wsl300/project/la/part-m-00000' 
overwrite into table cleaned_la;
select * from cleaned_la limit 5;
SELECT PERCENTILE(FLOOR(CAST(salary AS DOUBLE)), 0.5) from cleaned_la;


-- Create SF table in Hive

drop table if exists cleaned_sf;
create table cleaned_sf (department STRING, position STRING, salary 
STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;
load data inpath 
'hdfs://horton.hpc.nyu.edu:8020/user/wsl300/project/sf/part-m-00000' 
overwrite into table cleaned_sf;
select * from cleaned_sf limit 5;
SELECT PERCENTILE(FLOOR(CAST(salary AS DOUBLE)), 0.5) from cleaned_sf;


-- Create Chicago table in Hive

drop table if exists cleaned_chi;
create table cleaned_chi (department STRING, position STRING, salary 
STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;
load data inpath 
'hdfs://horton.hpc.nyu.edu:8020/user/wsl300/project/chi/part-m-00000' 
overwrite into table cleaned_chi;
select * from cleaned_chi limit 5;
SELECT PERCENTILE(FLOOR(CAST(salary AS DOUBLE)), 0.5) from cleaned_chi;
