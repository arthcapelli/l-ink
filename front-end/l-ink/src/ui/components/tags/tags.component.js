import "./style.css"
import Chip from "@mui/material/Chip"

export function Tags({ tags }) {
  return (
    <div className="tags-content">
      {tags.map((tag) => (
        <div className="tags-content-tag" key={tag}>
          <Chip
            className="tags-content-chip"
            label={tag}
            variant="outlined"
            size="small"
            sx={{
              background: "white",
              margin: "3px",
              fontFamily: "font-link",
              fontWeight: "bold",
            }}
          />
        </div>
      ))}
    </div>
  )
}
