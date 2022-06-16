import { useEffect } from "react"
import { Route, Switch, Redirect } from "react-router-dom"
import "./App.css"
import { ROUTES } from "./constants/index"
import { Loader, Toast } from "./ui/components"
import {
  RegisterUserScreen,
  LoginScreen,
  HomeScreen,
  CreatePostScreen,
  PostScreen,
  ProfileScreen,
} from "./ui/screens/index"
import { createTheme, ThemeProvider } from "@mui/material/styles"
import { useGlobalUser } from "./context"

// Função que permite a renderização de páginas caso o usuário esteja logado, senão o mesmo será redirecionado à página de login
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
  const [user] = useGlobalUser()

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
          <Route path={ROUTES.LOGIN} exact>
            <LoginScreen />
          </Route>
          <Route path={ROUTES.REGISTER_USER} exact>
            <RegisterUserScreen />
          </Route>
          <Route path={ROUTES.HOME} exact>
            <HomeScreen />
          </Route>
          <Route path={ROUTES.PROFILE} exact>
            <ProfileScreen />
          </Route>
          <PrivateRoute path={ROUTES.CREATE_POST} exact>
            <CreatePostScreen />
          </PrivateRoute>
          <PrivateRoute path={ROUTES.POST_PAGE} exact>
            <PostScreen />
          </PrivateRoute>
          <Route path="/">
            <Redirect to={ROUTES.HOME} />
          </Route>
        </Switch>
        <Loader />
        <Toast />
      </div>
    </ThemeProvider>
  )
}

export default App
