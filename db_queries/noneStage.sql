
SELECT concat(ra.firstname," ",ra.lastname) as label, `ra`.`id` as `value`, `ra`.`id` as `applicant_id`, `rae`.`email`, `rap`.`phone` 
FROM `tbl_recruitment_applicant` as `ra` 
INNER JOIN `tbl_recruitment_applicant_email` as `rae` ON `rae`.`applicant_id` = `ra`.`id` AND `primary_email` = 1 
INNER JOIN `tbl_recruitment_applicant_phone` as `rap` ON `rap`.`applicant_id` = `ra`.`id` AND `primary_phone` = 1 
WHERE `ra`.`archive` = 0 AND `ra`.`status` = 1 AND `ra`.`flagged_status` = 0 AND `ra`.`duplicated_status` = 0
# AND ra.recruiter=10
 GROUP BY `ra`.`id;