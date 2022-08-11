import { createRouter, createWebHistory } from 'vue-router'
import NonLoginView from '@/views/MainPage/NonLoginHome.vue'
import LoginHome from '@/views/MainPage/LoginHome.vue'
import LoginView from '@/views/Accounts/LoginView.vue'
import SignupView from '@/views/Accounts/SignupView.vue'
import SignUpByEmail from '@/views/Accounts/SignupByEmailView.vue'
import SigupDetail from '@/views/Accounts/SignupDetail.vue'
import ProfileView from '@/views/Accounts/ProfileView.vue'
import RandomVideo from '@/views/Video/RandomVideo.vue'
import store from '@/store/modules/accounts'

const routes = [
  {
    path: '/',
    name: 'NonLoginView',
    component: NonLoginView,
  },
  {
    path: '/Home',
    name: 'LoginHome',
    component: LoginHome,
  },
  {
    path: '/login',
    name: 'LoginView',
    component: LoginView,
  },
  {
    path: '/signup',
    name: 'SignupView',
    component: SignupView,
  },
  {
    path: '/signupbyemail',
    name: 'SignUpByEmail',
    component: SignUpByEmail,
  },
  {
    path: '/signupdetail',
    name: 'SigupDetail',
    component: SigupDetail,
  },
  {
    path: '/profile/:username',
    name: 'ProfileView',
    component: ProfileView,
  },
  {
    path: '/randomvideo',
    name: 'RandomVideo',
    component: RandomVideo,
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})


router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('accessToken')
  const noAuthPages = ['NonLoginView' ,'LoginView', 'SignupView','SigupDetail','SignUpByEmail']
  if (noAuthPages.includes(to.name)) {
    if (token) {
      next({ name: 'HomeView' })
    }
  } else {
    if (!token) {
      alert('로그인해라')
      next({ name: 'LoginView' })
    }
  }
  next()
})

export default router
