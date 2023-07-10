import { commonMounted } from '@/mixin'

export default {
  install(Vue) {
    console.log('@@' + '开始增强')
    Vue.mixin(commonMounted)
    Vue.prototype.hello = () => {alert('原型方法')}
  },
}
