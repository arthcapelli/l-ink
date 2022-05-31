import "./style.css";
import { Favorite } from "../favorite/favorite.component";
import { useState } from "react";
import { useGlobalUser } from "../../../context";
import { useHistory } from "react-router-dom";

export function Post({ item }) {
  const { id, userResponse, postImg, isFavorite } = item;
  const [user] = useGlobalUser();
  const [favorite, setFavorite] = useState(isFavorite);
  const { push } = useHistory();

  function handleClickPost() {
    push(`/post/${id}`);
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
        <div className="feed-card-author">
          <img
            src={userResponse.avatar}
            alt="avatar"
            className="avatar-img"
          ></img>
          <div>{userResponse.name}</div>
        </div>
        {user?.id && (
          <Favorite
            postId={id}
            isFavorite={favorite}
            setIsFavorite={setFavorite}
          />
        )}
      </div>
    </div>
  );
}
