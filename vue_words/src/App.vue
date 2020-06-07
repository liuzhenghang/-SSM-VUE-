<template>
    <div id="app">
        <el-container>
            <el-header>
                <mt-header v-show="!loginBox" fixed id="header" title="我的收藏">
                    <mt-button @click.native="openAddWord()" v-show="!loginBox" class="el-icon-plus" slot="left">添加单词</mt-button>
                    <mt-button @click.native="openTop()" icon="more" slot="right"></mt-button>
                </mt-header>
            </el-header>
                <el-main>
                    <router-view></router-view>
                </el-main>
        </el-container>
        <el-drawer id="cd"
                title="菜单"
                :visible.sync="drawer"
                :direction="direction"
                :with-header="false">
            <br/><br/><br/><br/><br/><br/><br/>
            <el-menu
                    default-active="2"
                    class="el-menu-vertical-demo">
                <el-menu-item @click.native="closeTable()" index="1">
                    <i class="el-icon-menu"></i>
                    <span slot="title"><router-link to="/home">学习空间</router-link></span>
                </el-menu-item>
                <el-menu-item @click.native="closeTable()" index="2">
                    <i class="el-icon-menu"></i>
                    <span slot="title"><router-link to="/like">收藏面板</router-link></span>
                </el-menu-item>
                <el-menu-item @click.native="closeTable()" index="3">
                    <i class="el-icon-menu"></i>
                    <span slot="title"><router-link to="/all">单词广场</router-link></span>
                </el-menu-item>
                <el-menu-item @click.native="closeTable()" index="4">
                    <i class="el-icon-menu"></i>
                    <span slot="title"><router-link to="/his">历史记录</router-link></span>
                </el-menu-item>
                <el-menu-item @click.native="closeTable()" index="5">
                    <i class="el-icon-menu"></i>
                    <span slot="title"><router-link to="/mine">个人空间</router-link></span>
                </el-menu-item>
            </el-menu>
        </el-drawer>

        <el-drawer
                title="登录"
                :visible.sync="loginBox"
                :direction="loginBoxRTA"
                :before-close="loginClose"
                :with-header="false">
            <span>登录/注册</span>
            <el-input class="input" v-model="username" placeholder="请输入账号"></el-input>
            <el-input class="input" placeholder="请输入密码" v-model="password" show-password></el-input>
            <div style="width: 70%;text-align: center;margin: auto">
                <el-button @click.native="login()" type="success" round>登录</el-button>
                <el-button @click.native="register()" type="primary" round>注册</el-button>
            </div>
        </el-drawer>

        <el-drawer style="padding-top: 30px"
                title="添加单词"
                :visible.sync="addWord"
                :direction="addWordBoxRTA"
                :with-header="false">
<!--            <span>添加单词</span>-->


            <div style="width: 70%;text-align: center;margin: auto">
                <el-steps :active="active" finish-status="success">
                    <el-step title="输入单词"></el-step>
                    <el-step title="输入读音"></el-step>
                    <el-step title="输入翻译"></el-step>
                    <el-step title="输入例子"></el-step>
                </el-steps>
                <el-input v-show="active===0" class="input" v-model="newWord.word" placeholder="请输入单词"></el-input>
                <el-input v-show="active===1" class="input" v-model="newWord.speak" placeholder="请输入读音"></el-input>
                <el-input v-show="active===2" class="input" v-model="newWord.tra" placeholder="请输入翻译"></el-input>
                <el-input v-show="active===3" class="input" v-model="newWord.exp" placeholder="请输入相关例子"></el-input>
                <el-button @click.native="addWordBtn()" type="success" round>{{activeText}}</el-button>
            </div>
        </el-drawer>
    </div>
</template>
<script>
    import { Loading } from 'element-ui';
export default {
  name: 'App',
    activated:function () {
        console.log("切换")
    },
    data(){
        return{
            drawer:false,
            direction: 'rtl',
            loading:this.DATA.loading,
            loginBox:false,
            loginBoxRTA:'btt',
            username:'',
            password:'',
            addWord:false,
            addWordBoxRTA:'btt',
            active:0,
            activeText:'下一步',

            word:'',
            speak:'',
            tra:'',
            exp:'',
            newWord:{word:'',speak:'',tra:'',exp:''}
        }
    },
    beforeMount:function () {
      if(localStorage.getItem('id')==='0' || localStorage.getItem('id')===null){
          this.loginBox=true;
      }else {
          this.DATA.userId=localStorage.getItem('id');
          this.DATA.user=localStorage.getItem('user');
      }
        if (this.DATA.userId===0){
            this.loginBox=true;
        }
    },
    methods:{
      openTop:function () {
            this.drawer=true
      },
        closeTable:function () {
            this.drawer=false
        },
        loginClose:function () {
            if (this.DATA.userId===0){
                this.$alert('你还未登录', '无法关闭', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$message({
                            type: 'info',
                            message: `action: ${ action }`
                        });
                    }
                });
                return false;
            }
        },
        login:function () {
          let b=Loading.service('正在登陆');
            this.$axios.get('user/login',
                {params:{username:this.username,password:this.password}}).then(
                response =>{
                    if (response.data.id===0){
                        this.$message.error('用户名或密码错误');
                        b.close();
                    }else {
                        this.$message({
                            message: '登陆成功  ',
                            type: 'success'
                        });
                        localStorage.setItem('id',response.data.id);
                        localStorage.setItem('user',this.username);
                        b.close();
                        this.loginBox=false;
                        this.DATA.userId=response.data.id;
                        location.reload()
                    }
                })
        },
        register:function () {
          if (this.username==='' || this.password===''){
              this.$message.error('用户名或密码不能为空');
              return false;
          }
            Loading.service('正在注册');
            this.$axios.get('user/register',
                {params:{username:this.username,password:this.password}}).then(
                response =>{
                    if (response.data.id===0){
                        this.$message.error('用户名重复');
                    }else {
                        this.$message({
                            message: '注册成功',
                            type: 'success'
                        });
                        location.reload();
                    }
                })
        },
        openAddWord:function () {
            this.addWord=true;
        },
        success:function () {
            let w=this.newWord;
            this.$axios.get('word/add',
                {params:{word:w.word,speak:w.speak,translate:w.tra,exp:w.exp,userid:this.DATA.userId}}).then(
                response =>{
                    if (response.data.message==='404'){
                        this.$message.error('添加失败');
                    }else if(response.data.message==='200'){
                        this.$message({
                            message: '添加成功',
                            type: 'success'
                        });
                        location.reload();
                    }
                })
        },
        addWordBtn:function () {
          let error=false;
          switch (this.active) {
            case 0:if (this.newWord.word==='') error=true;break;
            case 1:if (this.newWord.speak==='') error=true;break;
            case 2:if (this.newWord.tra==='') error=true;break;
            case 3:if (this.newWord.exp==='') error=true;break;
          }
          if (error){
              this.$message.error('不能为空');
              return false;
          }
            if (this.active<3) {
                this.active++;
            }else if (this.active===3){
                this.success();
            }
            if (this.active===3){
                this.activeText='提交！'
            }
        }
    }
}
</script>

<style>
    body{
        width: 100%;
        height: 100%;
        padding: 0;
        margin: 0;
    }
    .bg{
        width: 100%;
        height: 100%;
    }
    #cd{
        z-index: 99999;
    }
    .home{
        width: 90%;
        margin: auto;
        height: 100%;
    }
    body{
        /*background: #9F9F9F;*/
        background: url("https://api.moleft.cn/bing/images.php");
    }
    .input{
        margin-top: 10px;
    }
</style>
