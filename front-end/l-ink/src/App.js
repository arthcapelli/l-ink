import { useEffect } from "react"
import { Route, Switch, Redirect, useHistory } from "react-router-dom"
import "./App.css"
import { ROUTES } from "./constants/index"
import { Loader, Toast } from "./ui/components"
import { RegisterUserScreen, LoginScreen, HomeScreen } from "./ui/screens/index"
import { createTheme, ThemeProvider } from "@material-ui/core/styles"
import { useGlobalUser } from "./context"

function PrivateRoute({ path, children }) {
  const [user] = useGlobalUser()

  if (!user?.id) {
    return <Redirect to={ROUTES.LOGIN} />
  }

  return (
    <Route path={path} exact>
      {children}
    </Route>
  )
}

function App() {
  const [user, setUser] = useGlobalUser()

  useEffect(() => {
    localStorage.setItem("user", JSON.stringify(user))
  }, [user])

  const theme = createTheme({
    palette: {
      primary: {
        main: "#59081e",
      },
      secondary: {
        main: "#a68689",
      },
    },
  })
  return (
    <ThemeProvider theme={theme}>
      <div className="App">
        <Switch>
          <Route path={ROUTES.REGISTER_USER} exact>
            <RegisterUserScreen />
          </Route>
          <Route path={ROUTES.LOGIN} exact>
            <LoginScreen />
          </Route>
          <Route path={ROUTES.HOME} exact>
            <HomeScreen />
          </Route>
          <Route path="/">
            <Redirect to="/" />
          </Route>
        </Switch>
        <Loader />
        <Toast />
      </div>
    </ThemeProvider>
  )
}

export default App
