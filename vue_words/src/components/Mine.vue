<template>
    <div v-loading="loading" class="home">
<!--        <mt-header fixed="true" id="header" title="账号资料">-->
<!--        </mt-header>-->
<!--        <el-page-header content="账号资料">-->
<!--        </el-page-header>-->
        <h1>{{this.DATA.user}}</h1>
<!--        <div class="img">头像</div>-->
        <table class="layui-table">
            <tbody>
            <tr>
                <td>在广场展示我的单词</td>
                <td><mt-switch v-model="this.DATA.share"></mt-switch></td>
            </tr>
            <tr>
                <td>正在学习</td>
                <td>{{studying}}</td>
            </tr>
            <tr>
                <td>已学会</td>
                <td>{{studied}}</td>
            </tr>
            <tr>
                <td>收藏量</td>
                <td>{{like}}</td>
            </tr>
            </tbody>
        </table>
        <button @click="goOut()" type="button" class="layui-btn layui-btn-fluid">退出登录</button>
    </div>
</template>

<script>
    export default {
        name: "Mine",
        data(){
            return{
                studying:0,
                studied:0,
                like:0,
                loading:true
            }
        },
        beforeMount:function () {
            this.getAll();
        },
        methods: {
            getAll: function () {
                this.like=0;
                this.studying=0;
                this.studied=0;
                this.loading=true;
                this.$axios.get('word/my',{params:{id:this.DATA.userId}}).then(
                    response =>{
                        // let word=[];
                        for (let i in response.data.myWords){
                            let w=response.data.myWords[i];
                            // console.log(response.data[i])
                            if (w.collection){
                                this.like++;
                            }
                            this.studying++;
                        }
                        this.loading=false;
                    })
                this.$axios.get('word/history',{params:{id:this.DATA.userId}}).then(
                    response =>{
                        this.studied=response.data.his.length;
                        this.loading=false;
                    })
            },
            goOut:function () {
                localStorage.setItem('id',0);
                localStorage.setItem('user','');
                location.reload();
            }
        }
    }
</script>

<style scoped>
.img{width: 80%;height: auto;}
    h1{text-align: center}
</style>