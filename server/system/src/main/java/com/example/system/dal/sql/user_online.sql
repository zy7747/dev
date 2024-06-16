CREATE TABLE `user_online`
(
    `id`              BIGINT NOT NULL COMMENT 'id',
    `uid`             BIGINT                                  DEFAULT NULL COMMENT '用户id',
    `account`         VARCHAR(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
    `login_ip`        VARCHAR(32) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '登录IP',
    `login_place`     VARCHAR(32) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '登录地',
    `login_device`    VARCHAR(32) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '登录设备',
    `operate_results` VARCHAR(64) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '操作结果',
    `status`          VARCHAR(64) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '状态',
    `remark`          VARCHAR(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
    `creator`         VARCHAR(64) COLLATE utf8mb4_unicode_ci  DEFAULT '' COMMENT '创建人',
    `updater`         VARCHAR(64) COLLATE utf8mb4_unicode_ci  DEFAULT '' COMMENT '更新人',
    `create_time`     datetime                                DEFAULT NULL COMMENT '首次登录时间',
    `update_time`     datetime                                DEFAULT NULL COMMENT '本次登录时间',
    `is_deleted`      bit(1) NOT NULL                         DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`       BIGINT                                  DEFAULT NULL COMMENT '租户id',
    `version`         INT    NOT NULL                         DEFAULT '0' COMMENT '乐观锁',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '在线用户';