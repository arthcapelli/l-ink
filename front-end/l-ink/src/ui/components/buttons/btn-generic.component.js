import "./style.css"

export function Button({ children, disabled, isSecondary, ...props }) {
  return (
    <button
      className={`btn-generic ${isSecondary ? "btn-secondary" : ""} `}
      disabled={disabled}
      {...props}
    >
      {children}
    </button>
  )
}
