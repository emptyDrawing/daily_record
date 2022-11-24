<template>
  <li>
    <div class="post-title">{{ item.title }}</div>
    <div class="post-content">{{ item.contents }}</div>
    <div class="post-time">{{ item.createdAt }}</div>
  </li>
</template>

<script>
export default {
  props: {
    item: {
      type: Object,
      required: true,
    },
  },
};
</script>

<style></style>
