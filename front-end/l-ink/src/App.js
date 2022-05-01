import { Route, Switch } from "react-router-dom"
import "./App.css"
import { ROUTES } from "./constants/index"
import { Loader } from "./ui/components"
import { Toast } from "./ui/components/toast/toast.component"
import { RegisterUserScreen } from "./ui/screens/index"

function App() {
  return (
    <div className="App">
      <Switch>
        <Route path={ROUTES.REGISTER_USER} exact>
          <RegisterUserScreen />
        </Route>
      </Switch>
      <Loader />
      <Toast />
    </div>
  )
}

export default App
