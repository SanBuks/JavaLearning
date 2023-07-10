// 将配置项 与 webpack.config.js 中的配置合并
const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({ // defineConfig 提供语义支持
  transpileDependencies: true,
  lintOnSave: 'warning',        // 设置语法检查等级
  pages: {
    index: {
      entry: 'src/main.js',     // page 的入口
    }
  }
})
