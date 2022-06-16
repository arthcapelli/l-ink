import "./style.css"
import TextField from "@mui/material/TextField"
import Grid from "@mui/material/Grid"

export function Input({ onChange, ...props }) {
  // Função que passa para o parâmetro onChange, o valor inserido no TextField
  function handleChange(event) {
    onChange(event.target.value)
  }

  return (
    <Grid item xs={12}>
      <TextField
        fullWidth
        {...props}
        onChange={handleChange}
        variant="outlined"
        style={{ backgroundColor: "white" }}
      />
    </Grid>
  )
}
