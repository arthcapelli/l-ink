import { useState, useEffect } from "react"
import { useLinkApi } from "../../../api"
import { MultipleSelectChip } from "../../components"
import { useGlobalUser } from "../../../context"
import { Post } from "../index"
import "./style.css"
import filter from "../../../assets/icons/search.png"

export function Feed() {
  const [posts, setPosts] = useState([])
  const [filterTags, setFilterTags] = useState([])
  const [search, setSearch] = useState(false)
  const [user] = useGlobalUser()

  const { getPosts } = useLinkApi()

  function handleSearchClick() {
    setSearch(!search)
  }

  useEffect(() => {
    async function getApiPosts() {
      const apiPosts = await getPosts(user.id || 0, filterTags)
      setPosts(apiPosts)
    }

    getApiPosts()
  }, [search])

  return (
    <>
      <div className="feed-filter container">
        <div>
          <MultipleSelectChip setStyleTags={setFilterTags} hideLoader={false} />
        </div>
        <div className="feed-img-content">
          <img
            src={filter}
            className="search-img"
            onClick={handleSearchClick}
          ></img>
        </div>
      </div>
      <div className="feed-list-content">
        {posts?.length == 0 ? (
          <p>Não tem posts.</p>
        ) : (
          posts.map((post) => <Post item={post} key={post.id} />)
        )}
      </div>
    </>
  )
}
