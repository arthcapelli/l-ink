import "./style.css"
import { useParams } from "react-router"
import { useState, useEffect } from "react"
import { useGlobalUser } from "../../../context"
import { useLinkApi } from "../../../api"
import { Header, Post, ProfileInfo, Tags } from "../../components"

export function ProfileScreen() {
  const [userApi, setUserApi] = useState(null)
  const [userPostsApi, setUserPostsApi] = useState([])
  const [user] = useGlobalUser()
  const { id } = useParams()
  const { getUser, getUserPosts } = useLinkApi()

  // Função que utiliza um id para retornar o respectivo usuário, bem como os posts criados pelo mesmo
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
