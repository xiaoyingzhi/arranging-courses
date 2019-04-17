// +----------------------------------------------------------------------
// | 列表页通用引用文件（如有特殊需求可参照此文件写一个单独的js文件,如:sys_config.js）
// +----------------------------------------------------------------------
layui.use(['element', 'layer'], function () {
  var element = layui.element;
  var layer = layui.layer;
  var $ = layui.jquery;
  // +----------------------------------------------------------------------
  // | icheck
  // +----------------------------------------------------------------------
  $('input').iCheck({
    checkboxClass: 'icheckbox_minimal-red',
    radioClass: 'iradio_minimal-red',
    increaseArea: '20%' // optional
  });//checkbox美化
  $('.selectAll input').on('ifChecked', function (event) {
    $('input').iCheck('check');
  });//全选
  $('.selectAll input').on('ifUnchecked', function (event) {
    $('input').iCheck('uncheck');
  });//反选
  // +----------------------------------------------------------------------
  // | 弹出层
  // +----------------------------------------------------------------------
  $(".dw-dailog").click(function () {
    var dw_url = $(this).attr("dw-url");//URL地址，必填
    var dw_title = $(this).attr("dw-title");//弹出层标题，必填
    var dw_width = $(this).attr("dw-width");//弹出层宽度，如80%或500px；如果没有默认为屏幕宽度的50%
    var dw_height = $(this).attr("dw-height");//弹出层高度，如50%或500px；如果没有默认为屏幕高度的50%
    if (dw_url == undefined) {
      layer.msg("请给button加上dw-url属性");
      return false;
    }
    if (dw_title == undefined) {
      layer.msg("请给button加上dw-title属性");
      return false;
    }
    if (dw_width == undefined) dw_width = '50%';
    if (dw_height == undefined) dw_height = '50%';
    layer.open({
      type: 2,
      title: dw_title,
      shadeClose: true,
      shade: 0.8,
      area: [dw_width, dw_height],
      content: dw_url,
      cancel: function (index, layero) {
        $(".dw-refresh").trigger('click');
        return false;
      }
    });
  });
  // +----------------------------------------------------------------------
  // | 批量删除
  // +----------------------------------------------------------------------
  $(".dw-batch-delete").click(function () {
    var dw_url = $(this).attr("dw-url");//URL地址，必填
    if (dw_url == undefined) {
      layer.msg("请给button加上dw-url属性");
      return false;
    }
    var chk_value = [];
    $('input[name="id"]:checked').each(function () {
      chk_value.push($(this).val());
    });
    if (chk_value.length == 0) {
      layer.msg("请选择要删除的数据!", { anim: 1 });
      return false;
    }
    layer.confirm('确定要删除选中的数据吗?', { icon: 3, title: '提示' }, function (index) {
      $.ajax(dw_url, { id: chk_value }, function (obj) {
        if (obj.code == 1) {
          layer.msg(obj.msg, { anim: 1 });
          $(".dw-refresh").trigger('click');
        } else {
          layer.msg(obj.msg, { anim: 1 });
        }
      });
      layer.close(index);
    });
  });
  // +----------------------------------------------------------------------
  // | 删除
  // +----------------------------------------------------------------------
  $(".dw-delete").click(function () {
    var dw_url = $(this).attr("dw-url");//URL地址，必填
    var dw_title = $(this).attr("dw-title");//删除数据标识，如姓名，默认为'该数据'
    if (dw_url == undefined) {
      layer.msg("请给button加上dw-url属性");
      return false;
    }
    if (dw_title == undefined) {
      dw_title = '该数据';
    }
    layer.confirm('确定要删除[' + dw_title + ']吗?', { icon: 3, title: '提示' }, function (index) {
      $.ajax(dw_url, {}, function (obj) {
        if (obj.code == 1) {
          layer.msg(obj.msg, { anim: 1 });
          $(".dw-refresh").trigger('click');
        } else {
          layer.msg(obj.msg, { anim: 1 });
        }
      });
      layer.close(index);
    });
  });
  // +----------------------------------------------------------------------
  // | 刷新
  // +----------------------------------------------------------------------
  $(".dw-refresh").click(function () {
    location.href = location.href;
  });
});