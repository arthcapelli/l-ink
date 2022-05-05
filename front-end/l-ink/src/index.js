import React from "react"
import ReactDOM from "react-dom"
import "./index.css"
import App from "./App"
import reportWebVitals from "./reportWebVitals"
import { BrowserRouter } from "react-router-dom"
import {
  GlobalShowLoaderProvider,
  GlobalToastConfigProvider,
  UserGlobalProvider,
} from "./context"

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <GlobalShowLoaderProvider>
        <GlobalToastConfigProvider>
          <UserGlobalProvider>
            <App />
          </UserGlobalProvider>
        </GlobalToastConfigProvider>
      </GlobalShowLoaderProvider>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById("root")
)

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals()
