import createGlobalState from "react-create-global-state"

const [useGlobalShowLoader, GlobalShowLoaderProvider] = createGlobalState(false)

export { useGlobalShowLoader, GlobalShowLoaderProvider }
