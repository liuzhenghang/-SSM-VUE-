<template>
    <div v-loading="loading" class="home">
<!--        <mt-header fixed="true" id="header" title="温故而知新">-->
<!--        </mt-header>-->
<!--        <el-page-header content="温故而知新">-->
<!--        </el-page-header>-->
        <div v-if="ifNull()">您还没有学会的单词，多多加油噢</div>
        <div class="study" v-for="(item,i) in words" v-bind:key="i">
            <mt-cell-swipe title="左滑删除"
                           :right="[{
                  content: '删除',
                  style: { background: 'red', color: '#fff' },
                  handler: () => delWord(item.id)
                }
                    ]"></mt-cell-swipe>
            <div class="layui-card">
                <div class="layui-card-header">
                    <div class="word">{{item.word}}</div>
                    <div class="like">
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
        name: "History",
        data (){
            return{
                words:[],
                loading:true
            }
        },
        beforeMount:function(){
            this.getHistory();
        },
        methods:{
            delWord:function (id) {
                this.$axios.get('/word/del',
                    {params:{userid:this.DATA.userId,wordid:id}}).then(
                    response =>{
                        if (response.data.message==='200'){
                            this.$message({
                                message: '奥义！解脱+1！',
                                type: 'success'
                            });
                            this.getHistory();
                        }else {
                            this.$message('操作失败');
                        }
                    }
                )
            },
            getHistory:function () {
                this.words=[];
                this.$axios.get('word/history',{params:{id:this.DATA.userId}}).then(
                    response =>{
                        for (let i in response.data.his){
                            let w=response.data.his[i];
                            // console.log(response.data[i])
                            this.words.push({id:w.id,word:w.word,
                                like:w.collection,speak:w.speak,tra:w.translate,more:w.exp})
                        }
                        // this.words=this.DATA.words;
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