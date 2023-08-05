<template>
  <div>
    <van-card
        v-for="team in props.teamList"
        :title="`${team.name}`"
        :desc="team.description"
    >
      <template #tags>
        <van-tag plain type="danger" style="margin: 8px 8px 0 0 ">
          {{ teamStatusEnum[team.status]}}
        </van-tag>
      </template>
      <template #bottom>
        <div>
          {{ `队伍人数: ${team.hasJoinNum}/${team.maxNum}` }}
        </div>
        <div v-if="team.expireTime">
          {{ '过期时间:' + team.expireTime }}
        </div>
        <div>
          {{ '创建时间:' + team.createTime }}
        </div>
      </template>
      <template #footer>
        <van-button v-if="team.userId !== currentUser?.id && !team.hasJoin"
                    size="mini" type="primary" plain @click="preJoinTeam(team)"
        >加入队伍</van-button>
        <van-button size="mini" plain @click="doUpdateTeam(team.id)"
                    v-if="team.userId === currentUser?.id"
        >更新队伍</van-button>
        <!-- 仅加入队伍可见     -->
        <van-button size="mini" plain @click="doQuitTeam(team.id)"
                    v-if="team.userId !== currentUser?.id && !team.hasJoin"
        >退出队伍</van-button>
        <van-button type="danger" size="mini" plain @click="doDeleteTeam(team.id)"
                    v-if="team.userId === currentUser?.id"
        >解散队伍</van-button>
      </template>
    </van-card>
    <van-dialog v-model:show="showPasswordDialog" title="请输入密码" show-cancel-button
                @confirm="doJoinTeam" @cancel="doJoinTeam">
      <van-field v-model="password" placeholder="请输入密码" />
    </van-dialog>
  </div>
</template>

<script setup lang="ts">
import {TeamType} from "../models/team";
import {teamStatusEnum} from "../constants/team"
import {onMounted, ref, withDefaults} from "vue";
import {useRouter} from "vue-router";
import myAxios from "../plugins/myAxios.js";
import {Dialog, Toast} from "vant";
import {getCurrentUser} from "../services/user";

interface TeamCardListProps {
  teamList: TeamType[];
}

function defineProps<T>() {
  return undefined;
}

const props = withDefaults(defineProps<TeamCardListProps>(),{
  teamList: [] as TeamType[],
});

//当前用户
const currentUser = ref();
const VanDialog = Dialog.Component;
const router = useRouter();
const showPasswordDialog = ref(false);
const password = ref('');
const joinTeamId = ref(0);

// 页面加载时触发
onMounted(async () => {
  currentUser.value = await getCurrentUser();
})

/**
 * 判断是不是加密房间，是的话显示密码框
 * @param team
 */
const preJoinTeam = (team: TeamType) => {
  joinTeamId.value = team.id;
  if (team.status === 0) {
    doJoinTeam()
  } else {
    showPasswordDialog.value = true;
  }
}
// 清空密码
const doJoinCancel = () => {
  joinTeamId.value = 0;
  password.value = '';
}

/**
 * 加入队伍
 */
const doJoinTeam = async () => {
  if (!joinTeamId.value) {
    return;
  }
  const res = await myAxios.post('/team/join',{
    teamId: joinTeamId.value,
    password: password.value
  })
  if (res?.code == 0) {
    Toast.success('加入成功');
    doJoinCancel();
  }else {
    Toast.fail('加入失败' + (res.description ? `, ${res.description}` : ''))
  }
}

/**
 * 退出队伍
 * @param id
 */
const doQuitTeam = async (id: number) => {
  const res = await myAxios.post('/team/quit',{
    teamId: id,
  })
  if (res?.code == 0) {
    Toast.success('操作成功');
  }else {
    Toast.fail('操作失败' + (res.description ? `, ${res.description}` : ''))
  }
}

/**
 * 解散队伍
 * @param id
 */
const doDeleteTeam = async (id: number) => {
  const res = await myAxios.post('/team/delete',{
    id,
  })
  if (res?.code == 0) {
    Toast.success('操作成功');
  }else {
    Toast.fail('操作失败' + (res.description ? `, ${res.description}` : ''))
  }
}


/**
 * 跳转到更新队页
 * @param id
 */
const doUpdateTeam = (id: number) => {
  router.push({
    path: '/team/update',
    query: {
      id,
    }
  })
}

</script>

<style scoped>

</style>