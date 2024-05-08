<script setup>
import {onMounted, reactive, ref} from "vue";
import {Select} from "@element-plus/icons-vue";
import {post} from "../../net";
import {ElMessage} from "element-plus";
const infoForm = reactive({
  username: null,
  biography: '',
  phone: '',
  qq: '',
  wx: '',
  blog: '',
  sex: 'male'
})

// 对表单进行验证, 验证成功发送请求
const form = ref()


const validateUsername = (rule, value, callback) => {
  if (value === null) {
    callback(new Error('请输入用户名'))
  } else if(!/^[a-zA-Z0-9\u4e00-\u9fa5]+$/.test(value)){
    callback(new Error('用户名不能包含特殊字符，只能是中文/英文'))
  } else {
    callback()
  }
}
const validatePhoneNumber = (rule, value, callback) => {
  if(value !== '' && !/^1[3-9]\d{9}$/.test(value)) {
    callback(new Error('手机号格式错误'))
  } else {
    callback()
  }
}

const validateBlogURL = (rule, value, callback) => {
  if(value !== '' && !/^https?:\/\/((www.)?[\w-]+.[\w-]+.?[\w-])?\/?([\w-])\/?([\w-]*)\/?$/.test(value)) {
    callback(new Error('博客地址格式错误'))
  } else {
    callback()
  }
}


const rules = {
  username: [
    { validator: validateUsername, trigger: ['blur', 'change'] },
    { min: 2, max: 8, message: '用户名的长度必须在2-8个字符之间', trigger: ['blur', 'change'] },
  ],
  phone: [
    { validator: validatePhoneNumber, trigger: ['blur', 'change'] },
    { min: 11, max: 11, message: '手机号只能是11位标准手机号', trigger: ['blur', 'change'] },
  ],
  qq: [
    { min: 8, max: 11, message: 'QQ号只能是8-11位', trigger: ['blur', 'change'] },
  ],
  wx: [
    { min: 5, max: 30, message: '微信号只能是5-30位', trigger: ['blur', 'change'] },
  ],
  blog: [
    { validator: validateBlogURL, trigger: ['blur', 'change'] },
    { max: 50, message: '博客URL地址太长', trigger: ['blur', 'change'] },
  ],
  biography: [
    { max: 500, message: '个人简介不能超过500字', trigger: ['blur', 'change'] },
  ]
}

const save = () => {
  form.value.validate((isValid) => {
    if(isValid) {
      post('/api/user/save-info', infoForm, () => {
        ElMessage.success("保存成功！")
      }, 'json')
    } else {
      ElMessage.warning('表单内容有误，请重新检查内容是否正确填写')
    }
  })
}
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
      <el-form-item label="个人简介" prop="biography">
        <el-input :maxlength="500" type="textarea" v-model="infoForm.biography" :rows="6"/>
      </el-form-item>
    </el-form>
    <el-button type="success" :icon="Select" @click="save">保存个人信息设置</el-button>
  </div>
</template>

<style scoped>

</style>