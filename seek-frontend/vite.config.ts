import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import Components from 'unplugin-vue-components/vite';
import { VantResolver } from 'unplugin-vue-components/resolvers';
import dns from "dns"
dns.setDefaultResultOrder("verbatim")

// https://vitejs.dev/config/
export default defineConfig({
    server: {
        host: "localhost",
        port: 5000,
    },
    plugins: [
      vue(),
      Components({
          resolvers: [VantResolver()],
      }),
    ],
})


