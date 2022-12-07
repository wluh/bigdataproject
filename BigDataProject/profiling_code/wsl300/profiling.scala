// Los Angeles

val la_median = spark.sql("SELECT PERCENTILE(salary, 0.5) AS median_income FROM wsl300.cleaned_la")
println("la median salary")
la_median.show(1000, false)

val la_depts = spark.sql("SELECT DISTINCT department FROM wsl300.cleaned_la")
println("la departments")
la_depts.show(1000, false)

val la_num_employees = spark.sql("SELECT department, COUNT(*) FROM wsl300.cleaned_la GROUP BY department")
println("la employees per agency")
la_num_employees.show(1000, false)

val la_dept_median = spark.sql("SELECT department, PERCENTILE(salary, 0.5) AS median_income FROM wsl300.cleaned_la GROUP BY department ORDER BY median_income DESC")
println("la median salary by agency")
la_dept_median.show(1000, false)

// Chicago

val chi_median = spark.sql("SELECT PERCENTILE(salary, 0.5) AS median_income FROM wsl300.cleaned_chi")
println("chi median salary")
chi_median.show(1000, false)

val chi_depts = spark.sql("SELECT DISTINCT department FROM wsl300.cleaned_chi")
println("chi departments")
chi_depts.show(1000, false)

val chi_num_employees = spark.sql("SELECT department, COUNT(*) FROM wsl300.cleaned_chi GROUP BY department")
println("chi employees per agency")
chi_num_employees.show(1000, false)

val chi_dept_median = spark.sql("SELECT department, PERCENTILE(salary, 0.5) AS median_income FROM wsl300.cleaned_chi GROUP BY department ORDER BY median_income DESC")
println("chi median salary by agency")
chi_dept_median.show(1000, false)

// San Francisco

val sf_median = spark.sql("SELECT PERCENTILE(salary, 0.5) AS median_income FROM wsl300.cleaned_sf")
println("sf median salary")
sf_median.show(1000, false)

val sf_depts = spark.sql("SELECT DISTINCT department FROM wsl300.cleaned_sf")
println("sf departments")
sf_depts.show(1000, false)

val sf_num_employees = spark.sql("SELECT department, COUNT(*) FROM wsl300.cleaned_sf GROUP BY department")
println("sf employees per agency")
sf_num_employees.show(1000, false)

val sf_dept_median = spark.sql("SELECT department, PERCENTILE(salary, 0.5) AS median_income FROM wsl300.cleaned_sf GROUP BY department ORDER BY median_income DESC")
println("sf median salary by agency")
sf_dept_median.show(1000, false)