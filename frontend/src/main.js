import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyD3UpLAmPUjGLgXmF90qWqsMx-epM5Z3EA",
  authDomain: "wefit-e5b96.firebaseapp.com",
  databaseURL: "https://wefit-e5b96-default-rtdb.asia-southeast1.firebasedatabase.app",
  projectId: "wefit-e5b96",
  storageBucket: "wefit-e5b96.appspot.com",
  messagingSenderId: "705256829131",
  appId: "1:705256829131:web:422d37bd5a626721a7eb4e",
  measurementId: "G-7VHR47GGGF"
};

window.Kakao.init('1337aaeee72289e0725aed7e44932576');

// Initialize Firebase
const firebaseApp = initializeApp(firebaseConfig);
const analytics = getAnalytics(firebaseApp);
const app = createApp(App)

  
app.use(store).use(router).mount('#app')
