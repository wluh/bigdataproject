-- Creating cities table
CREATE EXTERNAL TABLE cities(city STRING, income STRING)
COMMENT 'Median Income By Metro Area'
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
STORED AS TEXTFILE
LOCATION '/user/wsl300/project/income';


-- Creating NYC table
CREATE EXTERNAL TABLE nyc(agency STRING, role STRING, salary BIGINT)
COMMENT 'NYC Payroll Data'
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
STORED AS TEXTFILE
LOCATION '/user/wsl300/project/nyc';

-- Creating Boston table
CREATE EXTERNAL TABLE boston(agency STRING, role STRING, salary BIGINT)
COMMENT 'Boston Payroll Data'
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
STORED AS TEXTFILE
LOCATION '/user/wsl300/project/boston';

-- Creating DC  table
CREATE EXTERNAL TABLE dc(agency STRING, role STRING, salary BIGINT)
COMMENT 'DC Payroll Data'
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
STORED AS TEXTFILE
LOCATION '/user/wsl300/project/dc';