import "./style.css"
import { getDate, getBrazilianTime } from "../../../utils/utils"
import { useHistory } from "react-router-dom"

export function Comment({ item }) {
  const { push } = useHistory()
  const { userResponse, commentText } = item

  function handleClickUser() {
    push(`/profile/${userResponse.id}`)
  }

  return (
    <div className="comment-content">
      <div className="comment-content-img">
        <img
          className="comment-user-avatar"
          src={userResponse.avatar}
          onClick={handleClickUser}
        ></img>
      </div>
      <div className="comment-user-text">
        <span className="comment-user-name" onClick={handleClickUser}>
          {userResponse.name}
        </span>
        <span className="comment-user-message">{commentText}</span>
      </div>
      <div className="comment-user-date">
        <span>{getDate(item.createdAt)}</span>{" "}
        <span>{getBrazilianTime(item.createdAt)}</span>
      </div>
    </div>
  )
}
