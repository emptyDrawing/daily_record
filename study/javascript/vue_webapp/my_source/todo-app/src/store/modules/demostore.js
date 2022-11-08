const state = {
    price : 100    
}

const getters = {
    getPrice(state){
        return state.price;
    },
    getDoublePrice(state){
        return state.price * 2
    },
}    

export default{ 
    state,
    getters,
}