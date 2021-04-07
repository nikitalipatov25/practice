<template>
  <div class="custom-nav-bar">
    <input type="text" placeholder="Поиск" v-model="searchText">
    <b-button variant="primary" @click="searchProducts">Поиск</b-button>
    <b-button variant="success" @click="sotrProducts('productName')">Название</b-button> | <b-button variant="success" @click="sotrProducts('productPrice')">Цена</b-button>
  </div>
</template>

<script>
import { eventBus } from '../main'

export default {
  data() {
    return {
      searchText: '',
      sort: '',
      previousSort: ''
    }
  },
  methods: {
    searchProducts() {
      eventBus.$emit('searchProducts', this.searchText);
    },
    sotrProducts(sortParameter) {
      if (this.sort === '') {
        this.sort = sortParameter;
      } else if (this.previousSort !== sortParameter) {
        this.sort = sortParameter;
      } else {
        this.sort = '';
      }
      eventBus.$emit('sortProducts', this.searchText, this.sort);
      this.previousSort = sortParameter;
    }
  }
}
</script>