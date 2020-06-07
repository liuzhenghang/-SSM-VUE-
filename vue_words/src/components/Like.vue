<template>
    <div class="home">
<!--        <mt-header fixed="true" id="header" title="我的收藏">-->
<!--            <mt-button icon="more" slot="right"></mt-button>-->
<!--        </mt-header>-->
<!--        <el-page-header title="" content="我的收藏">-->
<!--        </el-page-header>-->
        <div v-if="ifNull()">您还没有收藏，多多加油噢</div>
        <div class="study" v-for="(item,i) in words" v-bind:key="i">
            <div class="layui-card" name="el-fade-in-linear">
                <div class="layui-card-header">
                    <div class="word">{{item.word}}</div>
                    <div class="like">
<!--                        <mt-button @click.native="stars(item.id)">取消收藏</mt-button>-->
                        <el-button @click.native="stars(item.id)"
                                   type="danger" icon="el-icon-delete" circle></el-button>
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
        name: "Like",
        data(){
            return{
                words:[]
            }
        },
        beforeMount:function () {
            this.getLike();
        },
        methods:{
            getLike:function () {
                this.loading=true;
                this.DATA.words=[];
                this.$axios.get('word/my',{params:{id:this.DATA.userId}}).then(
                    response =>{
                        // let word=[];
                        for (let i in response.data.myWords){
                            let w=response.data.myWords[i];
                            if (w.collection){
                                this.words.push({id:w.id,word:w.word,
                                    like:w.collection,speak:w.speak,tra:w.translate,more:w.exp})
                            }
                        }
                        this.loading=false;
                    })
            },
            stars:function (id) {
                this.DATA.star(this,id);
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