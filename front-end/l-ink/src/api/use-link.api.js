import { useAxios } from "../hooks/index"

const useLinkApi = () => {
  const DONT_SHOW_LOADER = true
  const HIDE_LOADER = true
  const KEEP_LOADER = false

  const { get, post, put, del } = useAxios()

  const createUser = async (
    email,
    avatar,
    name,
    password,
    confirmPassword,
    isTattooArtist,
    expTime,
    userTags
  ) => {
    const response = await post(
      `usuario/criar-usuario`,
      {
        email,
        avatar,
        name,
        password,
        confirmPassword,
        isTattooArtist,
        expTime,
        userTags,
      },
      HIDE_LOADER
    )

    return response
  }

  const login = async (email, password) => {
    const token = await post(
      `autenticacao/login`,
      {
        email,
        password,
      },
      KEEP_LOADER
    )
    return token
  }

  const getTags = async () => {
    const tags = await get(`tags`, HIDE_LOADER)
    return tags
  }

  const createPost = async (postImg, bodyLocal, measures, userId, postTags) => {
    const response = await post(
      `post/criar-post`,
      {
        postImg,
        bodyLocal,
        measures,
        userId,
        postTags,
      },
      KEEP_LOADER
    )
    return response
  }

  const getPosts = async (userId) => {
    const posts = await get(`post?authUserId=${userId}`, HIDE_LOADER)
    return posts
  }

  const favoritePost = async (postId, userId) => {
    await post(
      `favorite/favoritar-post`,
      {
        userId,
        postId,
      },
      KEEP_LOADER,
      DONT_SHOW_LOADER
    )
  }

  return {
    createUser,
    login,
    getTags,
    createPost,
    getPosts,
    favoritePost,
  }
}

export { useLinkApi }
