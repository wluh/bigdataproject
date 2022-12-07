-- Calculating median income of all Boston city employees
SELECT PERCENTILE(salary, 0.5) AS median_income
FROM boston;

-- Retrieving unique government agencies in Boston
SELECT DISTINCT agency
FROM boston; 

-- Retrieving number of workers at each agency
SELECT agency, COUNT(*)
FROM boston
GROUP BY agency;

-- Calculating median income of each Boston government agency
SELECT agency, PERCENTILE(salary, 0.5) AS median_income
FROM boston
GROUP BY agency
ORDER BY median_income DESC;