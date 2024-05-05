<script setup>
import {onMounted, reactive, ref} from "vue"
import {Lock, Message, Select} from "@element-plus/icons-vue";
const securityForm = reactive({
  email: null,
  password_old: '',
  password_new: '',
  password_new_repeat: '',
});

</script>

<template>
  <div>
    <div>
      <h1><el-icon><Message/></el-icon> 邮箱设置</h1>
      <el-form
          ref="emailForm"
          label-position="top"
          label-width="100px"
          :model="securityForm"
          style="max-width: 460px">
        <el-form-item label="邮箱地址" prop="email">
          <el-input v-model="securityForm.email" />
        </el-form-item>
      </el-form>
      <el-button type="success" :icon="Select" >保存邮件地址</el-button>
    </div>
    <el-divider/>
    <div>
      <h1><el-icon><Lock/></el-icon> 密码设置</h1>
      <el-form
          ref="passwordForm"
          label-position="top"
          label-width="100px"
          :model="securityForm"
          style="max-width: 460px">
        <el-form-item prop="password_old" label="原密码">
          <el-input type="password" show-password v-model="securityForm.password_old" />
        </el-form-item>
        <el-form-item prop="password_new" label="新密码">
          <el-input type="password" show-password v-model="securityForm.password_new" />
        </el-form-item>
        <el-form-item prop="password_new_repeat" label="重复新密码">
          <el-input type="password" show-password v-model="securityForm.password_new_repeat" />
        </el-form-item>
      </el-form>
      <el-button type="success" :icon="Select">修改密码</el-button>
    </div>
  </div>
</template>

<style scoped>

</style>