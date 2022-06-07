import { useState, useEffect } from "react"
import { useLinkApi } from "../../../api"
import Box from "@material-ui/core/Box"
import OutlinedInput from "@material-ui/core/OutlinedInput"
import InputLabel from "@material-ui/core/InputLabel"
import MenuItem from "@material-ui/core/MenuItem"
import FormControl from "@material-ui/core/FormControl"
import Select from "@material-ui/core/Select"
import Chip from "@material-ui/core/Chip"

const ITEM_HEIGHT = 48
const ITEM_PADDING_TOP = 8
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
}

export function MultipleSelectChip({ setStyleTags, hideLoader }) {
  const [tags, setTags] = useState([])
  const [selectedTags, setSelectedTags] = useState([])

  const { getTags } = useLinkApi()

  useEffect(() => {
    async function getApiTags() {
      const apiTags = await getTags(hideLoader)
      setTags(apiTags)
    }

    getApiTags()
  }, [])

  const handleChange = (event) => {
    const {
      target: { value },
    } = event
    setSelectedTags(typeof value === "string" ? value.split(",") : value)
    setStyleTags(typeof value === "string" ? value.split(",") : value)
  }

  return (
    <div>
      <FormControl sx={{ m: 1, width: 300 }}>
        <InputLabel
          id="demo-multiple-chip-label"
          style={{ marginLeft: "15px" }}
        >
          Tags
        </InputLabel>
        <Select
          style={{ maxWidth: 300, width: 300 }}
          labelId="demo-multiple-chip-label"
          id="demo-multiple-chip"
          multiple
          value={selectedTags}
          onChange={handleChange}
          input={<OutlinedInput id="select-multiple-chip" label="Tags" />}
          renderValue={(selected) => (
            <Box
              style={{ whiteSpace: "pre-wrap" }}
              sx={{ display: "flex", flexWrap: "wrap" }}
            >
              {selected.map((value) => (
                <Chip style={{ margin: "2px" }} key={value} label={value} />
              ))}
            </Box>
          )}
          MenuProps={MenuProps}
        >
          {tags.map((tag) => (
            <MenuItem key={tag} value={tag}>
              {tag}
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    </div>
  )
}
