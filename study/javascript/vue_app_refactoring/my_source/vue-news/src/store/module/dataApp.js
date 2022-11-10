import { callAPIList, callItemInfo } from '../../api/index.js'

const state = {
    newsList : [],
    jobsList : [],
    askList : [],
    itemList : []
}

const getters =  {
    getAPIList(state) {
        return ({name}) => {
            switch (name) {
                case 'news':
                    return state.newsList
                case 'jobs':
                    return state.jobsList
                case 'ask':
                    return state.askList
                default :
                    return []
            }
        };
    }
    ,getItem(state) {
        // orgInfo.id -> number / id -> string....
        return ({id}) => state.itemList.find((orgInfo) => orgInfo.id == id )
    } 
}

const mutations = {
    setAPIData(state, { name, data }) {
        switch (name) {
            case 'news':
                state.newsList = data;
                break;
            case 'jobs':
                state.jobsList = data;
                break;
            case 'ask':
                state.askList = data;
                break;
            case 'item' :
                (() => {
                    const orgList = state.itemList;
                    const findIndex= orgList.findIndex( org => org.id == data.id);
                    if ( findIndex >= 0){
                        orgList.splice(findIndex,1);
                    }
                    orgList.push(data);
                })()
                break;    
        }
    }
}
 
const actions = {
    FETCH_DATA( { commit }, { name }) {
        callAPIList(name)
          .then( ({ data }) => {
              commit('setAPIData',{ name, data })
              return data; // promise 리턴
            })
          .catch( err => console.error(err) )
    },
    FETCH_ITEM( {commit}, {id}) {
        callItemInfo(id)
            .then(({ data }) =>  commit('setAPIData',{ name : "item", data }))
            .catch( err => console.error(err) )
    }
}

export default {
    state, 
    getters, 
    mutations,
    actions
}