import { Button } from "./btn-generic.component"

export function ButtonFunction({ onClick, name, disabled, isSecondary }) {
  return (
    <Button onClick={onClick} disabled={disabled} isSecondary={isSecondary}>
      {name}
    </Button>
  )
}
