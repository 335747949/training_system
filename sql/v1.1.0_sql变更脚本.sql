## 错题表增加字段
ALTER TABLE `exam_user_error_question` ADD COLUMN `examination_id` int(11) NULL COMMENT '考试id' AFTER `exam_question_id`;