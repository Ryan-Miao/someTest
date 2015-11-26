# someTest

My learning  
在工作中遇到一些问题，然后进行的一些测试  
1.从远程库clone到本地，本地会新建一个git库，主分为master。 
  
2.主分支是master,要稳定，安全。所以平时都去dev开发？  
   创建了dev之后，开发，提交。但是master忘记合并了。
   然后，用户1:checkout master, merge dev, and then  push. thus, master is 2.0   
   用户2： first pull dev，then push dev, then  checkout master,pull,push, thus,master is 3.0  
   实验了几次，当冲突的时候不知所措，稀里糊涂的弄好。最后，反正主代码没问题。大概也搞清楚了用法。
   注意几点：  
        a:先pull,保证你的版本不会落后
        b:pull之后有冲突修改好  
        c:add  -> commit ->push   
        


