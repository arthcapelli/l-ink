import { ButtonFunction, ButtonLink } from "../../components";
import { ROUTES } from "../../../constants";
import { useGlobalUser } from "../../../context";
import { useHistory } from "react-router-dom";
import "./style.css";
import logo from "../../../assets/images/logo.png";
import logout from "../../../assets/icons/logout.png";
import create_post from "../../../assets/icons/add-post.png";

export function Header() {
  const [user, setUser] = useGlobalUser();
  const history = useHistory();

  function handleLogout() {
    setUser({});
    history.push(ROUTES.HOME);
  }

  function goHome() {
    history.push(ROUTES.HOME);
  }

  function goPost() {
    history.push(ROUTES.CREATE_POST);
  }

  return (
    <div className="header-content">
      <img onClick={goHome} src={logo} className="logo-header"></img>
      {user?.id ? (
        <div className="header-user">
          <img onClick={goPost} src={create_post} className="img-header"></img>
          <img onClick={handleLogout} src={logout} className="img-header"></img>
          <img src={user.avatar} className="avatar"></img>
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
  );
}
