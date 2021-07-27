import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Patients from '../views/Patients.vue'
import Medications from '../views/Medications.vue'
import MedicationPlans from '../views/MedicationPlans.vue'
import Caregivers from '../views/Caregivers.vue'
import LogIn from '../views/LogIn.vue'
import SignUp from '../views/SignUp.vue'
import Profile from '../views/Profile.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/patients',
    name: 'Patients',
    component: Patients
  },
  {
    path: '/patients/:id',
    name: 'Patient',
    component: () => import("../components/Patient.vue")
  },
  {
    path: '/caregivers',
    name: 'Caregivers',
    component: Caregivers
  },
  {
    path: '/caregivers/:id',
    name: 'Caregiver',
    component: () => import("../components/Caregiver.vue")
  },
  {
    path: '/medications',
    name: 'Medications',
    component: Medications
  },
  {
    path: '/medications/:id',
    name: 'Medication',
    component: () => import("../components/Medication.vue")
  },
  {
    path: '/medicationplans/:id',
    name: 'MedicationPlans',
    component: MedicationPlans
  },
  {
    path: '/medicationplans/add/:id',
    name: 'MedicationPlan',
    component: () => import("../components/MedicationPlan.vue")
  },
  {
    path: '/login',
    name: 'LogIn',
    component: LogIn
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUp
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
