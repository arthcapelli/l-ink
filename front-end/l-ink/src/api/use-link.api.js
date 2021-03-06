import { useAxios } from "../hooks/index"

const useLinkApi = () => {
  const DONT_SHOW_LOADER = true
  const HIDE_LOADER = true
  const KEEP_LOADER = false

  const { get, post } = useAxios()

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
      `user`,
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
      `auth/login`,
      {
        email,
        password,
      },
      KEEP_LOADER
    )
    return token
  }

  const getTags = async (shouldHideLoader) => {
    const tags = await get(`tags`, shouldHideLoader)
    return tags
  }

  const createPost = async (postImg, bodyLocal, measures, userId, postTags) => {
    const response = await post(
      `post`,
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

  const getPosts = async (userId, filterTags) => {
    const posts = await get(
      `post?authUserId=${userId}&filterTags=${filterTags}`,
      HIDE_LOADER
    )
    return posts
  }

  const favoritePost = async (postId, userId) => {
    await post(
      `favorite`,
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
      `comment`,
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
    const user = await get(`user/${userId}`, HIDE_LOADER)
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
