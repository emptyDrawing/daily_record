
import { callAPIList } from '../../api/index.js'


const state = {
    newsList : [],
    jobsList : [],
    askList : [],
}

const getters =  {
    getNewsList() {
        return state.newsList;
    },
    getJobsList() {
        return state.jobsList;
    },
    getAskList() {
        return state.askList;
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
        }
    }
}
 
const actions = {
    // FETCH_DATA( context, { name })
    FETCH_DATA( { commit }, { name }) {
        callAPIList(name)
          .then( 
              //(resp) => { 
              //    const data = resp.data
              //    context.commit('setAPIData',{ name, data })      
              ({ data }) => {
              commit('setAPIData',{ name, data }) 
            })
          .catch( err => console.error(err) )
    }
}

export default {
    state, 
    getters, 
    mutations,
    actions
}