// +----------------------------------------------------------------------
// | 表单提交页面通用表单监听（如有特殊需求可参照此文件写一个单独的js文件，如:sys_config.js）
// +----------------------------------------------------------------------
layui.use(['element','form'], function(){
    var element = layui.element;
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function(data){
        var action = data.form.action;//表单提交URL地址
        console.log(JSON.stringify(data.field));//表单数据
        $.post(action,data.field,function(obj){
            //根据返回结果作出相应处理
        });
        return false;//注释掉这行代码后，表单将会以普通方式提交表单，否则以ajax方式提交表单
    });
});