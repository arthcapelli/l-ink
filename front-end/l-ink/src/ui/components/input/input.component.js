import "./style.css"
import TextField from "@material-ui/core/TextField"
import Grid from "@material-ui/core/Grid"

export function Input({ onChange, ...props }) {
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
      />
    </Grid>
  )
}
