import "./style.css"
import { useHistory, useParams } from "react-router"
import { useState, useEffect } from "react"
import { useGlobalUser } from "../../../context"
import { useLinkApi } from "../../../api"
import { Header, Post, Comment, Input, ProfileInfo } from "../../components"
import { useToast } from "../../../hooks"
import send from "../../../assets/icons/send.png"

export function ProfileScreen() {
  const { showErrorToast, showSuccessToast } = useToast()
  const [userApi, setUserApi] = useState(null)
  const [commentText, setCommentText] = useState("")
  // const [refresh, setRefresh] = useState(false)
  const [user] = useGlobalUser()
  const { id } = useParams()
  const { getUser } = useLinkApi()

  useEffect(() => {
    async function getApiUser() {
      const apiUser = await getUser(id)
      setUserApi(apiUser)
    }

    getApiUser()
  }, [])

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
      <div className="margin-default">
        <div className="profile-screen-content">
          {userApi && <ProfileInfo user={userApi} />}
        </div>
      </div>
    </div>
  )
}
