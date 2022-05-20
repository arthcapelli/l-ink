import { useAxios } from '../hooks/index';

const useLinkApi = () => {
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
    const response = await post(`usuario/criar-usuario`, {
      email,
      avatar,
      name,
      password,
      confirmPassword,
      isTattooArtist,
      expTime,
      userTags,
    });

    return response;
  };

  const login = async (email, password) => {
    const token = await post(`autenticacao/login`, {
      email,
      password,
    });
    return token;
  };

  const getTags = async () => {
    const tags = await get(`tags`);
    return tags;
  };

  return {
    createUser,
    login,
    getTags,
  };
};

export { useLinkApi };
