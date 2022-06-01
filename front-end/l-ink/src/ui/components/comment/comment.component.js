import "./style.css"

export function Comment({ item }) {
  const { userResponse, commentText } = item
  return (
    <div className="comment-content">
      <img className="comment-user-avatar" src={userResponse.avatar}></img>
      <div className="comment-user-text">
        <span className="comment-user-name">{userResponse.name}</span>
        <span className="comment-user-message">{commentText}</span>
      </div>
    </div>
  )
}
