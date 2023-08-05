<template>
  <user-card-list :user-list="userList"/>
  <van-empty v-if="!userList || userList.length < 1" image="search" description="搜索结果为空"/>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import myAxios from "../plugins/myAxios.js";
import {Toast} from "vant";
import qs from 'qs';
import UserCardList from "../components/UserCardList.vue";


const route = useRoute();
const { tags } = route.query;

// 存放用户列表
const userList = ref([])

// 页面挂载完毕发送请求
onMounted(async () => {
  // 异步调用
  const userListData = await myAxios.get('/user/search/tags',{
    params: {
      tagNameList: tags
    },
    // 序列化
    paramsSerializer: params => {
      return qs.stringify(params, { indices: false})
    }
  })
    .then(function (response){
      console.log("/user/search/tags success", response);
      Toast.success("请求成功！")
      // 返回数据
      // ?. 可选链操作符，避免数据为null或者undefined时报错
      return response?.data
    })
    .catch(function (error){
      console.log("/user/search/tags error", error);
      Toast.fail("请求失败！")
    })
  if (userListData){
    userListData.forEach(user => {
      if (user.tags){
        user.tags = JSON.parse(user.tags)
      }
    })
    userList.value = userListData;
  }
})

/*const mockUser = ref({
  id: 1,
  username: '国王',
  userAccount: 'wink',
  profile: '一个精神小伙，目前还有头发，加油加油加油！！！！',
  avatarUrl: 'https://img.zcool.cn/community/047e4558194755a84a148cd0d7924b.jpg',
  gender: '男',
  phone: '1234567890',
  email: '123456@qq.com',
  createTime: new Date().toLocaleString(),
})*/
</script>

<style lang="stylus" rel="stylesheet/stylus">

</style>