<template>
  <div class="catalog">
    <div class="footer">
      <h1>Каталог товаров</h1>
        <b-button variant="primary" @click="$bvModal.show('add-modal')" >Добавить товар</b-button>
    </div>
    <div class="body">
      <div class="row">
        <div class="col-2">
        </div>
        <div class="col-1">
          Категория товара
        </div>
        <div class="col-1">
          Тип товара
        </div>
        <div class="col-2">
          Изображение товара
        </div>
        <div class="col-2">
          Название товара
        </div>
        <div class="col-1">
          Стоимость товара
        </div>
        <div class="col-1">
          Количество товара на складе
        </div>
        <div class="col-2">
          <nav-bar/>
        </div>
      </div>
      <hr>
      <product-component
        v-for="product in products"
        :key="product.id"
        :product="product"
      />
      <div class="pagination-component">
        <pagination-component/>
      </div>
      <div>
        <b-modal id="add-modal" hide-footer title="Добавить товар">
          <div class="d-block text-left">
            <p>Введите категорию товара: <input type="text" v-model="newProduct.categoryType"/></p>
            <p>Введите тип товара: <input type="text" v-model="newProduct.categoryName"/></p>
            <p>Введите название товара: <input type="text" v-model="newProduct.productName"/></p>
            <p>Введите стоимость товара: <input type="text" v-model="newProduct.productPrice"/></p>
            <p>Введите описание товара: <input type="text" v-model="newProduct.productDescription"/></p>
            <p>Добавьте фотографию товара: <input type="text" v-model="newProduct.productPhoto"/></p>
            <p>Введите количество товара: <input type="text" v-model="newProduct.productKol"/></p>
          </div>
          <b-button @click="createCatalogItem" variant="success">Добавить товар</b-button>
          <b-button @click="$bvModal.hide('add-modal')" variant="danger">Закрыть</b-button>
        </b-modal>
      </div>
      <div>
        <b-modal id="edit-modal" hide-footer title="Изменить товар">
          <div class="d-block text-left">
            <p>Измените категорию товара: <input type="text" v-model="editProduct.categoryType"/></p>
            <p>Измените тип товара: <input type="text" v-model="editProduct.categoryName"/></p>
            <p>Измените название товара: <input type="text" v-model="editProduct.productName"/></p>
            <p>Измените стоимость товара: <input type="text" v-model="editProduct.productPrice"/></p>
            <p>Измените описание товара: <input type="text" v-model="editProduct.productDescription"/></p>
            <p>Измените фотографию товара: <input type="text" v-model="editProduct.productPhoto"/></p>
            <p>Измените количество товара: <input type="text" v-model="editProduct.productKol"/></p>
          </div>
          <b-button @click="editCatalogItem" variant="success">Изменить товар</b-button>
          <b-button @click="$bvModal.hide('edit-modal')" variant="danger">Закрыть</b-button>
        </b-modal>
      </div>
    </div>
  </div>
</template>

<script>
import productComponent from '../components/productComponent'
import navBar from '../components/navBar'
import paginationComponent from '../components/paginationComponent'
import { eventBus } from '../main'

export default {
  name: 'Catalog',
  components: {
    productComponent,
    navBar,
    paginationComponent
  },
  data() {
    return {
      editingProductUUID: '',
      editProduct: {},
      products: [],
      productsFromServer: {},
      newProduct: {
        categoryType: '',
        categoryName: '',
        productName: '',
        productPrice: 0.1,
        productDescription: '',
        productPhoto: '',
        productKol: 0
      }
    }
  },
  methods: {
    async getCatalog(currentPage) {
      this.productsFromServer = await this.$api.catalog.getCatalog(currentPage,2);
      this.products = await this.productsFromServer.data.content;
      console.log(this.products);
    },
    async getCatalogWithFilters(searchText, sortType) {
        this.productsFromServer = await this.$api.catalog.getCatalogWithFilters(searchText, sortType);
        this.products = await this.productsFromServer.data.content;
    },
    openEditModal(id) {
      this.editProduct = this.products.find(item => item.id === id);
      this.editingProductUUID = id;
      this.$bvModal.show('edit-modal');
    },
    async editCatalogItem() {
      await this.$api.catalog.editCatalogItem(this.editingProductUUID, this.editProduct);
      this.$bvModal.hide('edit-modal');
      await this.$api.catalog.getCatalog();
    },
    async createCatalogItem() {
      await this.$api.catalog.createCatalogItem(this.newProduct);
      this.$bvModal.hide('add-modal');
      this.productsFromServer = await this.$api.catalog.getCatalog();
      this.products = await this.productsFromServer.data.content;
    }
  },
  created() {
    this.getCatalog();
    eventBus.$on('deleteItem', this.getCatalog);
    eventBus.$on('editItem', this.openEditModal);
    eventBus.$on('searchProducts', this.getCatalogWithFilters);
    eventBus.$on('sortProducts', this.getCatalogWithFilters);
    eventBus.$on('changePage', this.getCatalog)
  }
}
</script>

<style>
.footer {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}
.col-2 {
  text-align: center;
}
.col-1 {
  text-align: center;
}
.pagination-component {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}
</style>


