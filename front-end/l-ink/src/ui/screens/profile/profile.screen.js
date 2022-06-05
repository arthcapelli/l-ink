import "./style.css"
import { useHistory, useParams } from "react-router"
import { useState, useEffect } from "react"
import { useGlobalUser } from "../../../context"
import { useLinkApi } from "../../../api"
import {
  Header,
  Post,
  // Comment,
  // Input,
  ProfileInfo,
  Tags,
} from "../../components"
import { useToast } from "../../../hooks"

export function ProfileScreen() {
  // const { showErrorToast, showSuccessToast } = useToast()
  const [userApi, setUserApi] = useState(null)
  const [userPostsApi, setUserPostsApi] = useState([])
  // const [commentText, setCommentText] = useState("")
  // const [refresh, setRefresh] = useState(false)
  const [user] = useGlobalUser()
  const { id } = useParams()
  const { getUser, getUserPosts } = useLinkApi()

  useEffect(() => {
    async function getApiUser() {
      const apiUser = await getUser(id)
      setUserApi(apiUser)
    }

    async function getApiUserPosts() {
      const apiUserPosts = await getUserPosts(id, user.id || 0)
      setUserPostsApi(apiUserPosts)
    }

    getApiUser()
    getApiUserPosts()
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
          {userApi && (
            <>
              <ProfileInfo user={userApi} />
              <Tags tags={userApi.userTags} />
            </>
          )}

          {!userPostsApi ? (
            <p>Usuário não tem posts.</p>
          ) : (
            userPostsApi.map((post) => (
              <div className="profile-screen-posts" key={post.id}>
                <Post item={post} />
              </div>
            ))
          )}
        </div>
      </div>
    </div>
  )
}
