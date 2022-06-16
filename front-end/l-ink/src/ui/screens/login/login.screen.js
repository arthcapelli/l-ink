import { Input, ButtonFunction, ButtonLink } from "../../components"
import { useLinkApi } from "../../../api"
import { useState } from "react"
import { useHistory } from "react-router-dom"
import { ROUTES } from "../../../constants"
import { useToast } from "../../../hooks"
import { useGlobalUser } from "../../../context"
import "./style.css"
import logo from "../../../assets/images/logo.png"

export function LoginScreen() {
  const { showErrorToast } = useToast()
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [, setUser] = useGlobalUser()
  const history = useHistory()
  const { login } = useLinkApi()

  // Função para realizar o login do usuário, validando se o mesmo inseriu um email e senha, caso seja executada com sucesso
  // redireciona para a página de home com o usuário global populado
  async function loginApp() {
    if (!email.length || !password.length) {
      showErrorToast("Campos devem ser preenchidos")
      return
    }

    const userResponse = await login(email, password)

    if (userResponse) {
      setUser(userResponse)

      history.push(ROUTES.HOME)
    }
  }

  // Função que previne o comportamento padrão do formulário, que seria renderizar a página novamente, para que as informações
  // preenchidas possam ser utilizadas na função de login no app
  function handleSubmit(event) {
    event.preventDefault()

    loginApp()
  }

  return (
    <div className="login-screen-content ">
      <div className="login-screen container">
        <img src={logo} className="login-logo"></img>

        <form onSubmit={handleSubmit} className="login-content">
          <Input
            value={email}
            onChange={setEmail}
            name="userEmail"
            label="Email"
          />

          <Input
            value={password}
            onChange={setPassword}
            name="password"
            type="password"
            label="Senha"
          />

          <div>
            <ButtonFunction
              name={"ENTRAR"}
              onClick={handleSubmit}
            ></ButtonFunction>

            <div className="login-screen-buttons">
              <p>Ainda não possui cadastro?</p>
              <div>
                <ButtonLink
                  isSecondary
                  path={ROUTES.REGISTER_USER}
                  name="REGISTRE-SE"
                ></ButtonLink>
                <span className="font-size-adjust"> ou </span>
                <ButtonLink
                  isSecondary
                  path={ROUTES.HOME}
                  name="Página inicial"
                ></ButtonLink>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  )
}
