import { useGlobalShowLoader } from "../../../context"
import { CircularProgress } from "@mui/material/"

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
