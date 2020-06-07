<template>
    <div v-loading="loading" class="home">
<!--        <mt-header fixed="true" id="header" title="我在学习">-->
<!--        </mt-header>-->
<!--        <el-page-header content="我在学习">-->
<!--        </el-page-header>-->
        <br><br><br>
        <div v-if="ifNull()">您还没有正在学习的内容，快去广场添加吧</div>
        <div class="study" v-for="(item,i) in words" v-bind:key="i">
            <mt-cell-swipe title="右滑学会"
                           :left="[{
                  content: '我学会了',
                  style: { background: 'blue', color: '#fff' },
                  handler: () => studyed(item.id)
                }
                    ]"></mt-cell-swipe>
            <div class="layui-card">
                <div class="layui-card-header">
                    <div class="word">{{item.word}}</div>
                    <div @click="stars(item.id)"  class="like">
                        <i v-if="!item.like" class="layui-icon layui-icon-heart"></i>
                        <i v-if="item.like" class="layui-icon layui-icon-heart-fill"></i>
                    </div>
                </div>
                <div class="layui-card-header">
                    <div class="word">{{item.speak}}</div>
                    <div class="like">
                        <i class="layui-icon layui-icon-play"></i>
                    </div>
                </div>
                <div class="layui-card-body">
                    {{item.tra}}<br>
                    {{item.more}}
                </div>
            </div>
            <hr class="layui-bg-orange">
        </div>

    </div>
</template>

<script>
    export default {
        name: "Home",
        data(){
            return {
                words:[],
                loading:true,
                userId:this.DATA.userId
            }
        },
        beforeMount:function(){
            this.getLikes()
        },
        methods:{
            final:function () {
                this.$toast('真他妈好');
            },
            stars:function (id) {
                for (let i in this.words){
                    if (this.words[i].id===id){
                        if (this.words[i].like){
                            this.$axios.get('/word/unLike',
                                {params:{userid:this.DATA.userId,wordid:id}}).then(
                                response =>{
                                    if (response.data.message==='200'){
                                        this.$message('已取消star');
                                        this.getLikes();
                                    }else {
                                        this.$message('操作失败');
                                    }
                                }
                            )
                        }else {
                            this.$axios.get('/word/like',
                                {params:{userid:this.DATA.userId,wordid:id}}).then(
                                response =>{
                                    if (response.data.message==='200'){
                                        this.$message({
                                            message: '棒极了，又收藏一个单词',
                                            type: 'success'
                                        });
                                        this.getLikes();
                                    }else {
                                        this.$message('操作失败');
                                    }
                                }
                            )
                        }

                    }
                }
            },
            studyed:function (id) {
                this.$axios.get('/word/success',
                    {params:{userid:this.DATA.userId,wordid:id}}).then(
                    response =>{
                        if (response.data.message==='200'){
                            this.$message({
                                message: '棒极了，又学会一个单词',
                                type: 'success'
                            });
                            this.getLikes();
                        }else {
                            this.$message('操作失败');
                        }
                    }
                )
            },
            getLikes:function () {
                this.loading=true;
                this.words=[];
                this.$axios.get('word/my',{params:{id:this.DATA.userId}}).then(
                    response =>{
                        // let word=[];
                        for (let i in response.data.myWords){
                            let w=response.data.myWords[i];
                            // console.log(response.data[i])
                            this.words.push({id:w.id,word:w.word,
                                like:w.collection,speak:w.speak,tra:w.translate,more:w.exp})
                        }
                        this.DATA.words=this.words;
                        this.loading=false;
                    })
            },
            ifNull:function () {
                if (this.words.length===0){
                    return true;
                }
                return false;
            }
        }
    }
</script>

<style scoped>
    .word{
        width: 50%;
        float: left;
        text-align: left;
    }
    .like{
        width: 50%;
        float: left;
        text-align: right;
    }
</style>