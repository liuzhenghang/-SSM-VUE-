<template>
    <div v-loading="loading"  class="home">
<!--        <mt-header fixed="true" id="header" title="单词广场">-->
<!--        </mt-header>-->
<!--        <el-page-header content="单词广场">-->
<!--        </el-page-header>-->
        <div v-if="ifNull()">广场单词枯竭啦</div>
<!--        <el-button @click.native="getAll()" type="primary" round>-->
<!--            刷新</el-button>-->
<!--        <div v-if="getLike()">广场空空如也</div>-->
        <div class="study" v-for="(item,i) in words" v-bind:key="i">
            <div class="layui-card">
                <div class="layui-card-header">
                    <div class="word">{{item.word}}</div>
                    <div class="like">
                        <el-button @click.native="add(item.id)" type="primary" round>
                            添加到我的单词本</el-button>
<!--                        <mt-button @click.native="add(item.id)">添加到我的单词本</mt-button>-->
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
        name: "All",
        data(){
            return{
                words:this.DATA.allWords,
                loading:true
            }
        },
        beforeMount:function(){
            this.getAll();
        },
        methods:{
            add:function (id) {
                this.$axios.get('/word/addme',
                    {params:{userid:this.DATA.userId,wordid:id}}).then(
                    response =>{
                        if (response.data.message==='200'){
                            this.$message({
                                message: '添加成功',
                                type: 'success'
                            });
                            this.getAll();
                        }else {
                            this.$message('操作失败');
                        }
                    }
                )
            },
            getAll:function () {
                this.words=[];
                this.$axios.get('/word/all',{params:{id:this.DATA.userId}}).then(
                    response=>{
                        for (let i in response.data.allWords){
                            let a=response.data.allWords[i];
                            let w={id:a.id,word:a.word,tra:a.translate,more:a.exp};
                            this.words.push(w);
                        }
                        // this.words=this.DATA.allWords;
                        this.loading=false;
                    }
                )
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