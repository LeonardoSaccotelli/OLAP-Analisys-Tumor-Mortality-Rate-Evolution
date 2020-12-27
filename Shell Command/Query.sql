--Grouping deaths estimate by sex
SELECT sex.sex_name AS sex, ROUND (SUM(mortality.deaths_estimate)) AS deaths_estimate
FROM sex JOIN mortality ON (sex.sex_id = mortality.sex)
GROUP BY sex.sex_name;

--Groupind deaths by sex caused by pancreas tumor in Alabama's counties in 2000
SELECT sex.sex_name AS sex, ROUND (SUM(mortality.deaths_estimate)) AS deaths_estimate
FROM sex JOIN mortality ON (sex.sex_id = mortality.sex)
WHERE mortality.locality IN ( SELECT fips_county FROM locality WHERE locality_name LIKE 'Alabama%' )
	 AND mortality.tumor IN ( SELECT tumor_id FROM tumor WHERE tumor_name = 'Pancreatic cancer')
	 AND mortality.years = 2000
GROUP BY sex.sex_name;

--Grouping deaths estimate by tumor_name by sex
SELECT tumor.tumor_name as tumor_name, sex.sex_name AS sex, 
	   ROUND (SUM(mortality.deaths_estimate)) AS deaths_estimate
FROM sex JOIN mortality ON (sex.sex_id = mortality.sex) JOIN tumor ON (tumor.tumor_id = mortality.tumor)
GROUP BY tumor.tumor_name,sex.sex_name;

--Showing year by year the deaths estimate only for female tumor
SELECT mortality.years AS years, ROUND (SUM(mortality.deaths_estimate)) AS deaths_estimate_for_female_cancer
FROM mortality
WHERE mortality.tumor IN ( SELECT tumor_id FROM tumor WHERE tumor_name = 'Ovarian cancer' OR tumor_name = 'Cervical cancer' ) 
GROUP BY mortality.years;							

--Showing year by year the deaths estimate only for male tumor
SELECT mortality.years AS years, ROUND (SUM(mortality.deaths_estimate)) AS deaths_estimate_for_male_cancer
FROM mortality
WHERE mortality.tumor IN ( SELECT tumor_id FROM tumor WHERE tumor_name = 'Testicular cancer' OR tumor_name = 'Prostate cancer' ) 
GROUP BY mortality.years;	

--Grouping deaths estimate by tumor name and showing the top 5 tumor for number of deaths
SELECT tumor.tumor_name AS tumor_name, ROUND (SUM(mortality.deaths_estimate)) AS deaths_estimate
FROM mortality JOIN tumor ON (tumor.tumor_id = mortality.tumor) 
GROUP BY tumor.tumor_name
ORDER BY deaths_estimate DESC
LIMIT 5;

--Showing for each tumor, the number of deaths in California in years 2004-2008, order by number of deaths
SELECT tumor.tumor_name AS tumor_name, ROUND (SUM(mortality.deaths_estimate)) AS deaths_estimate
FROM tumor JOIN mortality ON (tumor.tumor_id = mortality.tumor)
WHERE mortality.locality IN ( SELECT fips_county FROM locality WHERE locality_name LIKE 'California%' )
	AND mortality.years > 2004 AND mortality.years < 2008
GROUP BY tumor.tumor_name
ORDER BY deaths_estimate DESC;