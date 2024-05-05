<script setup>
import {onMounted, reactive, ref} from "vue";
import {Select} from "@element-plus/icons-vue";
const infoForm = reactive({
  username: null,
  desc: '',
  phone: '',
  qq: '',
  wx: '',
  blog: '',
  sex: ''
})
</script>


<template>
  <div>
    <el-form
        ref="form"
        :rules="rules"
        label-position="top"
        label-width="100px"
        :model="infoForm"
        style="max-width: 460px">
      <el-form-item prop="username" label="用户名">
        <el-input :maxlength="8" v-model="infoForm.username" />
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="infoForm.sex" class="ml-4">
          <el-radio label="male" size="large">男</el-radio>
          <el-radio label="female" size="large">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="phone" label="手机号">
        <el-input :maxlength="11" v-model="infoForm.phone" />
      </el-form-item>
      <el-form-item label="QQ" prop="qq">
        <el-input :maxlength="11" v-model="infoForm.qq" />
      </el-form-item>
      <el-form-item label="微信" prop="wx">
        <el-input :maxlength="30" v-model="infoForm.wx" />
      </el-form-item>
      <el-form-item label="博客" prop="blog">
        <el-input :maxlength="50" v-model="infoForm.blog" />
      </el-form-item>
      <el-form-item label="个人简介" prop="desc">
        <el-input :maxlength="500" type="textarea" v-model="infoForm.desc" :rows="6"/>
      </el-form-item>
    </el-form>
    <el-button type="success" :icon="Select" >保存个人信息设置</el-button>
  </div>
</template>

<style scoped>

</style>