<!-- CForm -->
<template>
  <el-form
    ref="formRef"
    v-bind="$attrs"
    :model="model"
    :rules="rules"
    label-position="top"
    :validate-on-rule-change="false"
  >
    <template v-for="item in formConfig.formParams">
      <el-col :span="item.span" v-show="item.show ? item.show() : true">
        <el-form-item :label="item.label" :prop="item.prop">
          <c-schema
            :item="item"
            :type="item.type"
            :options="item.options"
            :params="item.params"
            v-model="model[item.prop]"
          />
        </el-form-item>
      </el-col>
    </template>
  </el-form>
</template>

<script lang="ts" setup>
import type { FormInstance } from "element-plus";
const formRef: any = ref<FormInstance>();

const prop = defineProps({
  formConfig: {
    text: "表格配置",
    type: [Object],
    default: () => {
      return {};
    },
  },
  model: {
    text: "表单值",
    type: [Object],
    default: () => {
      return {};
    },
  },
});

//提交
const submitForm = () => {
  if (!formRef) return;
  return unref(formRef).validate((valid: boolean) => {
    if (valid) {
      return true;
    } else {
      ElMessage({
        message: "校验不通过",
        type: "warning",
        showClose: true,
        grouping: true,
      });
      return false;
    }
  });
};

//重置
const resetForm = () => {
  if (!formRef) return;
  unref(formRef).resetFields();
};

const rules = computed(() => {
  const rules: any = {};

  if (
    !prop.formConfig ||
    !prop.formConfig.formParams ||
    !prop.formConfig.formParams.length
  ) {
    return rules;
  }

  prop.formConfig.formParams.forEach((item: any) => {
    if (item.rules) {
      rules[item.prop] = item.rules.map((i: any) => {
        if (i.required) {
          return {
            required: true,
            message: item.label + " " + $t("system.is no null", "不能为空"),
            trigger: "blur",
          };
        } else {
          return item;
        }
      });
    }
  });

  return rules;
});

defineExpose({
  submitForm,
  resetForm,
});
</script>

<style lang="scss" scoped>
.el-col {
  padding: 0 7px;
}
.el-form {
  display: flex;
  flex-wrap: wrap;
}
</style>
