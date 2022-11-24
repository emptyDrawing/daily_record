<template>
  <div>
    <div class="main list-container contents">
      <h1 class="page-header">Today I Learned</h1>
      <LoadingSpinner v-if="isLoading"></LoadingSpinner>
      <ul v-else>
        <NoteListItem
          v-for="item in listItem"
          :item="item"
          :key="item._id"
        ></NoteListItem>
      </ul>
    </div>
  </div>
</template>

<script>
import { fetchNotes } from '@/api/index';
import NoteListItem from '@/components/posts/NoteListItem.vue';
import LoadingSpinner from '@/components/common/LoadingSpinner.vue';

export default {
  components: { NoteListItem, LoadingSpinner },
  data() {
    return {
      listItem: [],
      isLoading: false,
    };
  },
  methods: {
    async getMyNotes() {
      this.isLoading = true;
      const { data } = await fetchNotes();
      this.isLoading = false;
      this.listItem = data.posts;
    },
  },
  created() {
    this.getMyNotes();
  },
};
</script>

<style></style>
