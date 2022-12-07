# Remove all previous cleaning runs
echo "Clearing previous cleaning attempts from HDFS"
hdfs dfs -rm -r project/la
hdfs dfs -rm -r project/sf
hdfs dfs -rm -r project/chi

echo "Removing previous cleaning attempts from local (.class, .jar)"
# Remove class and jar files from previous runs
rm LA/*.class
rm LA/*.jar
rm SF/*.class
rm SF/*.jar
rm CHI/*.class
rm CHI/*.jar

echo "Creating folders required for cleaning"
# Setup the folder for cleaning
hdfs dfs -mkdir project

# Process and clean LA Data
echo "Running MapReduce for LA data"
cd LA
javac -classpath `yarn classpath` -d . CleanLAMapper.java
javac -classpath `yarn classpath` -d . CleanLAReducer.java
javac -classpath `yarn classpath`:. -d . CleanLA.java
jar -cvf CleanLA.jar *.class
hadoop jar CleanLA.jar CleanLA project/rawdata/original_la.csv /user/wsl300/project/la/
cd ..

# Process and clean SF data
echo "Running MapReduce for SF data"
cd SF
javac -classpath `yarn classpath` -d . CleanSFMapper.java
javac -classpath `yarn classpath` -d . CleanSFReducer.java
javac -classpath `yarn classpath`:. -d . CleanSF.java
jar -cvf CleanSF.jar *.class
hadoop jar CleanSF.jar CleanSF project/rawdata/original_sf.csv /user/wsl300/project/sf/
cd ..

# Process and clean Chicago data
echo "Running MapReduce for Chicago data"
cd CHI
javac -classpath `yarn classpath` -d . CleanChiMapper.java
javac -classpath `yarn classpath` -d . CleanChiReducer.java
javac -classpath `yarn classpath`:. -d . CleanChi.java
jar -cvf CleanChi.jar *.class
hadoop jar CleanChi.jar CleanChi project/rawdata/original_chi.csv /user/wsl300/project/chi/
cd ..
