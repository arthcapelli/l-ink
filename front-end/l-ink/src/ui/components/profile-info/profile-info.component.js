import "./style.css"
import address from "../../../assets/icons/map.png"
import machine from "../../../assets/icons/machine.png"
import whatsapp from "../../../assets/icons/whatsapp.png"

export function ProfileInfo({ user }) {
  const {
    name,
    userTags,
    avatar,
    street,
    city,
    uf,
    phone,
    tattooArtist,
    expTime,
  } = user

  return (
    <div className="profile-info">
      <div className="profile-info-left">
        <div className="profile-info-avatar">
          <img className="profile-info-img" src={avatar} alt={name} />
        </div>
        <div className="profile-info-name">{name}</div>
      </div>
      {tattooArtist && (
        <div className="profile-info-right">
          <div className="profile-info-address adjust-display">
            <img className="profile-info-address-icon" src={address} />
            <div className="profile-info-address-text">
              <div className="profile-info-street">{street}</div>
              <div className="profile-info-city-uf">
                {city} - {uf}
              </div>
            </div>
          </div>
          <div className="profile-info-phone adjust-display">
            <img className="profile-info-phone-icon" src={whatsapp} />
            <div className="profile-info-phone">{phone}</div>
          </div>
          <div className="profile-info-machine adjust-display">
            <img className="profile-info-exptime-icon" src={machine} />
            <div className="profile-info-expTime">{expTime} anos</div>
          </div>
        </div>
      )}
    </div>
  )
}
