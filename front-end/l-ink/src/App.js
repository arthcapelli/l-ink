import { Route, Switch } from "react-router-dom"
import "./App.css"
import { ROUTES } from "./constants/index"
import { Loader } from "./ui/components"
import { Toast } from "./ui/components/toast/toast.component"
import { RegisterUserScreen } from "./ui/screens/index"
import { createTheme, ThemeProvider } from "@material-ui/core/styles"

function App() {
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
        </Switch>
        <Loader />
        <Toast />
      </div>
    </ThemeProvider>
  )
}

export default App
