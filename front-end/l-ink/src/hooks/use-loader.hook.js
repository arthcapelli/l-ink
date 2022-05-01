import { useGlobalShowLoader } from "../context"

const useLoader = () => {
  const [, setShowLoader] = useGlobalShowLoader()

  const showLoader = () => {
    setShowLoader(true)
  }

  const hideLoader = () => {
    setTimeout(() => setShowLoader(false), 400)
  }

  return {
    showLoader,
    hideLoader,
  }
}

export { useLoader }
