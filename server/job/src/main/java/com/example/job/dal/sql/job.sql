CREATE TABLE `job`
(
    `id`               bigint                                                       NOT NULL COMMENT 'id',
    `job_name`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务名称',
    `job_code`         varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务编码',
    `start_time`       datetime                                                              DEFAULT NULL COMMENT '开始时间',
    `execute_count`    int                                                                   DEFAULT '0' COMMENT '执行次数',
    `remaining_count`  int                                                                   DEFAULT '0' COMMENT '剩余次数',
    `execute_interval` int                                                                   DEFAULT '0' COMMENT '执行间隔',
    `cron`             varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT 'cron表达式',
    `status`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '状态',
    `remark`           varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '备注',
    `creator`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '创建人',
    `updater`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci          DEFAULT '' COMMENT '更新人',
    `create_time`      datetime                                                              DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime                                                              DEFAULT NULL COMMENT '更新时间',
    `is_deleted`       bit(1)                                                       NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`        bigint                                                                DEFAULT NULL COMMENT '租户id',
    `version`          int                                                          NOT NULL DEFAULT '0' COMMENT '乐观锁',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `job_name` (`job_name`),
    UNIQUE KEY `job_code` (`job_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='定时任务';