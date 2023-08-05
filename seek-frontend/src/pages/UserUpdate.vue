<template>
  <template v-if="user">
    <van-cell title="头像" is-link to="/user/edit">
      <img :src="user.avatarUrl" style="height: 48px"/>
    </van-cell>
    <van-cell title="账号" :value="user.userAccount" />
    <van-cell title="昵称" is-link to="/user/edit" :value="user.username" @click="toEdit('username',user.username,'昵称')"/>
    <van-cell title="性别" is-link to="/user/edit" :value="user.gender" @click="toEdit('gender',user.gender,'性别')" />
    <van-cell title="电话" is-link to="/user/edit" :value="user.phone" @click="toEdit('phone',user.phone,'电话')" />
    <van-cell title="邮箱" is-link to="/user/edit" :value="user.email" @click="toEdit('email',user.email,'邮箱')" />
    <van-cell title="注册时间" :value="user.createTime" />
  </template>
</template>

<script setup lang="ts">
  import {onMounted, ref} from "vue";
  import {useRouter} from "vue-router";
  import {getCurrentUser} from "../services/user";

/*  const user = ref({
    id: 1,
    username: '国王',
    userAccount: 'wink',
    avatarUrl: 'https://img.zcool.cn/community/047e4558194755a84a148cd0d7924b.jpg',
    gender: '男',
    phone: '1234567890',
    email: '123456@qq.com',
    createTime: new Date().toLocaleString(),
  })*/

  const user = ref();

  // 获取当前用户信息
  onMounted(async () => {
    user.value = await getCurrentUser();
  })

  const router = useRouter();
  // 跳转页面修改信息
  const toEdit = (editKey: string, currentValue: string, editName: string) => {
    router.push({
      path: '/user/edit',
      query: {
        editKey,
        currentValue,
        editName
      }
    })
  }

</script>

<style scoped>

</style>