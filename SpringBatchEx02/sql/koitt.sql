DROP TABLE IF EXISTS raw_report;
DROP TABLE IF EXISTS report;

# CPC(Cost Per Click), CPM(Cost Per 1000 impressions)
# 일자, 노출수, 클릭수, 수익

CREATE TABLE report (
	refId INT,
	name VARCHAR(10),
	age INT,
	dob DATE,
	income DECIMAL(9, 2)
);

SELECT * FROM raw_report;
SELECT * FROM report;