import "./style.css"
import heart_blank from "../../../assets/icons/heart-blank.png"

export function Post({ item }) {
  const { id, userResponse, postImg } = item

  return (
    <div className="feed-card-content" key={id}>
      <div>
        <img className="card-img" src={postImg} alt={userResponse.name}></img>
      </div>
      <div className="feed-card-info">
        <div className="feed-card-author">
          <img
            src={userResponse.avatar}
            alt="avatar"
            className="avatar-img"
          ></img>
          <div>{userResponse.name}</div>
        </div>
        <img src={heart_blank} alt="heart-icon" className="icon-img"></img>
      </div>
    </div>
  )
}
