import ListView from './ListView.vue'
import bus from '../utils/bus'


export default function createListView(componentName) {
    return {
        // 재사용할 인스턴스(컴포넌트) 옵션들이 들어가는 자리
        name: componentName, // HighOrder Component 이름
        created(){
            bus.$emit('start:spinner')
            this.$store.dispatch('FETCH_DATA', {'name' : this.$route.name})
                .then( (data) => { bus.$emit('end:spinner'); return data; } )
                .catch( (e) => { console.log(e); bus.$emit('end:spinner'); } );
        },
        render(createElement) {
            return createElement(ListView);
        }
    }
}