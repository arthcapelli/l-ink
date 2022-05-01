import { useGlobalShowLoader } from "../../../context"
// import { logo } from "../../assets"

import "./style.css"

const Loader = () => {
  const [showLoader] = useGlobalShowLoader()

  return showLoader ? (
    <div className="loader">
      {/* <img src={logo} className="loader-logo" alt="logo" /> */}
      CARREGANDO
    </div>
  ) : null
}

export { Loader }
