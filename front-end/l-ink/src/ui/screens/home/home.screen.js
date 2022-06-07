import { Header, Feed } from "../../components"
import "./style.css"

export function HomeScreen() {
  return (
    <div>
      <Header />
      <div className="margin-default">
        <Feed />
      </div>
    </div>
  )
}
