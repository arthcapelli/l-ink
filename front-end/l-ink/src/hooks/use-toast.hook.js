import { useGlobalToastConfig } from "../context";
import { TOAST_TYPE, TOAST_MESSAGE } from "../constants";

let timeoutID;

const useToast = () => {
  const [, setToastConfig] = useGlobalToastConfig();

  const hideToast = () => {
    setToastConfig((config) => ({ ...config, show: false }));
  };

  const showToast = ({ type, message }) => {
    setToastConfig((config) => ({ ...config, type, message, show: true }));
    clearTimeout(timeoutID);
    timeoutID = setTimeout(hideToast, 5000);
  };

  const showSuccessToast = (message) => {
    showToast({
      type: TOAST_TYPE.SUCCESS,
      message: message || TOAST_MESSAGE.SUCCESS,
    });
  };

  const showErrorToast = (message) => {
    showToast({
      type: TOAST_TYPE.ERROR,
      message: message || TOAST_MESSAGE.ERROR,
    });
  };

  return {
    showSuccessToast,
    showErrorToast,
    hideToast,
  };
};

export { useToast };
