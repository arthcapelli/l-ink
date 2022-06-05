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
    phone,
    location,
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
        phone,
        location,
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

  const getPost = async (postId, userId) => {
    const post = await get(`post/${postId}?authUserId=${userId}`, HIDE_LOADER)
    return post
  }

  const createComment = async (postId, userId, commentText) => {
    const response = await post(
      `comment/criar-comentario`,
      {
        postId,
        userId,
        commentText,
      },
      HIDE_LOADER
    )
    return response
  }

  const getUser = async (userId) => {
    const user = await get(`usuario/${userId}`, HIDE_LOADER)
    return user
  }

  const getUserPosts = async (userId, authUserId) => {
    const posts = await get(
      `post/user-posts/${userId}?authUserId=${authUserId}`,
      HIDE_LOADER
    )
    return posts
  }

  return {
    createUser,
    login,
    getTags,
    createPost,
    getPosts,
    favoritePost,
    getPost,
    createComment,
    getUser,
    getUserPosts,
  }
}

export { useLinkApi }
