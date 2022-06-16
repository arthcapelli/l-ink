import { useAxios } from "../hooks/index"

const useLinkApi = () => {
  const DONT_SHOW_LOADER = true
  const HIDE_LOADER = true
  const KEEP_LOADER = false

  const { get, post } = useAxios()

  // Função que chama o endpoint para criação do usuário, a partir de informações recebidas por parâmetro
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

  // Função que chama o endpoint para realizar o login utilizando um email e password
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

  // Função que chama o endpoint para retornar todas as tags cadastradas no backend
  const getTags = async (shouldHideLoader) => {
    const tags = await get(`tags`, shouldHideLoader)
    return tags
  }

  // Função que chama o endpoint para criação de uma postagem
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

  // Função que chama o endpoint para retornar os posts a partir de um filtro de tags passado como "path variable" a fim de retornar apenas
  // os posts que possuem tais tags, também é utilizando o usuário logado para verificar, no back, as postagens que o mesmo favoritou
  const getPosts = async (userId, filterTags) => {
    const posts = await get(
      `post?authUserId=${userId}&filterTags=${filterTags}`,
      HIDE_LOADER
    )
    return posts
  }

  // Função que recebe o id de um post e do usuário logado, para chamar o endpoint que realizar a ação de favoritar o respectivo post
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

  // Função que chama o endpoint para retornar um post específico a partir de um id
  const getPost = async (postId, userId) => {
    const post = await get(`post/${postId}?authUserId=${userId}`, HIDE_LOADER)
    return post
  }

  // Função que consome o endpoint para criação de comentário, passando como parâmetro o id do post no qual o mesmo está sendo inserido
  // o id do usuário que escreveu o comentário, bem como o texto contido no mesmo
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

  // Função que consome o endpoint para retornar um usuário específico a partir de um id
  const getUser = async (userId) => {
    const user = await get(`user/${userId}`, HIDE_LOADER)
    return user
  }

  // Função que utiliza o endpoint para retornar os posts realizados por um usuário específico
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
