const { defineConfig } = require('@vue/cli-service')
module.exports= defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  devServer: {
    // port:8080,
    proxy: {
      "/api/v1": {
        target: "http://i7b206.p.ssafy.io",
        changeOrigin: true
      },
    },
  },

})