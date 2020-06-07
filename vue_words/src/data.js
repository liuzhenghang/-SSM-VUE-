const api = 'http://localhost/Words_war_exploded/'
const token = '123456'
const user = '林花落了春红，太匆匆'
const userId = 0
const share=true
const loading=true;
const words=[]
const allWords=[]
const stu=[]


function star(m,id) {
    const w=this.words;
    for(let i in w){
        if (w[i].id===id){
            if (w[i].like){
                this.$axios.get('/word/unLike',{params:{userid:this.userid,wordid:id}}).then(
                    response =>{
                        if (response.data.message==='200'){
                            m.$message('已取消star');
                        }else {
                            m.$message('操作失败');
                        }
                    }
                )
                // m.$toast('已取消star');
                // this.words[i].like=false;
            }else {
                this.$axios.get('/word/like',{params:{userid:this.userid,wordid:id}}).then(
                    response =>{
                        if (response.data.message==='200'){
                            m.$message({
                                message: '棒极了，又收藏一个单词',
                                type: 'success'
                            });
                        }else {
                            m.$message('操作失败');
                        }
                    }
                )
                // m.$message({
                //     message: '棒极了，又收藏一个单词',
                //     type: 'success'
                // });
                // m.$toast('棒极了，又收藏一个单词');
                // this.words[i].like=true;
            }
        }
    }
}

function add(m,id) {
    const w=this.allWords;
    // this.$axios.get('/word/')
    for(let i in w){
        if (w[i].id===id){
            this.words.push(w[i]);
            this.allWords.splice(i,1);
            // m.$toast('已添加到我的单词本');
            m.$message({
                message: '已添加到我的单词本',
                type: 'success'
            });
            return;
        }
    }
}

function study(m,id) {
    const w=this.words;
    for(let i in w){
        if (w[i].id===id){
            this.stu.push(w[i]);
            this.words.splice(i,1);
            // m.$toast('又学会一个单词！');
            m.$message({
                message: '又学会一个单词！',
                type: 'success'
            });
            return;
        }
    }
}

function dWord(m,id) {
    const w=this.stu;
    for(let i in w){
        if (w[i].id===id){
            this.stu.splice(i,1);
            // m.$toast('解脱+1！');
            m.$message({
                message: '解脱+1！！',
                type: 'success'
            });
            return;
        }
    }
}

function getAll() {
    const url=this.api+"/word/all";
    this.allWords=[];
    this.$http.jsonp(url,null).then(function (res) {
        for (let i in res.data.allWords){
            let a=res.data.allWords[i];
            // {id:7,word:'demo',like:false,speak:'daimo',tra:'这是demo的翻译',more:'这是对demo的理解'},
            let w={id:a.id,word:a.word,tra:a.translate,more:a.exp};
            this.allWords.push(w);
        }
    })
}

export default {
    allWords,
    api,
    token,
    user,
    userId,
    words,
    stu,
    share,loading,

    star,
    add,
    study,dWord,getAll
}