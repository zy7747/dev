CREATE TABLE `permission`
(
    `id`          BIGINT                                  NOT NULL COMMENT 'id',
    `name`        VARCHAR(32) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '权限名称',
    `permission`  VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限标识',
    `type`        VARCHAR(32) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '类型',
    `description` VARCHAR(512) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '描述',
    `status`      VARCHAR(64) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '状态',
     `remark`      VARCHAR(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
    `creator`     VARCHAR(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '创建人',
    `updater`     VARCHAR(64) COLLATE utf8mb4_unicode_ci           DEFAULT '' COMMENT '更新人',
    `create_time` datetime                                         DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                         DEFAULT NULL COMMENT '更新时间',
    `is_deleted`  bit(1)                                  NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id`   BIGINT                                           DEFAULT NULL COMMENT '租户id',
    `version`     INT                                     NOT NULL DEFAULT '0' COMMENT '乐观锁',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='权限';