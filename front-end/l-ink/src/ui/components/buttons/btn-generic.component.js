import "./style.css"

export function Button({ children, disabled, ...props }) {
  return (
    <button
      className={`botao-generico ${
        disabled ? "botao-generico-desabilitado" : ""
      } `}
      disabled={disabled}
      {...props}
    >
      {children}
    </button>
  )
}
