<template>
  <nav aria-label="Page navigation example">
    <ul class="pagination">
      <li @click="changePageNoIndex('first')" class="page-item"><a class="page-link" >&laquo;</a></li>
      <li @click="changePageNoIndex('previous')" class="page-item"><a class="page-link" >Предыдущая</a></li>
      <li @click="changePage(pageIndex)" class="page-item" v-for="pageIndex in totalPages" :key="pageIndex"><a class="page-link">{{ pageIndex }}</a></li>
      <li @click="changePageNoIndex('next')" class="page-item"><a class="page-link" >Следующая</a></li>
      <li @click="changePageNoIndex('last')" class="page-item"><a class="page-link" >&raquo;</a></li>
    </ul>
  </nav>
</template>

<script>
import { eventBus } from '../main'

export default {
  data() {
    return {
      totalPages: 0,
      currentPage: 0,
      pageSize: 0
    }
  },
  methods: {
    async countPages() {
      let fromServer = await this.$api.catalog.getCatalog(0,2);
      this.totalPages = fromServer.data.totalPages;
    },
    async changePage(pageIndex) {
      this.currentPage = pageIndex - 1;
      eventBus.$emit('changePage', this.currentPage);
    },
    async changePageNoIndex(parameter) {
      switch(parameter) {
        case 'first':
          this.currentPage = 0;
          break;
        case 'previous':
          this.currentPage = this.currentPage - 1;
          break;
        case 'next':
          this.currentPage = this.currentPage + 1;
          break;
        case 'last':
          this.currentPage = this.totalPages - 1;
          break;
      }
      eventBus.$emit('changePage', this.currentPage);
    }
  },
  created() {
    this.countPages();
  }
}
</script>