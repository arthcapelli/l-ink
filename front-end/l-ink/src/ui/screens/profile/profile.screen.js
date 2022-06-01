import "./style.css"
import { useHistory, useParams } from "react-router"
import { useState, useEffect } from "react"
import { useGlobalUser } from "../../../context"
import { useLinkApi } from "../../../api"
import { Header, Post, Comment, Input } from "../../components"
import { useToast } from "../../../hooks"
import send from "../../../assets/icons/send.png"

export function ProfileScreen() {
  const { showErrorToast, showSuccessToast } = useToast()
  const [post, setPost] = useState(null)
  const [commentText, setCommentText] = useState("")
  const [refresh, setRefresh] = useState(false)
  const [user] = useGlobalUser()
  const { id } = useParams()
  const { getPost } = useLinkApi()
  const { createComment } = useLinkApi()

//   useEffect(() => {
//     async function getApiPost() {
//       const apiPost = await getPost(id, user.id)
//       setPost(apiPost)
//     }

//     getApiPost()
//   }, [refresh])

//   async function handleComment() {
//     if (!commentText.length) {
//       showErrorToast("Insira uma mensagem para criar o comentário!")
//       return
//     }

//     const response = await createComment(id, user.id, commentText)

//     if (response.length) {
//       showSuccessToast(response)
//       setRefresh(!refresh)
//       setCommentText("")
//     }
//   }

  return (
    <div>
      <Header />
      <div className="margin-default">OI PERFIL</div>
    </div>
  )
}
