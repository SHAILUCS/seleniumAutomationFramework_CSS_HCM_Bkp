SELECT concat(ra.firstname," ",ra.lastname) as label, `ra`.`id` as `value`, `ra`.`id` as `applicant_id`, `rae`.`email`, `rap`.`phone` 
FROM `tbl_recruitment_applicant` as `ra` 
INNER JOIN `tbl_recruitment_applicant_email` as `rae` ON `rae`.`applicant_id` = `ra`.`id` AND `primary_email` = 1 
INNER JOIN `tbl_recruitment_applicant_phone` as `rap` ON `rap`.`applicant_id` = `ra`.`id` AND `primary_phone` = 1 
WHERE current_stage IN (SELECT id FROM tbl_recruitment_stage as rs WHERE archive = 0 AND stage_label_id = 4) AND `ra`.`archive` = 0 AND `ra`.`status` = 1 AND `ra`.`flagged_status` = 0 AND `ra`.`duplicated_status` = 0
 AND not EXISTS ( select s_rta.applicant_id, (case when s_rta.status = 1 AND (s_rsl.stage_number = 3 or s_rsl.stage_number = 6) 
then (select mark_as_no_show from tbl_recruitment_applicant_group_or_cab_interview_detail where recruitment_task_applicant_id = s_rta.id AND archive = 0) else 0 end) as mark_as_no_show 
from tbl_recruitment_task_applicant as s_rta 
inner join tbl_recruitment_task s_rt on s_rt.id = s_rta.taskId AND s_rt.status = 1 
inner join tbl_recruitment_stage_label s_rsl on s_rsl.stage_number = s_rt.task_stage AND s_rsl.id = 4 
where ra.id = s_rta.applicant_id AND s_rta.archive = 0 AND s_rta.status IN (1,0) having mark_as_no_show = 0 )
# AND ra.recruiter=10
GROUP BY `ra`.`id`;