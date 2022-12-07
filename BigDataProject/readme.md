# Competitiveness of Local Government Pay

The purpose of the application is to determine which major city offers the most competitive while taking into account the area's median income. It hopes to also reveal which departments within city governments are outliers (either they pay much higher/lower than expected) and discover whether local government pay inline with local rates. 


## Directories and Files
All project files:
/home/wsl300/project

Input data for Wes:
/home/wsl300/project/rawdata

Input data for Roy:
/home/rz1478/rawdata
```
├── ana_code
│   ├── scala_anal_rz1478.scala
│   └── scala_anal_wsl300.scala
├── data_ingest
│   └── import_to_hdfs_wsl300.sh
├── etl_code
│   ├── rz1478
│   │   ├── CleanNYC (driver, mapper reducer)
│   │   ├── CleanBoston (driver, mapper reducer)
│   │   ├── CleanDC (driver, mapper reducer)
│   │   ├── CleanIncome (driver, mapper reducer)
│   │   ├── runMapReduce.sh
│   │   └── import_to_hive_rz1478.hql

│   └── wsl300
│       ├── import_to_hive.hql
│       ├── run_mapreduce.sh
│       ├── CHI (folder contains driver, mapper, reducer)
│       ├── LA (folder contains driver, mapper, reducer)
│       └── SF (folder contains driver, mapper, reducer)
└── profiling_code
    └── rz1478
        ├── exploring_boston.hql
        ├── exploring_dc.hql
        └── exploring_nyc.hql
    └── wsl300
        ├── profiling.scala
```




**data_ingest**
- **import_to_hdfs_wsl300.sh**: this script imports data to hdfs from the local source

**etl_code**
- Roy
    - import_to_hive_rz1478.hql: This file creates Hive tables with the cleaned data in HDFS 
    - Mapper, Reducer, and Driver classes for the MapReduce jobs for NYC, Boston, DC, and the census income data.
    - **runMapReduce.sh**: this script runs the MapReduce jobs and puts the original data and cleaned data into HDFS
- Wes
    - **CHI**: Contains MapReduce code for Chicago
    - **LA**: Contains MapReduce code for LA
    - **SF**: Contains MapReduce code for SF
    - **run_mapreduce.sh**: runs mapreduce for all 3 cities once data has been imported through in ingestion
    - **import_to_hive.hql**: brings cleaned data from hdfs to Hive.

**profiling_code**
- **wes_prof/profiling.scala**: this file is to be run through the scala shell to get profiling results
- HQL files in **roy_prof**: these files can be run on Hive to explore the datasets for each of the three cities 

**ana_code**
- **scala_anal_wsl300.scala**: this file contains the scala code that provides the final analytic for SF, LA and Chicago
- **scala_anal_rz1478.scala**: this file contains the scala code that provides the final analytic for DC, NYC, and Boston

## Running the Code

### Data Ingestion - Wes

**RUN THIS CODE FIRST**

Run the import_to_hdfs.sh script. This will import the data for SF, LA, and Chicago to HDFS.

### ETL - Wes

Run the run_mapreduce.sh script after running the data ingestion script. This will run all three mapreduce tasks.

Then, log into hive via beeline and run import_to_hive code.hql using the following syntax:

```
!run /path/to/import_to_hive.hql
```

### Data Ingestion and ETL - Roy

**RUN THIS CODE SECOND**

For each of the NYC, Boston, DC and census datasets, run the runMapReduce.sh script. This will move the dataset from Peel to HDFS, and then run the MapReduce job for that dataset.

In total, this script should be run four times. To use runMapReduce.sh:
```
./runMapReduce input1 input2 input3
```
runMapReduce.sh takes 3 arguments:
input1: name of driver class
input2: input data
input3: output location 

The output location should be in the following format to match the other cities:
```
/user/*netID*/project/*cityname*/
```

To create Hive tables with the cleaned data in HDFS, log into hive via beeline, and then run the following:
```
!run /path/to/import_to_hive_rz1478.hql
```


### Data Profiling - Wes

Launch the Scala Shell first.

Then, run the profiling.scala code using the following syntax INSIDE the Scala Shell:

```
:load /path/to/profiling.scala
```

### Data Profiling - Roy
While logged into Hive, run the following code:
```
!run /path/to/exploring_nyc.hql
!run /path/to/exploring_boston.hql
!run /path/to/exploring_dc.hql
```
Each of these will return the number of unique agencies, number of workers in each agency, and the median salary of each agency for each of the three cities.

### Analysis

For analysis on SF, LA, and Chicago, run scala_anal.scala from the Spark Shell using the following syntax INSIDE the Scala Shell:
```
:load /path/to/scala_anal.scala
```

For analysis on NYC, Boston, and DC, run the government_analysis_rz1478.scala code from the Spark Shell using the following syntax INSIDE the Scala Shell:
```
:load /path/to/government_analysis_rz1478.scala
```