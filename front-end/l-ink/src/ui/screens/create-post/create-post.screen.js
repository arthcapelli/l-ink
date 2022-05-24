import {
  Input,
  ButtonFunction,
  ButtonLink,
  MultipleSelectChip,
  Header,
} from "../../components";
import { useLinkApi } from "../../../api";
import { useState } from "react";
import { useHistory } from "react-router-dom";
import { ROUTES } from "../../../constants";
import { useToast } from "../../../hooks";
import Switch from "@material-ui/core/Switch";
import "./style.css";
import logo from "../../../assets/images/logo.png";
import { useGlobalUser } from "../../../context";
import bg_image from "../../../assets/icons/bg-image.png";

export function CreatePostScreen() {
  const { showErrorToast, showSuccessToast } = useToast();
  const [postImg, setPostImg] = useState("");
  const [bodyLocal, setBodyLocal] = useState("");
  const [measures, setMeasures] = useState("");
  const [postTags, setPostTags] = useState([]);
  const history = useHistory();
  const [user, setUser] = useGlobalUser();
  const { createPost } = useLinkApi();

  async function create() {
    if (!postImg.length) {
      showErrorToast("Insira uma imagem para criar o post!");
      return;
    }

    const response = await createPost(
      postImg,
      bodyLocal,
      measures,
      user.id,
      postTags
    );

    if (response.length) {
      showSuccessToast(response);
      history.push(ROUTES.HOME);
    }
  }

  function handleSubmit(event) {
    event.preventDefault();

    create();
  }

  return (
    <>
      <Header />
      <div className="create-post-screen">
        <img
          className="create-post-img margin-default"
          src={postImg || bg_image}
        ></img>
        <div className="container">
          <form onSubmit={handleSubmit} className="create-post-content">
            <Input
              value={postImg}
              onChange={setPostImg}
              name="postImg"
              label="Imagem"
            />

            <div className="create-post-middle">
              <div>
                <Input
                  style={{ width: 145 }}
                  value={bodyLocal}
                  onChange={setBodyLocal}
                  name="bodyLocal"
                  label="Local do Corpo"
                />
              </div>

              <div>
                <Input
                  style={{ width: 145 }}
                  value={measures}
                  onChange={setMeasures}
                  name="measures"
                  label="Altura x Largura"
                />
              </div>
            </div>

            <MultipleSelectChip setStyleTags={setPostTags} />

            <div>
              <ButtonFunction
                name={"POSTAR"}
                onClick={handleSubmit}
              ></ButtonFunction>
            </div>
          </form>
        </div>
      </div>
    </>
  );
}
