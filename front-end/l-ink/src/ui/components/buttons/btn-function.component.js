import { Button } from "./btn-generic.component"

export function ButtonFunction({ onClick, name, disabled }) {
  return (
    <Button onClick={onClick} disabled={disabled}>
      {name}
    </Button>
  )
}
