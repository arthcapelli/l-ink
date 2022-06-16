import { ButtonLink } from "../../components"
import { ROUTES } from "../../../constants"
import { useGlobalUser } from "../../../context"
import { useHistory } from "react-router-dom"
import "./style.css"
import logo from "../../../assets/images/logo.png"
import logout from "../../../assets/icons/logout.png"
import create_post from "../../../assets/icons/add-post.png"

export function Header() {
  const [user, setUser] = useGlobalUser()
  const { push } = useHistory()

  // Função que limpa o usuário global e renderiza a página de home
  function handleLogout() {
    setUser({})
    push(ROUTES.HOME)
  }

  // Função para renderizar a página de home
  function goHome() {
    push(ROUTES.HOME)
  }

  // Função que renderiza a página para criação de post
  function goPost() {
    push(ROUTES.CREATE_POST)
  }

  // Função que renderiza a página de perfil do usuário logado
  function handleClickUser() {
    push(`/profile/${user.id}`)
  }

  return (
    <div className="header-content">
      <img onClick={goHome} src={logo} className="logo-header"></img>
      {user?.id ? (
        <div className="header-user">
          <img onClick={goPost} src={create_post} className="img-header"></img>
          <img onClick={handleLogout} src={logout} className="img-header"></img>
          <img
            onClick={handleClickUser}
            src={user.avatar}
            className="avatar"
          ></img>
        </div>
      ) : (
        <div className="header-anonymous">
          <ButtonLink
            isSecondary={true}
            path={ROUTES.REGISTER_USER}
            name="Cadastre-se"
          ></ButtonLink>
          <ButtonLink
            isSecondary={true}
            path={ROUTES.LOGIN}
            name="Entrar"
          ></ButtonLink>
        </div>
      )}
    </div>
  )
}
