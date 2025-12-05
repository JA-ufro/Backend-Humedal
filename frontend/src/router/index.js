import { createRouter, createWebHistory } from "vue-router";
import Home from "@/views/Home.vue";
import Informacion from "@/views/Informacion.vue";
import Conservacion from "@/views/Conservacion.vue";
import HumedalDetalle from "@/views/HumedalDetalle.vue";
import AdminLogin from "@/views/AdminLogin.vue";
import AdminPanel from "@/views/AdminPanel.vue";
import AdminEditHumedal from "@/views/AdminEditHumedal.vue";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/informacion',
    name: 'Informacion',
    component: Informacion
  },
  {
    path: '/conservacion',
    name: 'Conservacion',
    component: Conservacion
  },
  {
    path: '/humedal/:id',
    name: 'HumedalDetalle',
    component: HumedalDetalle,
    props: true
  },
  {
    path: '/admin',
    name: '/AdminLogin',
    component: AdminLogin
  },
  {
    path: '/admin/panel',
    name: '/AdminPanel',
    component: AdminPanel
  },
  {
    path: '/admin/edit/:id',
    name: 'AdminEditHumedal',
    component: AdminEditHumedal,
    props: true
  }

]

const router = createRouter( {history : createWebHistory(), routes })
export default router