import { useGlobalShowLoader } from "../../../context"
import { CircularProgress } from "@material-ui/core"

import "./style.css"

const Loader = () => {
  const [showLoader] = useGlobalShowLoader()

  return showLoader ? (
    <div className="loader">
      <CircularProgress />
    </div>
  ) : null
}

export { Loader }
