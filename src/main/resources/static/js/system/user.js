var oTable;
function initUser() {

    oTable = new TableInit();
    oTable.Init();


    //初始化Button的点击事件
    $("#btn_query").click(function () {
        oTable.refresh();
    });

    $("#btn_add").click(function () {
        addUser();
    });


    initRG();
    initForm();

}


var rg_locked;
function initRG() {
    rg_locked = new createRadioGroup($('#user_locked'));
    var lockeds = [{value: true, text: '是'}, {value: false, text: '否'}];
    rg_locked.init('locked', lockeds);
}


function initForm() {
    initValidator();

    validatorForm = $('#user_form').validate({
        rules: {
            username: {
                required: true
            }
        },
        messages: {
            username: {
                required: "用户名不能为空"
            }
        },
        submitHandler: function (form) {
            doSave();
        }
    });
}

/**
 * 处理 并提交更新
 */
function doSave() {
    var tree = $.fn.zTree.getZTreeObj("treeRole");

    var changes = tree.getChangeCheckedNodes();
    var roles = [];
    changes.forEach(function (a) {
        var temp = new Object();
        temp.id = a.id;
        temp.add = a.checked;
        roles.push(temp);
    });

    var id = $("#user_id").val().trim();
    var username = $("#user_username").val().trim();
    var password = $("#user_password").val().trim();
    var locked = rg_locked.val();

    if (id == "") {
        var param = {
            username: username,
            password: password,
            roles: roles
        };
        $.ajax({
            type: "POST",
            url: hostUrl + "user/add",
            data: JSON.stringify(param),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            beforeSend: function () {
                $("#addUser_add").button("loading");
            },
            success: function (r) {
                $("#addUser_add").button("reset");

                if (r.state) {
                    toastr.info('添加成功');

                    showPanel(1);
                    oTable.refresh();
                } else {
                    toastr.error('添加失败');
                }
            },
            error: function (result) {
                $("#addUser_add").button("reset");
                toastr.error('发送请求失败');
            }
        });
    } else {
        var param = {
            id: id,
            username: username,
            password: password,
            locked: locked,
            roles: roles
        };
        $.ajax({
            type: "POST",
            url: hostUrl + "user/update",
            data: JSON.stringify(param),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            beforeSend: function () {
                $("#btn_save").button("loading");
            },
            success: function (r) {
                $("#btn_save").button("reset");

                if (r.state) {
                    toastr.info('修改成功');

                    showPanel(1);
                    oTable.refresh();
                } else {
                    toastr.error('修改失败');
                }
            },
            error: function (result) {
                $("#btn_save").button("reset");
                toastr.error('发送请求失败');
            }
        });
    }
}


/**
 * 添加用户
 */
function addUser() {
    showPanel(0, 'add');

    //获取权限 相关菜单和权限信息
    $.post(hostUrl + "role/list")
        .done(function (data) {

            var nodes = [];
            data.forEach(function (role) {
                var node = new Object();
                node.id = role.id;
                node.pId = 0;
                node.name = role.name;
                node.open = true;
                node.checkedOld = false;
                nodes.push(node)
            });


            //展现 菜单和权限信息
            $.fn.zTree.init($("#treeRole"), treeSetting, nodes);

        })
        .fail(function () {
            toastr.error('发送请求失败');
        });


}


/**
 * 查看 或者 修改用户信息
 * @param user
 * @param type
 */
function viewUser(user, type) {

    showPanel(0, type);

    //填入用户信息
    $("#user_id").val(user.id);
    $("#user_username").val(user.username);
    $("#user_password").val('');
    rg_locked.val(user.locked);


    //获取权限 相关菜单和权限信息
    $.post(hostUrl + "user/info", {"id": user.id})
        .done(function (r) {
            if (r.state) {
                var data = r.data;
                var roleIds = data.roleIds;

                var nodes = [];
                data.roles.forEach(function (role) {
                    var node = new Object();
                    node.id = role.id;
                    node.pId = 0;
                    node.name = role.name;
                    if ($.inArray(role.id, roleIds) != -1) {
                        node.checked = true;
                    }
                    node.checkedOld = node.checked;
                    nodes.push(node)
                });


                //展现 菜单和权限信息
                $.fn.zTree.init($("#treeRole"), treeSetting, nodes);
            } else {
                toastr.error('请求用户信息失败，请重试');
            }
        })
        .fail(function () {
            toastr.error('发送请求失败');
        });


}


function deleteUser(id) {
    editAlert('警告', '是否确定删除该条用户信息', '删除', function () {
        $.ajax({
            type: "POST",
            url: hostUrl + "user/delete",
            data: {"id": id},
            dataType: "json",
            success: function (r) {
                if (r.state) {
                    toastr.info('删除成功');
                    hideAlert();
                    oTable.refresh();
                } else {
                    toastr.error('删除失败');
                }
            },
            error: function (result) {
                toastr.error('发送请求失败');
            }
        });
    });
    showAlert();

}

//菜单ztree配置
var treeSetting = {
    check: {
        enable: true,
        chkDisabledInherit: true,
        chkboxType: {"Y": "p", "N": "s"}
    },
    data: {
        simpleData: {
            enable: true
        }
    }
};

//Table初始化
var TableInit = function () {
    var oTableInit = new Object();


    //初始化Table
    oTableInit.Init = function () {
        $('#table_user').bootstrapTable({
            url: hostUrl + 'user/list',         //请求后台的URL（*）
            method: 'post',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            contentType: 'application/x-www-form-urlencoded',
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            // search: true,                       //是否显示表格搜索
            strictSearch: false,                 //设置为 true启用 全匹配搜索，否则为模糊搜索
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 600,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                field: 'id',
                title: 'ID',
                width: 80
            }, {
                field: 'username',
                title: '用户名'
            }, {
                field: 'locked',
                title: '冻结',
                formatter: function (value) {
                    return value ? '是' : '否';
                }
            }, {
                field: 'operate',
                title: '操作',
                events: operateEvents,
                formatter: operateFormatter
            }]
        });


    };

    //操作 监听
    window.operateEvents = {
        'click .view': function (e, value, row, index) {
            viewUser(row, "view");
        },
        'click .edit': function (e, value, row, index) {
            viewUser(row, "edit");
        },
        'click .remove': function (e, value, row, index) {
            deleteUser(row.id);
        }
    };

    //操作显示format
    function operateFormatter(value, row, index) {
        return [
            '<button type="button" class="view btn btn-primary btn-xs">查看</button> \
             <button type="button" class="edit btn btn-info btn-xs">修改</button> \
             <button type="button" class="remove btn btn-danger btn-xs">删除</button>'
        ].join('');
    }

    //刷新数据
    oTableInit.refresh = function () {
        $('#table_user').bootstrapTable('refresh');
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            name: $("#input_search").val()
        };
        return temp;
    };
    return oTableInit;
};

function showPanel(a, b) {
    if (a) {
        titleContentHeader("用户管理");
        $("#form-panel").hide();
        $("#table-panel").show();
    } else {
        $("#form-panel").show();
        $("#table-panel").hide();

        var form = $("#user_form");
        form.resetForm();
        $.fn.zTree.destroy();
        rg_locked.reset();

        resetValidator(form);

        switch (b) {
            case 'add':
                titleContentHeader("新增用户");
                $('#btn_save').show();
                break;
            case 'view':
                titleContentHeader("查看用户");
                $('#btn_save').hide();
                break;
            case 'edit':
                titleContentHeader("修改用户");
                $('#btn_save').show();
                break;
        }
    }


}



