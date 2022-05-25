/*import Box from "@material-ui/core/Box";
import ImageList from "@material-ui/core/ImageList";
import ImageListItem from "@material-ui/core/ImageListItem";
import ImageListItemBar from "@material-ui/core/ImageListItemBar";
import IconButton from "@material-ui/core/IconButton";
*/
import Box from "@mui/material/Box";
import ImageList from "@mui/material/ImageList";
import ImageListItem from "@mui/material/ImageListItem";
import ImageListItemBar from "@mui/material/ImageListItemBar";
import IconButton from "@mui/material/IconButton";
import heart_blank from "../../../assets/icons/heart-blank.png";
import { useLinkApi } from "../../../api";
import { useState, useEffect } from "react";
import { Post } from "../index";
import "./style.css";

export function Feed() {
  const [posts, setPosts] = useState([]);

  const { getPosts } = useLinkApi();

  useEffect(() => {
    async function getApiPosts() {
      const apiPosts = await getPosts();
      setPosts(apiPosts);
    }

    getApiPosts();
  }, []);

  return (
    <div className="feed-list-content">
      {!posts ? (
        <p>"Não tem posts."</p>
      ) : (
        posts.map((post) => <Post item={post} key={post.id} />)
      )}
    </div>
  );
}
