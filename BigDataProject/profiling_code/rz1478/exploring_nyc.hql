-- Calculating median income of all NYC city employees
SELECT PERCENTILE(salary, 0.5) AS median_income
FROM nyc;

-- Retrieving unique government agencies in NYC
SELECT DISTINCT agency
FROM nyc; 

-- Retrieving number of workers at each agency
SELECT agency, COUNT(*)
FROM nyc
GROUP BY agency;

-- Calculating median income of each NYC government agency
SELECT agency, PERCENTILE(salary, 0.5) AS median_income
FROM nyc
GROUP BY agency
ORDER BY median_income DESC;