import bus from '../utils/bus'

export default {
    // 재사용할 컴포넌트 옵션
    // created(){
    //     bus.$emit('start:spinner')
    //     this.$store.dispatch('FETCH_DATA',{'name' : this.$route.name})
    //         .catch( (e) => { console.log(e); bus.$emit('end:spinner'); } );
    // },
    mounted(){
        console.log('mounted');
        bus.$emit('end:spinner');
    },
}