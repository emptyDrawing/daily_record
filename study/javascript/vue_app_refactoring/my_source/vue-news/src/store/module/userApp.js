import { callUserInfo } from "@/api"

const state = {
    userList : [],
}
// https://www.rrrhys.com/vue-js-mapgetters-with-params/
const getters =  {
    getUserInfo : (state) => (user) => {
        return state.userList.find( userInfo => userInfo.id === user.id);
    }
}

const mutations = {
    setUserInfo(state, { data }) {
        state.userList.push(data)
    }
}

const actions = {
    FETCH_USER( { state, commit }, { name: userName }) {
        if( state.userList.findIndex( userInfo => userInfo.id === userName ) < 0 ) {
            return callUserInfo(userName)
                .then( ({ data }) => {
                    commit('setUserInfo',{ data }) 
                })
                .catch( err => console.error(err) )        
        }
        
        return new Promise();
    }
}

export default {
    state, 
    getters, 
    mutations,
    actions,
}