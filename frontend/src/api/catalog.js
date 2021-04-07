export default function (instance) {
    return {
        getCatalog(page = 0, size = 2, sort = 'productName') {
            return instance.get('catalog/?page=' + page + '&size=' + size + '&sort=' + sort + ',asc')
        },
        getCatalogWithFilters(searchText  = 'empty', sort = 'empty') {
            if (searchText !== 'empty' && sort !== 'empty') {
                return instance.get('catalog?searching=' + searchText + '&sort=' + sort + ',asc')
            }
            if (searchText !== 'empty' && sort === 'empty') {
                return instance.get('catalog?searching=' + searchText)
            }
            if (searchText === 'empty' && sort !== 'empty') {
                return instance.get('catalog?sort=' + sort)
            }
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