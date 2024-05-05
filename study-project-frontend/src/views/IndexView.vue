<template>
  <div style="height: 100vh">
    <el-container style="height: 100%">
      <el-aside style="border-right: solid 1px #d3d3d3; transition: 0.5s" :width="isCollapse ? '60px' : '220px'">
        <div style="text-align: center;padding: 15px 0;margin-left: 12px">
          <el-image src="https://avatars.githubusercontent.com/u/68583457?s=400&v=4"
                    style="width: 35px"/>
        </div>
        <el-menu
            :default-active="router.currentRoute.value.path"
            router
            :collapse="isCollapse"
            style="border: none"
          >
          <el-menu-item index="/index">
            <el-icon><icon-menu /></el-icon>
            <span>帖子列表</span>
          </el-menu-item>
          <el-menu-item index="2">
            <el-icon><document /></el-icon>
            <span>校园表白墙</span>
          </el-menu-item>
          <el-menu-item index="3">
            <el-icon><setting /></el-icon>
            <span>帖子管理</span>
          </el-menu-item>
          <el-menu-item index="4">
            <el-icon><StarFilled /></el-icon>
            <span>我的收藏</span>
          </el-menu-item>
          <el-menu-item index="/index/settings">
            <el-icon><UserFilled /></el-icon>
            <span>个人设置</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header style="border-bottom: solid 1px #d3d3d3; padding: 0 20px 0 0">
          <div style="display: flex;padding: 7px 0">
            <div>
              <el-button :icon="isCollapse ? Expand : Fold" text @click="isCollapse = !isCollapse"
                         style="font-size: 25px;margin-top: 6px; margin-left: 5px"/>
            </div>
            <!-- 左边直接占据剩余的空间 -->
            <div style="flex: 1; text-align: center">
              <el-input
                  style="max-width: 400px;margin-top: 6px"
                  placeholder="搜索论坛内容..."
              >
                <template #prefix>
                  <el-icon><Search/></el-icon>
                </template>
              </el-input>
            </div>
            <div style="display: flex">
              <div style="text-align: right;margin: 8px 10px 0 0;line-height: 15px">
                <div style="font-weight: bold">{{store.auth.user?.username}}</div>
                <div style="font-size: 13px">{{store.auth.user?.email}}</div>
              </div>
            </div>
           <div style="padding-right: 14px">
             <!-- 右边是用多少占多少 -->
             <el-dropdown trigger="click">
               <div><el-avatar class="avatar"
                   :size="45" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" /></div>
               <template #dropdown>
                 <el-dropdown-menu>
                   <el-dropdown-item><el-icon><setting /></el-icon>个人设置</el-dropdown-item>
                   <el-dropdown-item @click="logout"  divided><el-icon color="red"><Back /></el-icon><div style="color: red">退出登录</div></el-dropdown-item>
                 </el-dropdown-menu>
               </template>
             </el-dropdown>
           </div>
          </div>


        </el-header>
        <el-main style="padding: 0">
          <el-scrollbar style="height: 100%">
            <router-view/>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";
import {useStore} from "@/stores";
import {Document, Location, Setting, Menu as IconMenu, Search, UserFilled, StarFilled, Back, Expand, Fold} from "@element-plus/icons-vue";
import {ref} from "vue";

//折叠菜单
const isCollapse = ref(true)

const store = useStore()

const logout = () => {
    get('/api/auth/logout', (message) => {
      ElMessage.success(message)
      store.auth.user = null
      localStorage.removeItem('user')
      router.push('/')
    })
}
</script>

<style scoped>
.avatar:hover{
  cursor: pointer;
}
</style>