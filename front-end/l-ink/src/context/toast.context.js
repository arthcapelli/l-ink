import createGlobalState from 'react-create-global-state'

const [useGlobalToastConfig, GlobalToastConfigProvider] = createGlobalState({})

export { useGlobalToastConfig, GlobalToastConfigProvider }
