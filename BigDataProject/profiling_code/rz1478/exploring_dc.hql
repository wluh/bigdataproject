-- Calculating median income of all DC city employees
SELECT PERCENTILE(salary, 0.5) AS median_income
FROM dc;

-- Retrieving unique government agencies in DC
SELECT DISTINCT agency
FROM dc; 

-- Retrieving number of workers at each agency
SELECT agency, COUNT(*)
FROM dc
GROUP BY agency;

-- Calculating median income of each DC government agency 
SELECT agency, PERCENTILE(salary, 0.5) AS median_income
FROM dc
GROUP BY agency
ORDER BY median_income DESC;