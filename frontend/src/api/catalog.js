export default function (instance) {
    return {
        getCatalog() {
            return instance.get('catalog')
        },
        getCatalogItemByUUID(UUID) {
            return instance.get('catalog/' + UUID)
        },
        createCatalogItem(payload) {
            return instance.post('catalog/', payload)
        },
        editCatalogItem(UUID, payload) {
            return instance.put('catalog/' + UUID, payload)
        },
        deleteCatalogItem(UUID) {
            return instance.delete('catalog/' + UUID)
        }
    }
}