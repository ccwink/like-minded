<template>
  <van-skeleton title avatar :row="3" :loading="props.loading" v-for="user in props.userList">
    <van-card
        :title="`${user.username}(${user.email})`"
        :desc="user.profile"
        :thumb="user.avatarUrl"
    >
      <template #tags>
        <van-tag plain type="danger" v-for="tag in user.tags" style="margin: 8px 8px 0 0 ">
          {{tag}}
        </van-tag>
      </template>
      <template #footer>
        <van-button size="mini" @click="`Toast('提示内容')`">联系我</van-button>
      </template>
    </van-card>
  </van-skeleton>
</template>

<script setup lang="ts">
import {UserType} from "../models/user";
import {withDefaults} from "vue";


interface UserCardListProps {
  loading: boolean;
  userList: UserType[];
}

function defineProps<T>() {
  return undefined;
}

const props = withDefaults(defineProps<UserCardListProps>(),{
  userList: [] as UserType[],
  loading: true,
});

</script>

<style scoped>

</style>