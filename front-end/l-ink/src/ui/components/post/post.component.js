import "./style.css"
import { Favorite } from "../favorite/favorite.component"
import { useState } from "react"
import { useGlobalUser } from "../../../context"
import { useHistory } from "react-router-dom"
import { getDate, getBrazilianTime } from "../../../utils/utils"
import machine from "../../../assets/icons/machine.png"

export function Post({ item }) {
  const { id, userResponse, postImg, isFavorite, createdAt } = item
  const [user] = useGlobalUser()
  const [favorite, setFavorite] = useState(isFavorite)
  const { push } = useHistory()

  // Função para renderizar a página do post passando o id respectivo
  function handleClickPost() {
    push(`/post/${id}`)
  }

  // Função para renderizar a página de perfil do usuário passando o id respectivo
  function handleClickUser() {
    push(`/profile/${userResponse.id}`)
  }

  return (
    <div className="feed-card-content" key={id}>
      <div>
        <img
          className="card-img"
          src={postImg}
          alt={userResponse.name}
          onClick={handleClickPost}
        ></img>
      </div>
      <div className="feed-card-info">
        <div className="feed-card-author" onClick={handleClickUser}>
          <div className="post-author-img">
            <img
              src={userResponse.avatar}
              alt="avatar"
              className="avatar-img"
            ></img>
          </div>
          <div className="feed-details">
            <div className="post-author-name">{userResponse.name}</div>
            <div className="post-date">
              {getDate(createdAt)} - {getBrazilianTime(createdAt)}
            </div>
          </div>
        </div>
        <div className="feed-card-icons">
          {userResponse.tattooArtist && (
            <img src={machine} className="is-artist-symbol"></img>
          )}
          {user?.id && (
            <Favorite
              postId={id}
              isFavorite={favorite}
              setIsFavorite={setFavorite}
            />
          )}
        </div>
      </div>
    </div>
  )
}
