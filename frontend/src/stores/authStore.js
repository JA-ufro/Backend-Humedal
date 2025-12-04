import { ref, computed } from 'vue'

const token = ref(localStorage.getItem('token') || null)

export function useAuthStore() {
  const isLoggedIn = computed(() => !!token.value)

  function setToken(newToken) {
    token.value = newToken
    if (newToken) {
      localStorage.setItem('token', newToken)
    } else {
      localStorage.removeItem('token')
    }
  }

  return {
    token,
    isLoggedIn,
    setToken,
  }
}