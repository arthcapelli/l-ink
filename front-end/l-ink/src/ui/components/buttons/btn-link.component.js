import { Button } from "./btn-generic.component"
import { Link } from "react-router-dom"

export function ButtonLink({ path, name, disabled, isSecondary }) {
  return (
    <Link to={path}>
      <Button disabled={disabled} isSecondary={isSecondary}>
        {name}
      </Button>
    </Link>
  )
}
