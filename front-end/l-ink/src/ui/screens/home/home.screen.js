import { Input, ButtonFunction, ButtonLink, Header } from "../../components"
import { useLinkApi } from "../../../api"
import { useState } from "react"
import { useHistory } from "react-router-dom"
import { ROUTES } from "../../../constants"
import "./style.css"
import logo from "../../../assets/images/logo.png"

export function HomeScreen() {
  return (
    <div>
      <Header />
      <p className="margin-default">OI HOME</p>
    </div>
  )
}
