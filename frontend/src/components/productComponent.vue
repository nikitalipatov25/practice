<template>
  <div class="row">
    <div class="col-2">

    </div>
    <div class="col-1">
      {{ product.categoryType }}
    </div>
    <div class="col-1">
      {{ product.categoryName }}
    </div>
    <div class="col-2">
      <img
        :src="product.productPhoto"
        width="100px"
        height="100px"
        alt=""
      >
    </div>
    <div class="col-2">
      {{ product.productName }}
    </div>
    <div class="col-1">
      {{ product.productPrice }}
    </div>
    <div class="col-1">
      {{ product.productKol }}
    </div>
    <div class="col-2">
      <button type="button" class="btn btn-danger" v-on:click="onClickDelete">Удалить</button>
      <button type="button" class="btn btn-warning" v-on:click="onClickEdit">Редактировать</button>
    </div>
  </div>
</template>

<script>
import { eventBus } from '../main'

export default {
  props: {
    product: {
      type: Object
    }
  },
  methods: {
    async onClickDelete() {
      await this.$api.catalog.deleteCatalogItem(this.product.id);
      eventBus.$emit("deleteItem");
    },
      async onClickEdit() {
      await eventBus.$emit("editItem", this.product.id);
    }
  }
}
</script>
