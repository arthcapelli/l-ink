import axios from "axios"
import { useLoader, useToast } from "../hooks"
import { API_URL, API_METHOD } from "../constants"
import { useGlobalUser } from "../context"
import { useCallback } from "react"

const axiosInstance = axios.create({
  baseURL: API_URL,
})

const useAxios = () => {
  const { showErrorToast } = useToast()
  const { showLoader, hideLoader } = useLoader()
  const [user] = useGlobalUser()

  const callApi = async (method, url, data, hideLoading, showLoading) => {
    const requestConfig = {
      method,
      url,
      data,
      headers: {
        authorization: user.token,
      },
    }

    try {
      if (!showLoading) {
        showLoader()
      }
      const { data } = await axiosInstance.request(requestConfig)
      return data
    } catch (requestError) {
      hideLoader()
      showErrorToast(requestError.response.data.message)
    } finally {
      if (hideLoading) {
        hideLoader()
      }
    }
  }

  const get = (url, hideLoading, showLoading) => {
    return callApi(API_METHOD.GET, url, "", hideLoading, showLoading)
  }

  const post = (url, data, hideLoading, showLoading) => {
    return callApi(API_METHOD.POST, url, data, hideLoading, showLoading)
  }

  const put = (url, data, hideLoading, showLoading) => {
    return callApi(API_METHOD.PUT, url, data, hideLoading, showLoading)
  }

  const del = (url, hideLoading, showLoading) => {
    return callApi(API_METHOD.DELETE, url, "", hideLoading, showLoading)
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
