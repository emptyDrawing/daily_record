import axios from "axios";

// 1. HttP Request / Response 관련 기본설정
const config = {
    baseurl : 'https://api.hnpwa.com/v0',
    apiList : ['news', 'jobs', 'ask']
}

// 2. API 함수 개별 정의

const callAPIList = (name) => {
    return new Promise((resolve, reject) => {
        if( config.apiList.includes(name) ){
            resolve(callListByName(name));
        }

        reject(`This [${name}]API is not provided`);
    });
}

const callListByName = (name) => {
    return axios.get(`${config.baseurl}/${name}/1.json`)
}

const callUserInfo = (userName) => {
    return axios.get(`${config.baseurl}/user/${userName}.json`)
}

const callItemInfo = ( id ) => {
    return axios.get(`${config.baseurl}/item/${id}.json`)
}

// 3. export
export {
    callAPIList,
    callUserInfo,
    callItemInfo,
}