<template>
  <van-form @submit="onSubmit">
    <van-cell-group inset>
      <van-field
          v-model="editUser.currentValue"
          :name="editUser.editKey"
          :label="editUser.editName"
          :placeholder="`请输入${editUser.editName}`"
      />
    </van-cell-group>
    <div style="margin: 16px;">
      <van-button round block type="primary" native-type="submit">
        提交
      </van-button>
    </div>
  </van-form>
</template>

<script setup lang="ts">
import {useRoute, useRouter} from "vue-router";
  import {ref} from "vue";
  import myAxios from "../plugins/myAxios.js";
  import {Toast} from "vant";
  import {getCurrentUserState} from "../states/user";

  const route = useRoute();
  const router = useRouter();
  const editUser = ref({
    editKey: route.query.editKey,
    currentValue: route.query.currentValue,
    editName: route.query.editName
  })
  console.log(editUser.value)

  const onSubmit = async () => {
    // 获取当前用户信息
    const currentUser = await getCurrentUserState();
    // 请求更新
    const res = await myAxios.post('/user/update', {
      'id':currentUser.id,
      [editUser.value.editKey as string]: editUser.value.currentValue,  //动态取值
    })
    console.log(res,'更新请求')
    if (res.code === 0 && res.data > 0){
      Toast.success('修改成功');
      router.back();
    }else {
      Toast.fail('修改失败')
    }
  }
</script>

<style scoped>

</style>