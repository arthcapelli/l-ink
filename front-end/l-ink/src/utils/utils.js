import { useHistory } from "react-router-dom"

function getDate(createdAt) {
  const dateObj = new Date(createdAt)
  const month = dateObj.getMonth() + 1
  const day = dateObj.getUTCDate() - 1
  const year = dateObj.getFullYear()
  return `${day}/${month}/${year}`
}

function getBrazilianTime(createdAt) {
  const dateObj = new Date(createdAt)
  const hour = dateObj.getHours()
  const minute = dateObj.getMinutes()
  const second = dateObj.getSeconds()
  return `${hour}:${minute}:${second}`
}

export { getDate, getBrazilianTime }
