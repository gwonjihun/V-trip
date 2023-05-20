<template>
  <div>
    {{ plan }}
    {{ plan_init}}
    {{ plans}}
  </div>
</template>

<script>
export default {
  data() {
    return {
      plans: [
        //   {
        //     day: String,
        //     trip_list: [],
        //   },
      ],
      day : "",
      idx : "",
      target : {},
    };
  },
  props: {
    //이것은 plan info table용
    plan: {
      title: "",
      content_id: "",
      addr: "",
    },
    plan_init: {
      title: "",
      start_date: "",
      end_date: "",
    },
    
  },
  watch: {
    plan :{
        handler(){
            if(this.plans.length<1){
                const plan ={
                    day : '1',
                    trip_list:[{...this.plan}]
                };
                this.plans.push(plan);
            }else{
                const plan = {...this.plan};
                this.plans[0].trip_list.push(plan);
            }
            console.log("!!!");
        },
        deep:true,
    },
  },
};
</script>

<style>
</style>