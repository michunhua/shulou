  //  辅助 layui
  layui.use('element', function(){
  var element = layui.element;
})



//  导航栏显示
// admin
var administrator = function(admins) {
  console.log('admin', localStorage.name)
  var name = localStorage.name
  // var name = document.querySelector('#admin').innerText
  console.log(name)
  var navigate = document.querySelector('#nav')
  var html = `<div class="layui-collapse">
    <div class="layui-colla-item">
      <h2 class="layui-colla-title">贷款管理</h2>
      <div class="layui-colla-content layui-show">
        <ul class="layui-nav layui-nav-tree"  lay-filter="test">
            <li class="layui-nav-item ">
                <a href="javascript:;">贷款创建</a>
                <dl class="layui-nav-child">
                    <dd><a href="../loanManagement/loanCreate/loanCreateTable.html">贷款创建列表</a></dd>
                    <dd><a href="../loanManagement/loanCreate/loanerInfo.html">借款申请人个人资料</a></dd>
                    <dd><a href="../loanManagement/loanCreate/commomLoanerInfo.html">共同申请人个人资料</a></dd>
                    <dd><a href="../loanManagement/loanCreate/loanerMate.html">借款申请人配偶</a></dd>
                    <dd><a href="../loanManagement/loanCreate/commomMate.html">共同申请人配偶</a></dd>
                    <dd><a href="../loanManagement/loanCreate/loanInfo.html">申请借款信息</a></dd>
                    <dd><a href="../loanManagement/loanCreate/estateInfo.html">房产资料</a></dd>
                    <dd><a href="../loanManagement/loanCreate/noteInfo.html">备注说明</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item ">
                <a href="javascript:;">贷款初审</a>
                <dl class="layui-nav-child">
                  <dd><a href="../loanManagement/loanFirst/loanFirstTable.html">贷款初审列表</a></dd>
                  <dd><a href="../loanManagement/loanFirst/loanerInfo.html">借款申请人个人资料</a></dd>
                  <dd><a href="../loanManagement/loanFirst/commomLoanerInfo.html">共同申请人个人资料</a></dd>
                  <dd><a href="../loanManagement/loanFirst/loanerMate.html">借款申请人配偶</a></dd>
                  <dd><a href="../loanManagement/loanFirst/commomMate.html">共同申请人配偶</a></dd>
                  <dd><a href="../loanManagement/loanFirst/loanInfo.html">申请借款信息</a></dd>
                  <dd><a href="../loanManagement/loanFirst/estateInfo.html">房产资料</a></dd>
                  <dd><a href="../loanManagement/loanFirst/noteInfo.html">初审备注</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item ">
                <a href="javascript:;">贷款终审</a>
                <dl class="layui-nav-child">
                  <dd><a href="../loanManagement/loanFinal/loanFinalTable.html">贷款终审列表</a></dd>
                  <dd><a href="../loanManagement/loanFinal/loanerInfo.html">借款申请人个人资料</a></dd>
                  <dd><a href="../loanManagement/loanFinal/commomLoanerInfo.html">共同申请人个人资料</a></dd>
                  <dd><a href="../loanManagement/loanFinal/loanerMate.html">借款申请人配偶</a></dd>
                  <dd><a href="../loanManagement/loanFinal/commomMate.html">共同申请人配偶</a></dd>
                  <dd><a href="../loanManagement/loanFinal/loanInfo.html">申请借款信息</a></dd>
                  <dd><a href="../loanManagement/loanFinal/estateInfo.html">房产资料</a></dd>
                  <dd><a href="../loanManagement/loanFinal/noteInfo.html">终审备注</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item ">
                <a href="../loanManagement/loanInfoRead.html">贷款信息查看</a>
            </li>
            <li class="layui-nav-item ">
                <a href="../loanManagement/repayConfirm.html">回款确认</a>
              </dl>
            </li>
            <li class="layui-nav-item ">
                <a href="../loanManagement/gainProof.html">取证凭证</a>
                </dl>
            </li>
            <li class="layui-nav-item ">
                <a href="../loanManagement/reliefProof.html">解押凭证</a>
                </dl>
            </li>
            <li class="layui-nav-item ">
                <a href="../loanManagement/incareProof.html">进押凭证</a>
            </li>
        </ul>
      </div>
    </div>
    <div class="layui-colla-item">
      <h2 class="layui-colla-title">财务管理</h2>
      <div class="layui-colla-content">
        <ul class="layui-nav layui-nav-tree"  lay-filter="test">
            <li class="layui-nav-item">
                <a href="javascript:;">财务审批</a>
                <dl class="layui-nav-child">
                    <dd><a href="../financeManagement/financeApproval/financeApproval.html">审批列表</a></dd>
                    <dd><a href="../financeManagement/financeApproval/financeNote.html">财务备注</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="../financeManagement/transferProof.html">转账凭证</a>
            </li>
            <li class="layui-nav-item">
                <a href="../financeManagement/clearProof.html">结算凭证</a>
            </li>
        </ul>
      </div>
    </div>
    <div class="layui-colla-item">
      <h2 class="layui-colla-title">个人信息</h2>
      <div class="layui-colla-content">
        <ul class="layui-nav layui-nav-tree"  lay-filter="test">
            <li class="layui-nav-item">
                <a href="../userInfo/updateUserInfo.html">个人信息修改</a>
            </li>
            <li class="layui-nav-item">
                <a href="../userInfo/updatePassword.html">密码修改</a>
            </li>
        </ul>
      </div>
    </div>
    <div class="layui-colla-item">
      <h2 class="layui-colla-title">用户管理</h2>
      <div class="layui-colla-content">
        <ul class="layui-nav layui-nav-tree"  lay-filter="test">
            <li class="layui-nav-item">
                <a href="javascript:;">用户</a>
                <dl class="layui-nav-child">
                    <dd><a href="../userManagement/user/userList.html">用户列表</a></dd>
                    <dd><a href="../userManagement/user/addUser.html">添加用户</a></dd>
                    <dd><a href="../userManagement/user/allotUser.html">分配角色</a></dd>
                    <dd><a href="../userManagement/user/deleteUser.html">删除用户</a></dd>
                    <dd><a href="../userManagement/user/updateUser.html">修改用户信息</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">角色</a>
                <dl class="layui-nav-child">
                    <dd><a href="../userManagement/role/roleList.html">角色列表</a></dd>
                    <dd><a href="../userInfo/role/addRole.html">添加角色</a></dd>
                    <dd><a href="../userInfo/role/updateRole.html">修改角色权限</a></dd>
                </dl>
            </li>
        </ul>
      </div>
    </div>
  </div>`
  if (name === admins) {
    console.log('innerHTML')
    // navigate.innerHTML = html
  }
}

// administrator('admin')
// city
var city = function(city) {
  var name = localStorage.name
  // var name = document.querySelector('#city').innerText
  var navigate = document.querySelector('#nav')
  var html = ''
  if (name === city) {

  }
}
// Mortage //navloan, navfinance, navuserInfo, navuserManagement
// tree-create
// tree-trival
// tree-final
// tree-read
// tree-confirm
// tree-gain
// tree-relief
// tree-incare
var mortage = function(mortage) {
  var name = localStorage.name
  // var name = document.querySelector('#mortage').innerText
  var navigate = document.querySelector('#nav')
  var navloan = document.querySelector('#navloan')
  var navfinance = document.querySelector('#navfinance')
  var navuserInfo = document.querySelector('#navuserInfo')
  var navuserManagement = document.querySelector('#navuserManagement')
  var treecreate = document.querySelector('#tree-create')
  var treetrival = document.querySelector('#tree-trival')
  var treefinal = document.querySelector('#tree-final')
  var treeread = document.querySelector('#tree-read')
  var treeconfirm = document.querySelector('#tree-confirm')
  var treegain = document.querySelector('#tree-gain')
  var treerelief = document.querySelector('#tree-relief')
  var treeincare = document.querySelector('#tree-incare')

  var html = ''
  console.log('name', name)
  if (name === mortage) {
    navfinance.innerHTML = null
    // navuserInfo.innerHTML = null
    navuserManagement.innerHTML = null
    // treecreate.innerHTML = null
    treetrival.innerHTML = null
    treefinal.innerHTML = null
    treeread.innerHTML = null
    treeconfirm.innerHTML = null
    treegain.innerHTML = null
    treerelief.innerHTML = null
    treeincare.innerHTML = null
  }
}
// First trial
var trial = function(first) {
  var name = localStorage.name
  // var name = document.querySelector('#first').innerText
  var navigate = document.querySelector('#nav')
  var navloan = document.querySelector('#navloan')
  var navfinance = document.querySelector('#navfinance')
  var navuserInfo = document.querySelector('#navuserInfo')
  var navuserManagement = document.querySelector('#navuserManagement')
  var treecreate = document.querySelector('#tree-create')
  var treetrival = document.querySelector('#tree-trival')
  var treefinal = document.querySelector('#tree-final')
  var treeread = document.querySelector('#tree-read')
  var treeconfirm = document.querySelector('#tree-confirm')
  var treegain = document.querySelector('#tree-gain')
  var treerelief = document.querySelector('#tree-relief')
  var treeincare = document.querySelector('#tree-incare')
  console.log('初审')
  var html = ''
  if(name === first) {
    navfinance.innerHTML = null
    // navuserInfo.innerHTML = null
    navuserManagement.innerHTML = null
    treecreate.innerHTML = null
    // treetrival.innerHTML = null
    treefinal.innerHTML = null
    treeread.innerHTML = null
    treeconfirm.innerHTML = null
    treegain.innerHTML = null
    treerelief.innerHTML = null
    treeincare.innerHTML = null
  }
}
// Final review
var review = function(final) {
  var name = localStorage.name
  // var name = document.querySelector('#final').innerText
  var navigate = document.querySelector('#nav')
  var navloan = document.querySelector('#navloan')
  var navfinance = document.querySelector('#navfinance')
  var navuserInfo = document.querySelector('#navuserInfo')
  var navuserManagement = document.querySelector('#navuserManagement')
  var treecreate = document.querySelector('#tree-create')
  var treetrival = document.querySelector('#tree-trival')
  var treefinal = document.querySelector('#tree-final')
  var treeread = document.querySelector('#tree-read')
  var treeconfirm = document.querySelector('#tree-confirm')
  var treegain = document.querySelector('#tree-gain')
  var treerelief = document.querySelector('#tree-relief')
  var treeincare = document.querySelector('#tree-incare')
  var html = ''
  if (name === final) {
    navfinance.innerHTML = null
    // navuserInfo.innerHTML = null
    navuserManagement.innerHTML = null
    treecreate.innerHTML = null
    treetrival.innerHTML = null
    // treefinal.innerHTML = null
    treeread.innerHTML = null
    treeconfirm.innerHTML = null
    treegain.innerHTML = null
    treerelief.innerHTML = null
    treeincare.innerHTML = null
  }
}
// Financial approval
var approval = function(finance) {
  var name = localStorage.name
  // var name = document.querySelector('#finance').innerText
  var navigate = document.querySelector('#nav')
  var navloan = document.querySelector('#navloan')
  var navfinance = document.querySelector('#navfinance')
  var navuserInfo = document.querySelector('#navuserInfo')
  var navuserManagement = document.querySelector('#navuserManagement')
  var html = ''
  if (name === finance) {
    navloan.innerHTML = null
    // navfinance.innerHTML = null
    // navuserInfo.innerHTML = null
    navuserManagement.innerHTML = null
  }
}
// Financial pay
// var pay = function(city) {
//   var name = document.querySelector('#pay').innerText
//   var navigate = document.querySelector('#nav')
// }

//  事件监听

var admin = 'admin'
// var btn1 = function() {
//   var findEle = document.querySelector('#admin')
//   findEle.addEventListener('click', function() {
//     firstPage()
    administrator(admin)
//   })
// }
//
// var btn2 = function() {
//   var findEle = document.querySelector('#city')
//   findEle.addEventListener('click', function() {
//     firstPage()
    city('城市经理')
//   })
// }
//
// var btn3 = function() {
//   var findEle = document.querySelector('#mortage')
//   findEle.addEventListener('click', function() {
//     firstPage()
    mortage('按揭员')
//   })
// }
//
// var btn4 = function() {
//   var findEle = document.querySelector('#first')
//   findEle.addEventListener('click', function() {
//     firstPage()
    trial('初审')
//   })
// }
//
// var btn5 = function() {
//   var findEle = document.querySelector('#final')
//   findEle.addEventListener('click', function() {
//     firstPage()
    review('终审')
//   })
// }
//
// var btn6 = function() {
//   var findEle = document.querySelector('#finance')
//   findEle.addEventListener('click', function() {
//     firstPage()
    approval('审批财务')
//   })
// }
//
//
// btn1()
// btn2()
// btn3()
// btn4()
// btn5()
// btn6()
