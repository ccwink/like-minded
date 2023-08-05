<template>
  <div id="team">
    <van-search v-model="searchText" placeholder="搜索队伍" @search="onSearch" />
    <van-tabs v-model:active="active" @change="onTabChange">
      <van-tab title="公开" name="public" />
      <van-tab title="加密" name="private" />
    </van-tabs>
    <van-button class="add-button" type="primary" icon="plus" @click="toAddTeam"/>
    <team-card-list :team-list="teamList"/>
    <van-empty v-if="teamList?.length < 1" description="数据为空"/>
  </div>
</template>

<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import myAxios from "../plugins/myAxios.js";
import {Toast} from "vant";
import TeamCardList from "../components/TeamCardList.vue";

const router = useRouter();
const active = ref('public')
const searchText = ref('');

// 跳转到加入队伍
const toAddTeam = () => {
  router.push({
    path: "/team/add"
  })
}

// 队伍列表数据
const teamList = ref([]);

/**
 * 切换状态查询
 * @param name
 */
const onTabChange = (name) => {
  // 查公开
  if (name === 'public') {
    listTeam(searchText.value, 0);
  }else {
    // 查加密
    listTeam(searchText.value, 2)
  }
}

/**
 * 搜索队伍
 * @param val
 * @returns {Promise<void>}
 */
const listTeam = async (val = '', status = 0) => {
  const res = await myAxios.get("/team/list", {
    params: {
      searchText: val,
      pageNum: 1,
      status
    }
  });
  if (res?.code === 0) {
    teamList.value = res.data;
  }else {
    Toast.fail("队伍加载失败，请刷新重试")
  }
}

// 页面加载时只触发一次
onMounted(() => {
  listTeam();
})

const onSearch = (val) => {
  listTeam(val);
}


</script>

<style scoped>
.add-button{
  position: fixed;
  bottom: 60px;
  width: 50px;
  right: 12px;
  height: 50px;
  border-radius: 50%;
  z-index: 99;
}
</style>