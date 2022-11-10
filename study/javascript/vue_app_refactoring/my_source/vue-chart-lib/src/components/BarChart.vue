<template>
     <canvas ref="chart" id="barChart"></canvas>
</template>

<script>

export default {
    props : [ 'chartdata'],
    mounted() {
        
    new this.$_Chart(this.$refs.chart.getContext('2d'), {
        type: 'bar',
        data: {
            labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
            datasets: this.chartdata
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
  }
}
</script>

<style>

</style>