import instance from './instance'

import catalogModule from './catalog'

export default {
    catalog: catalogModule(instance)
}