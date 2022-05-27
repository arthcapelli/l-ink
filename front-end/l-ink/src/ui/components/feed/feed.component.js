import { useState, useEffect } from "react"
import { useLinkApi } from "../../../api"
import { useGlobalUser } from "../../../context"
import { Post } from "../index"
import "./style.css"

export function Feed() {
  const [posts, setPosts] = useState([])
  const [user] = useGlobalUser()

  const { getPosts } = useLinkApi()

  useEffect(() => {
    async function getApiPosts() {
      const apiPosts = await getPosts(user.id || 0)
      setPosts(apiPosts)
    }

    getApiPosts()
  }, [])

  return (
    <div className="feed-list-content">
      {!posts ? (
        <p>Não tem posts.</p>
      ) : (
        posts.map((post) => <Post item={post} key={post.id} />)
      )}
    </div>
  )
}
