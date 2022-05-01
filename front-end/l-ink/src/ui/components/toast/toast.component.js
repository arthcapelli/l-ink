import { useGlobalToastConfig } from "../../../context"
import { TOAST_TYPE } from "../../../constants"
import { useToast } from "../../../hooks"

import "./style.css"

const TYPE_CLASS = {
  [TOAST_TYPE.SUCCESS]: "toast--success",
  [TOAST_TYPE.ERROR]: "toast--error",
}

const Toast = () => {
  const [toastConfig] = useGlobalToastConfig()

  const { hideToast } = useToast()

  const typeClass = TYPE_CLASS[toastConfig.type]

  return toastConfig?.show ? (
    <div className={`toast ${typeClass}`}>
      <span onClick={hideToast}>X</span>
      {toastConfig.message}
    </div>
  ) : null
}

export { Toast }
