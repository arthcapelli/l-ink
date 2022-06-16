import "./style.css"
import { useLinkApi } from "../../../api"
import { useGlobalUser } from "../../../context"
import heart_blank from "../../../assets/icons/heart-blank.png"
import heart_fill from "../../../assets/icons/heart-fill.png"

export function Favorite({ postId, isFavorite, setIsFavorite }) {
  const [user] = useGlobalUser()
  const { favoritePost } = useLinkApi()

  // Função que é chamada no clique do usuário, para executar a função de favoritar o post
  async function handleClick() {
    await favorite()
  }

  // Função que favorita o post passando o id do mesmo e do usuário logado
  async function favorite() {
    setIsFavorite(!isFavorite)
    await favoritePost(postId, user.id)
  }

  return (
    <>
      {isFavorite ? (
        <img
          src={heart_fill}
          alt="heart-icon"
          className="icon-img"
          onClick={handleClick}
        ></img>
      ) : (
        <img
          src={heart_blank}
          alt="heart-icon"
          className="icon-img"
          onClick={handleClick}
        ></img>
      )}
    </>
  )
}
