CREATE TABLE `role`
(
    `id`          BIGINT                                  NOT NULL COMMENT 'id',
    `role_name`   VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名称',
    `role_code`   VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色编码',
    `status`      VARCHAR(64) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '状态',
    `remark`      text COLLATE utf8mb4_unicode_ci COMMENT '备注',
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
  COLLATE = utf8mb4_unicode_ci COMMENT ='角色';
