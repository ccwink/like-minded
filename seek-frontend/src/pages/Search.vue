<template>
  <form action="/">
    <van-search
        v-model="searchText"
        show-action
        placeholder="请输入搜索关键词"
        @search="onSearch"
        @cancel="onCancel"
    />
  </form>
  <van-divider content-position="left">已选标签</van-divider>
  <div v-if="activeIds.length === 0 ">
    <van-tag plain round type="warning">暂无标签</van-tag>
  </div>
  <van-row gutter="20">
    <van-col v-for="tag in activeIds" >
      <van-tag closeable round size="small" type="primary" @close="doClose(tag)">
        {{ tag }}
      </van-tag>
    </van-col>
  </van-row>
  <van-divider content-position="left">选择标签</van-divider>
  <van-tree-select
      v-model:active-id="activeIds"
      v-model:main-active-index="activeIndex"
      :items="tagList"
  />
  <div style="padding: 16px">
    <van-button type="primary" block @click="doSearchResult">搜索</van-button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import {useRouter} from "vue-router";

const searchText = ref('');
// 原标签列表
const originTagList = [
  {
    text: '语言',
    children: [
      { text: 'java', id: 'java'},
      { text: 'python', id: 'python'},
      { text: 'c++', id: 'c++'},
      { text: 'go', id: 'go'},
    ]
  },
  {
    text: '性别',
    children: [
      { text: '男', id: '男' },
      { text: '女', id: '女' },
    ],
  },
  {
    text: '年级',
    children: [
      { text: '大一', id: '大一' },
      { text: '大二', id: '大二' },
      { text: '大三', id: '大三' },
      { text: '大4', id: '大4' },
      { text: '大5', id: '大5' },
      { text: '大6', id: '大6' },
      { text: '大7', id: '大7' },
    ],
  },
];

// 标签列表
let tagList = ref(originTagList);
// 搜索
const onSearch = (val) => {
  tagList.value = originTagList.map(parentTag => {
    const tempChildrenTag = [...parentTag.children];
    const tempParentTag = {...parentTag};
    tempParentTag.children = tempChildrenTag.filter(item => item.text.includes(searchText.value));
    return tempParentTag;
  })
};
// 取消搜索
const onCancel = () => {
  searchText.value = '';
  tagList.value = originTagList;
};

// 已选中的标签
const activeIds = ref([]);  // 右侧选中项的 id
const activeIndex = ref(0); // 左侧选中项的索引

// 移除标签
const doClose = (tag) => {
  activeIds.value = activeIds.value.filter(item => {
    return item !== tag;
  })
}

const router = useRouter();
const doSearchResult = () => {
  // 路由跳转
  router.push({
    path: '/user/list',
    query: {
      tags: activeIds.value
    }
  })
}


</script>

<style scoped>

</style>