import "./style.css";
import heart_blank from "../../../assets/icons/heart-blank.png";

export function Post({ item }) {
  const { id, userResponse, postImg } = item;

  return (
    <div className="feed-card-content" key={id}>
      <div className="feed-card-img">
        <img className="card-img" src={postImg} alt={userResponse.name}></img>
      </div>
      <div className="feed-card-info">
        {/* <div className="feed-favorite-icon"> */}
        <img src={heart_blank} alt="heart-icon" className="icon-img"></img>
        {/* </div> */}
        <div className="feed-card-author">{userResponse.name}</div>
      </div>
    </div>
  );
}
