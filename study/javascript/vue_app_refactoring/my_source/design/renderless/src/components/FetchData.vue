<script>
import axios from "axios";

export default {
    props: ['url'],
    data() {
        return {
            response: null,
            loading : true,
        }
    },
    created() {
        axios.get(this.url)
            .then( resp => { 
                this.response = resp.data
                this.loading = false
            })
            .catch( e => console.log(e))
    },
    render() {
        return this. $scopedSlots.default({
            response : this.response,
            loading : this.loading
        })
    }
}
</script>