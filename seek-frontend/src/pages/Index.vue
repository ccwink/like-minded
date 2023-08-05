<template>
  <van-cell center title="心动模式">
    <template #right-icon>
      <van-switch v-model="isMatchMode" size="24"/>
    </template>
  </van-cell>
  <user-card-list :user-list="userList" :loading="loading"/>
  <van-empty v-if="!userList || userList.length < 1" description="数据为空"/>
</template>

<script setup lang="ts">
import {ref, watchEffect} from "vue";
import myAxios from "../plugins/myAxios.js";
import UserCardList from "../components/UserCardList.vue";
import {UserType} from "../models/user";
import {Toast} from "vant/es";

// 存放用户列表
const userList = ref([])

const loading = ref(true)

// 定义一个开关切换常量，默认关闭
const isMatchMode = ref<boolean>(false);

// 加载数据
const loadData = async () => {
  let userListData;
  loading.value = true;
  // 心动模式，根据标签匹配用户
  if (isMatchMode.value) {
    const  num = 10;
    userListData = await myAxios.get('/user/match', {
      params: {
        num
      }
    })
        .then(function (response) {
          console.log('/user/match succeed', response);
          Toast.success("请求成功！")
          return response?.data;
        })
        .catch(function (error) {
          console.log('/user/match error', error);
          Toast.fail('请求失败');
        })
  }else {
    // 普通模式，直接分页查询用户
    userListData = await myAxios.get('/user/recommend',{
      params: {
        pageSize: 10,
        pageNum: 1,
      },
    })
        .then(function (response){
          console.log("/user/recommend success", response);
          Toast.success("请求成功！")
          // 返回数据
          // ?. 可选链操作符，避免数据为null或者undefined时报错
          return response?.data?.records;
        })
        .catch(function (error){
          console.log("/user/recommend error", error);
          Toast.fail("请求失败！")
        })
  }
  if (userListData){
    userListData.forEach((user: UserType) => {
      if (user.tags){
        user.tags = JSON.parse(user.tags)
      }
    })
    userList.value = userListData;
  }
  loading.value = false
}
watchEffect(() => {
  loadData();
})


</script>

<style scoped>

</style>