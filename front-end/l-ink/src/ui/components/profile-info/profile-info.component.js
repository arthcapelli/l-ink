import "./style.css"

export function ProfileInfo({ user }) {
  const {
    name,
    userTags,
    avatar,
    location,
    whatsapp,
    isTattooArtist,
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
      <div className="profile-info-right">
        {!isTattooArtist ? (
          <div className="profile-info-tags">
            {userTags.map((tag) => (
              <div className="profile-info-tag" key={tag}>
                {tag}
              </div>
            ))}
          </div>
        ) : (
          <div>
            <div className="profile-info-location">{location}</div>
            <div className="profile-info-whatsapp">{whatsapp}</div>
            <div className="profile-info-expTime">{expTime}</div>
          </div>
        )}
      </div>
    </div>
  )
}
