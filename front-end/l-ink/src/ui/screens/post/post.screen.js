import "./style.css";
import { useHistory, useParams } from "react-router";
import { useState, useEffect } from "react";
import { useGlobalUser } from "../../../context";
import { useLinkApi } from "../../../api";
import { Header, Post, Comment, Input } from "../../components";
import send from "../../../assets/icons/send.png";

export function PostScreen() {
  const [post, setPost] = useState(null);
  const [user] = useGlobalUser();
  const { id } = useParams();
  const { getPost } = useLinkApi();

  useEffect(() => {
    async function getApiPost() {
      const apiPost = await getPost(id, user.id);
      setPost(apiPost);
    }

    getApiPost();
  }, []);

  return (
    <div>
      <Header />
      <div className="margin-default post-screen-content">
        {!post ? (
          <p>Não tem post.</p>
        ) : (
          <div>
            <Post item={post} key={post.id} />
            {!post.comments.length ? (
              <p>Não tem comentários!</p>
            ) : (
              post.comments.map((comment) => <Comment item={comment} />)
            )}
          </div>
        )}
        <Input
          placeholder="Digite um comentário..."
          className="post-screen-input"
        />
        <img className="post-screen-send" src={send}></img>
      </div>
    </div>
  );
}
