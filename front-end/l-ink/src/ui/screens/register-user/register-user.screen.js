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
import {
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  Switch,
} from "@material-ui/core"
import "./style.css"
import logo from "../../../assets/images/logo.png"
import { useGlobalUser } from "../../../context"
import { UF } from "../../../constants/uf.constants"

export function RegisterUserScreen() {
  const { showErrorToast } = useToast()
  const [name, setName] = useState("")
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [confirmPassword, setConfirmPassword] = useState("")
  const [avatar, setAvatar] = useState("")
  const [phone, setPhone] = useState("")
  const [street, setStreet] = useState("")
  const [city, setCity] = useState("")
  const [uf, setUf] = useState("")
  const [location, setLocation] = useState("")
  const [isTattooArtist, setIsTattooArtist] = useState(false)
  const [expTime, setExpTime] = useState(0)
  const [userTags, setUserTags] = useState([])
  const [, setUser] = useGlobalUser()
  const { createUser, login } = useLinkApi()
  const { push } = useHistory()

  async function register() {
    if (
      !name.length ||
      !password.length ||
      !email.length ||
      userTags.length === 0
    ) {
      showErrorToast("Faltam dados para o cadastro. Preencha todos os campos!")
      return
    }

    if (isTattooArtist) {
      if (!expTime || !street.length || !city.length || !uf.length) {
        showErrorToast(
          "Faltam dados para o cadastro. Preencha todos os campos!"
        )
        return
      }
    }

    setLocation(`${street}-${city}-${uf}`)

    const response = await createUser(
      email,
      avatar,
      name,
      password,
      confirmPassword,
      isTattooArtist,
      expTime,
      phone,
      location,
      userTags
    )

    if (response) {
      const userResponse = await login(email, password)

      setUser(userResponse)

      push(ROUTES.HOME)
    }
  }

  function handleSubmit(event) {
    event.preventDefault()

    register()
  }

  function handleSelectChange(event) {
    setUf(event.target.value)
  }

  function goHome() {
    push(ROUTES.HOME)
  }

  return (
    <div className="register-user-screen-full">
      <div className="register-user-screen-bg container">
        <div className="register-user-screen">
          <div className="register-user-header">
            <p className="primary-color">Cadastro</p>
            <img
              src={logo}
              className="register-user-logo"
              onClick={goHome}
            ></img>
          </div>
          <form
            onSubmit={handleSubmit}
            className={
              isTattooArtist
                ? "register-user-content-artist"
                : "register-user-content"
            }
          >
            <Input
              value={name}
              onChange={setName}
              name="userName"
              label="Nome"
              inputProps={{ maxLength: 20 }}
            />

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

            <MultipleSelectChip setStyleTags={setUserTags} hideLoader={true} />

            <div className="register-user-select">
              <p className="register-user-select-label secondary-color">
                É tatuador?
              </p>
              <Switch
                checked={isTattooArtist}
                onChange={() => setIsTattooArtist(!isTattooArtist)}
                style={{ color: "#a68689" }}
              />
            </div>

            {isTattooArtist ? (
              <>
                <Input
                  value={expTime}
                  onChange={setExpTime}
                  name="expTime"
                  type="number"
                  label="Tempo de experiência (anos)"
                />
                <Input
                  value={phone}
                  onChange={setPhone}
                  name="phone"
                  label="Telefone"
                />
                <Input
                  value={street}
                  onChange={setStreet}
                  name="street"
                  label="Rua"
                />
                <Input
                  value={city}
                  onChange={setCity}
                  name="city"
                  label="Cidade"
                />
                <FormControl className="register-user-select-uf" fullWidth>
                  <InputLabel
                    id="uf-select-label"
                    style={{ marginLeft: "15px" }}
                  >
                    UF
                  </InputLabel>
                  <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={uf}
                    label="UF"
                    variant="outlined"
                    onChange={handleSelectChange}
                  >
                    {UF.map((item) => (
                      <MenuItem key={item} value={item}>
                        {" "}
                        {item}{" "}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
              </>
            ) : (
              ""
            )}

            <div className="register-user-screen-btns">
              <ButtonFunction
                name={"CADASTRAR"}
                onClick={handleSubmit}
              ></ButtonFunction>

              <div className="register-user-btn-label">
                <p className="register-user-login-label">Já possui cadastro?</p>
                <ButtonLink
                  isSecondary
                  path={ROUTES.LOGIN}
                  name="VOLTAR"
                ></ButtonLink>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}
