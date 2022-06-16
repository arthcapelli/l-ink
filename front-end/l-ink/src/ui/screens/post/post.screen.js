import "./style.css"
import { useParams } from "react-router"
import { useState, useEffect } from "react"
import { useGlobalUser } from "../../../context"
import { useLinkApi } from "../../../api"
import { Header, Post, Comment, Input, Tags } from "../../components"
import { useToast } from "../../../hooks"
import send from "../../../assets/icons/send.png"
import info from "../../../assets/icons/info.png"

export function PostScreen() {
  const { showErrorToast, showSuccessToast } = useToast()
  const [post, setPost] = useState(null)
  const [commentText, setCommentText] = useState("")
  const [refresh, setRefresh] = useState(false)
  const [user] = useGlobalUser()
  const { id } = useParams()
  const { createComment, getPost } = useLinkApi()

  // Função que renderiza o post específico, passando um id e userId, podendo ser chamada mais vezes em caso de mudança no estado
  // da constante refresh
  useEffect(() => {
    async function getApiPost() {
      const apiPost = await getPost(id, user.id)
      setPost(apiPost)
    }

    getApiPost()
  }, [refresh])

  // Função que valida se o usuário digitou algo no campo de commentText, se sim ela chamada a função de criar comentário
  // passando o id do post, do usuário e o texto digitado, ao final ela muda o estado de refresh para que o post
  // seja renderizado novamente, com o comentário inserido
  async function handleComment() {
    if (!commentText.length) {
      showErrorToast("Insira uma mensagem para criar o comentário!")
      return
    }

    const response = await createComment(id, user.id, commentText)

    if (response.length) {
      showSuccessToast(response)
      setRefresh(!refresh)
      setCommentText("")
    }
  }

  return (
    <div>
      <Header />
      <div className="margin-default post-screen-content">
        {!post ? (
          <p>Não tem post.</p>
        ) : (
          <>
            <Post item={post} key={post.id} />
            <div className="post-screen-info container">
              <div className="post-screen-info-icon">
                <img src={info} alt="info" className="info-icon" />
              </div>
              <div className="post-screen-info-text">
                <p>Local do corpo: {post.bodyLocal}</p>
                <p>Medidas: {post.measures}</p>
              </div>
            </div>
            <Tags tags={post.postTags} />
            <div className="post-screen-comments">
              <div className="container">
                {!post.comments.length ? (
                  <p>Não tem comentários!</p>
                ) : (
                  post.comments.map((comment) => (
                    <Comment item={comment} key={comment.id} />
                  ))
                )}
              </div>
            </div>
          </>
        )}
        <div className="post-screen-input-comment container">
          <Input
            placeholder="Digite um comentário..."
            className="post-screen-input"
            inputProps={{ maxLength: 40 }}
            value={commentText}
            onChange={setCommentText}
          />
          <img
            className="post-screen-send"
            src={send}
            onClick={handleComment}
          ></img>
        </div>
      </div>
    </div>
  )
}
