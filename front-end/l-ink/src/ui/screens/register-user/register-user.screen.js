import {
  Input,
  ButtonFunction,
  ButtonLink,
  MultipleSelectChip,
} from "../../components"
import { useLinkApi } from "../../../api"
import { useState } from "react"
import { useHistory } from "react-router-dom"
import { ROUTES } from "../../../constants"
import { useToast } from "../../../hooks"
import Switch from "@material-ui/core/Switch"
import "./style.css"
import logo from "../../../assets/images/logo.png"

export function RegisterUserScreen() {
  const { showErrorToast } = useToast()
  const [name, setName] = useState("")
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [confirmPassword, setConfirmPassword] = useState("")
  const [avatar, setAvatar] = useState("")
  const [isTattooArtist, setIsTattooArtist] = useState(false)
  const [userTags, setUserTags] = useState([])
  const history = useHistory()
  const { createUser } = useLinkApi()

  async function register() {
    if (!name.length || !password.length) {
      showErrorToast(
        "Faltam dados para o cadastro, escolha uma senha e um nome de usuário"
      )
      return
    }

    await createUser(
      email,
      avatar,
      name,
      password,
      confirmPassword,
      isTattooArtist,
      userTags
    )
  }

  function handleSubmit(evento) {
    evento.preventDefault()

    register()
  }

  return (
    <div className="register-user-screen container">
      <div className="register-user-header">
        <p className="primary-color">Cadastro</p>
        <img src={logo} className="register-user-logo"></img>
      </div>
      <form onSubmit={handleSubmit} className="register-user-content">
        <Input value={name} onChange={setName} name="userName" label="Nome" />

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

        <Input
          value={confirmPassword}
          onChange={setConfirmPassword}
          name="confirmPassword"
          type="password"
          label="Confirmação de senha"
        />

        <Input
          value={avatar}
          onChange={setAvatar}
          name="avatar"
          label="Imagem de perfil (url)"
        />

        <MultipleSelectChip setUserTags={setUserTags} />

        <div className="register-user-select-label">
          <p className="secondary-color">É tatuador?</p>
          <Switch
            checked={isTattooArtist}
            onChange={() => setIsTattooArtist(!isTattooArtist)}
            style={{ color: "#a68689" }}
          />
        </div>

        <div>
          <ButtonFunction
            name={"CADASTRAR"}
            onClick={handleSubmit}
          ></ButtonFunction>
          <ButtonLink
            disabled={true}
            path={ROUTES.LOGIN}
            name="VOLTAR"
          ></ButtonLink>
        </div>
      </form>
    </div>
  )
}