<!-- CForm -->
<template>
  <el-form
    ref="formRef"
    :disabled="disabled"
    :model="formConfig.formData"
    :rules="rules"
    label-position="top"
    :validate-on-rule-change="false"
  >
    <template v-for="item in formConfig.formParams">
      <el-col
        :span="item.span"
        v-if="item.show == undefined ? true : item.show"
      >
        <el-form-item :label="item.label" :prop="item.prop">
          <c-schema
            :type="item.type"
            :options="item.options"
            :params="item.params"
            v-model="formConfig.formData[item.prop]"
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
  disabled: {
    text: "禁用表单",
    type: [Boolean],
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
  if (
    !prop.formConfig ||
    !prop.formConfig.formParams ||
    !prop.formConfig.formParams.length
  ) {
    return {};
  }
  const rules: any = {};

  prop.formConfig.formParams.forEach((item: any) => {
    if (item.rules) {
      rules[item.prop] = item.rules;
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
