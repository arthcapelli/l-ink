import { Button } from "./btn-generic.component"
import { Link } from "react-router-dom"

export function ButtonLink({ path, name, disabled }) {
  return (
    <Link to={path}>
      <Button disabled={disabled}>{name}</Button>
    </Link>
  )
}
