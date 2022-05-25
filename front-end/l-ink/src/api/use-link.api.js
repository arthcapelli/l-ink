import { useAxios } from "../hooks/index";

const useLinkApi = () => {
  const HIDE_LOADER = true;
  const SHOW_LOADER = false;

  const { get, post, put, del } = useAxios();

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
    );

    return response;
  };

  const login = async (email, password) => {
    const token = await post(
      `autenticacao/login`,
      {
        email,
        password,
      },
      HIDE_LOADER
    );
    return token;
  };

  const getTags = async () => {
    const tags = await get(`tags`, HIDE_LOADER);
    return tags;
  };

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
      SHOW_LOADER
    );
    return response;
  };

  const getPosts = async () => {
    const posts = await get(`post`, HIDE_LOADER);
    return posts;
  };

  return {
    createUser,
    login,
    getTags,
    createPost,
    getPosts,
  };
};

export { useLinkApi };
