// NYC
// Median income in NYC
val nycCensusMedianDf = spark.sql("SELECT * FROM wsl300.cities WHERE city LIKE '%New York%'")
val nycCensusMedian : Double = nycCensusMedianDf.first.getString(1).toDouble

// Median salary of NYC government employees
val nycGovernMedianDf = spark.sql("SELECT PERCENTILE(salary, 0.5) AS median_income FROM wsl300.nyc")
val nycGovernMedian : Double = nycGovernMedianDf.first.getDouble(0)

// Comparing median government salary to median income
println("NYC: govern / median", (nycGovernMedian / nycCensusMedian) * 100)

// Highest paying NYC government agency
val highestPayingAgencyDf = spark.sql("SELECT agency, PERCENTILE(salary, 0.5) AS median_income FROM wsl300.nyc GROUP BY agency ORDER BY median_income DESC")
val nycAgencyMedian : Double = highestPayingAgencyDf.first.getDouble(1)

// Comparing to other values
println("NYC: agency / median", (nycAgencyMedian / nycCensusMedian) * 100)
println("NYC: agency / govern", (nycAgencyMedian / nycGovernMedian) * 100)


// BOSTON
// Median income in Boston
val bosCensusMedianDf = spark.sql("SELECT * FROM wsl300.cities WHERE city LIKE '%Boston%'")
val bosCensusMedian : Double = bosCensusMedianDf.first.getString(1).toDouble

// Median salary of Boston government employees
val bosGovernMedianDf = spark.sql("SELECT PERCENTILE(salary, 0.5) AS median_income FROM wsl300.boston")
val bosGovernMedian : Double = bosGovernMedianDf.first.getDouble(0)

// Compaing median government salary to median income
println("BOS: govern / median", (bosGovernMedian / bosCensusMedian) * 100)

// Highest paying Boston government agency
val highestPayingAgencyDf = spark.sql("SELECT agency, PERCENTILE(salary, 0.5) AS median_income FROM wsl300.boston GROUP BY agency ORDER BY median_income DESC")
val bosAgencyMedian : Double = highestPayingAgencyDf.first.getDouble(1)

// Comparing to other values
println("BOS: agency / median", (bosAgencyMedian / bosCensusMedian) * 100)
println("BOS: agency / govern", (bosAgencyMedian / bosGovernMedian) * 100)


// Washington DC
// Median income in DC
val dcCensusMedianDf = spark.sql("SELECT * FROM wsl300.cities WHERE city LIKE '%Washington%'")
val dcCensusMedian : Double = dcCensusMedianDf.collect()(2).getString(1).toDouble

// Median salary of DC government employees
val dcGovernMedianDf = spark.sql("SELECT PERCENTILE(salary, 0.5) AS median_income FROM wsl300.dc")
val dcGovernMedian : Double = bosGovernMedianDf.first.getDouble(0)

// Compaing median government salary to median income
println("DC: govern / median", (dcGovernMedian / dcCensusMedian) * 100)

// Highest paying DC government agency
val highestPayingAgencyDf = spark.sql("SELECT agency, PERCENTILE(salary, 0.5) AS median_income FROM wsl300.dc GROUP BY agency ORDER BY median_income DESC")
val dcAgencyMedian : Double = highestPayingAgencyDf.collect()(5).getDouble(1)

// Comparing to other values
println("DC: agency / median", (dcAgencyMedian / dcCensusMedian) * 100)
println("DC: agency / govern", (dcAgencyMedian / dcGovernMedian) * 100)
