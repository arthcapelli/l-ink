import {
  Input,
  ButtonFunction,
  MultipleSelectChip,
  Header,
} from "../../components"
import { useLinkApi } from "../../../api"
import { useState } from "react"
import { useHistory } from "react-router-dom"
import { ROUTES } from "../../../constants"
import { useToast } from "../../../hooks"
import "./style.css"
import { useGlobalUser } from "../../../context"
import bg_image from "../../../assets/icons/bg-image.png"

export function CreatePostScreen() {
  const { showErrorToast, showSuccessToast } = useToast()
  const [postImg, setPostImg] = useState("")
  const [bodyLocal, setBodyLocal] = useState("")
  const [height, setHeight] = useState("")
  const [width, setWidth] = useState("")
  const [postTags, setPostTags] = useState([])
  const history = useHistory()
  const [user] = useGlobalUser()
  const { createPost } = useLinkApi()
  let measures = ""

  // Função para criação de post, que valida se o usário inseriu uma imagem e atribuiu tags ao mesmo e, em seguida, renderiza a página
  // de home
  async function create() {
    if (!postImg.length || postTags.length === 0) {
      showErrorToast("Insira uma imagem e tags para criar o post!")
      return
    }

    measures = `${height}x${width}`

    const response = await createPost(
      postImg,
      bodyLocal,
      measures,
      user.id,
      postTags
    )

    if (response.length) {
      showSuccessToast(response)
      history.push(ROUTES.HOME)
    }
  }

  // Função que previne o comportamento padrão do formulário, que seria renderizar a página novamente, para que as informações
  // preenchidas possam ser utilizadas na função de criação de post
  function handleSubmit(event) {
    event.preventDefault()

    create()
  }

  return (
    <>
      <Header />
      <div className="create-post-screen">
        <img
          className="create-post-img margin-default"
          src={postImg || bg_image}
        ></img>
        <div className="container create-post-content">
          <form onSubmit={handleSubmit} className="create-post-form">
            <div className="create-post-form-input">
              <Input
                value={postImg}
                inputProps={{ maxLength: "500" }}
                onChange={setPostImg}
                name="postImg"
                label="Imagem"
              />
            </div>
            <div className="create-post-form-input">
              <Input
                value={bodyLocal}
                onChange={setBodyLocal}
                inputProps={{ maxLength: "20" }}
                name="bodyLocal"
                label="Local do Corpo"
              />
            </div>
            <div className="create-post-middle-measures">
              <Input
                style={{ width: 145 }}
                value={height}
                onChange={setHeight}
                inputProps={{ maxLength: "3" }}
                name="height"
                label="Altura"
              />
              <Input
                style={{ width: 145 }}
                value={width}
                onChange={setWidth}
                inputProps={{ maxLength: "3" }}
                name="width"
                label="Largura"
              />
            </div>

            <MultipleSelectChip setStyleTags={setPostTags} hideLoader={true} />

            <div>
              <ButtonFunction
                name={"POSTAR"}
                onClick={handleSubmit}
              ></ButtonFunction>
            </div>
          </form>
        </div>
      </div>
    </>
  )
}
