import "./style.css";

export function Comment({ item }) {
  const { id, userResponse, commentText } = item;
  return (
    <div className="comment-content">
      <img className="comment-user-avatar" src={userResponse.avatar}></img>
      <div className="comment-user-text">
        <span>{userResponse.name}</span>
        <span>{commentText}</span>
      </div>
    </div>
  );
}
