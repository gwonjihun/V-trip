<template>
  <div class="comment-item">
    <b-row>
      <!-- <b-col cols="8" class="content" :class="{ child: isChild }">{{ comment }}</b-col> -->
      <b-col cols="7" class="content">{{ (isChild ? "└ " : "") + comment }}</b-col>
      <b-col cols="2">
        <span v-if="!isChild" v-b-toggle="'arccordion-' + comment_id" class="reply-comment-btn">답글</span>
      </b-col>
      <b-col cols="1">
        <span class="link" @click="moveUserPage(writername)">
          {{ `${writername}(${nickname})` }}
        </span>
      </b-col>
      <b-col cols="2">
        {{ createat | dateFilter }}
      </b-col>
    </b-row>
    <b-row v-if="children">
      <b-col>
        <comment-item v-for="child in children" :key="child.comment_id" v-bind="child" :is-child="true" />
      </b-col>
    </b-row>
    <div v-if="!isChild">
      <b-collapse :id="'arccordion-' + comment_id" accordion="comment-item">
        <comment-form :content_id="content_id" :plink="comment_id" />
      </b-collapse>
    </div>
  </div>
</template>

<script>
import CommentForm from "./CommentForm.vue";
export default {
  components: { CommentForm },
  name: "CommentItem",
  props: {
    comment_id: Number,
    content_id: Number,
    comment: String,
    createat: String,
    writername: String,
    nickname: String,
    children: [],
    isChild: { type: Boolean, default: false },
  },
  methods: {
    moveUserPage(user_id) {
      this.$router.push({ name: "userPage", params: { id: user_id } }).catch(() => {});
    },
  },
};
</script>

<style scoped>
.row {
  text-align: right;
}
.content {
  text-align: left;
}
.child {
  padding-left: 3rem;
}

.comment-item {
  margin-top: 1rem;
}

.reply-comment-btn {
  color: gray;
}
</style>
