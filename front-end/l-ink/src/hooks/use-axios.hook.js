import axios from "axios"
import { useLoader, useToast } from "../hooks"
import { API_URL, API_METHOD } from "../constants"
import { useGlobalUser } from "../context"
import { useCallback } from "react"

const axiosInstance = axios.create({
  baseURL: API_URL,
})

const useAxios = () => {
  const { showSuccessToast, showErrorToast } = useToast()
  const { showLoader, hideLoader } = useLoader()
  const [user] = useGlobalUser()

  const callApi = async (method, url, data) => {
    const requestConfig = {
      method,
      url,
      data,
      headers: {
        authorization: user.token,
      },
    }

    try {
      showLoader()
      const { data } = await axiosInstance.request(requestConfig)
      showSuccessToast()
      return data
    } catch (requestError) {
      console.log(requestError)
      showErrorToast(requestError.response.data.message)
    } finally {
      hideLoader()
    }
  }

  const get = (url) => {
    return callApi(API_METHOD.GET, url)
  }

  const post = (url, data) => {
    return callApi(API_METHOD.POST, url, data)
  }

  const put = (url, data) => {
    return callApi(API_METHOD.PUT, url, data)
  }

  const del = (url) => {
    return callApi(API_METHOD.DELETE, url)
  }

  return useCallback(
    {
      get,
      post,
      put,
      del,
    },
    [user]
  )
}

export { useAxios }
