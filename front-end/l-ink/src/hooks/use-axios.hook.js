import axios from "axios";
import { useLoader, useToast } from "../hooks";
import { API_URL, API_METHOD } from "../constants";
import { useGlobalUser } from "../context";
import { useCallback } from "react";

const axiosInstance = axios.create({
  baseURL: API_URL,
});

const useAxios = () => {
  const { showErrorToast } = useToast();
  const { showLoader, hideLoader } = useLoader();
  const [user] = useGlobalUser();

  const callApi = async (method, url, data, hideLoading) => {
    const requestConfig = {
      method,
      url,
      data,
      headers: {
        authorization: user.token,
      },
    };

    try {
      showLoader();
      const { data } = await axiosInstance.request(requestConfig);
      return data;
    } catch (requestError) {
      console.log(requestError);
      showErrorToast(requestError.response.data.message);
    } finally {
      if (hideLoading) {
        hideLoader();
      }
    }
  };

  const get = (url, showLoading) => {
    return callApi(API_METHOD.GET, url, "", showLoading);
  };

  const post = (url, data, showLoading) => {
    return callApi(API_METHOD.POST, url, data, showLoading);
  };

  const put = (url, data, showLoading) => {
    return callApi(API_METHOD.PUT, url, data, showLoading);
  };

  const del = (url, showLoading) => {
    return callApi(API_METHOD.DELETE, url, "", showLoading);
  };

  return useCallback(
    {
      get,
      post,
      put,
      del,
    },
    [user]
  );
};

export { useAxios };
