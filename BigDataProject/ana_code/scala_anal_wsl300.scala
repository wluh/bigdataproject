// get the median from the census db
val chi_census_median_df = spark.sql("SELECT * FROM wsl300.cities WHERE city LIKE '%Chicago%'")
val chi_census_median = chi_census_median_df.first.getString(1).toDouble


// get the medians from the city db
val chi_dept_df = spark.sql("SELECT department AS Chi_Department, COUNT(*) as num_employees, PERCENTILE(FLOOR(CAST(salary AS DOUBLE)),0.5) AS Chi_median_dept FROM wsl300.cleaned_chi GROUP BY department ORDER BY Chi_median_dept")


val chi_dept_final = chi_dept_df.withColumn("ratio", $"Chi_median_dept" /chi_census_median)

chi_dept_final.show(10000)

//------------------------------------------

// get the median from the census db
val sf_census_median_df = spark.sql("SELECT * FROM wsl300.cities WHERE city LIKE '%San Francisco%'")
val sf_census_median = sf_census_median_df.first.getString(1).toDouble


// get the medians from the city db
val sf_dept_df = spark.sql("SELECT department AS SF_Department, COUNT(*) as num_employees, PERCENTILE(FLOOR(CAST(salary AS DOUBLE)),0.5) AS SF_median_dept FROM wsl300.cleaned_sf GROUP BY department ORDER BY SF_median_dept")


val sf_dept_final = sf_dept_df.withColumn("ratio", $"SF_median_dept" /sf_census_median)

sf_dept_final.show(10000)


//------------------------------------------


// get the median from the census db
val la_census_median_df = spark.sql("SELECT * FROM wsl300.cities WHERE city LIKE '%Los Angeles%'")
val la_census_median = la_census_median_df.first.getString(1).toDouble


// get the medians from the city db
val la_dept_df = spark.sql("SELECT department AS LA_Department, COUNT(*) as num_employees, PERCENTILE(FLOOR(CAST(salary AS DOUBLE)),0.5) AS LA_median_dept FROM wsl300.cleaned_la GROUP BY department ORDER BY LA_median_dept")


val la_dept_final = la_dept_df.withColumn("ratio", $"LA_median_dept" /la_census_median)


la_dept_final.show(10000)
