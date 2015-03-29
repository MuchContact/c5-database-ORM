angular.module('LoginModule',['ngCookies']).controller("UserCtrl", function($cookieStore, $scope, $http, $window, $location, filterFilter) {
    //if the followed definition is not set then scope.user is not accessible in function initialize
    $scope.user = {};
    $scope.userForRegister = {};
    $scope.showLoginForm = true;
    $scope.loginError = false;
    $scope.isUsernameExisted = true;
    $scope.acceptAgreement= false;

   $scope.initialize = function(){
        var user = $cookieStore.get('currentUser');
        var pass = $cookieStore.get('password');
        if(user!=undefined){
            $scope.user.username = user;
        }
        if(pass!=undefined){
            $scope.user.password = pass;
        }
   }
   $scope.go2Register = function(){
    $scope.showLoginForm = false;
    $scope.isUsernameExisted = false;
   }
   $scope.go2Login = function(){
    $scope.showLoginForm = true;
    $scope.isUsernameExisted = true;
   }
   $scope.login=function(user){
      $scope.user.password = $.md5($scope.user.password);
      $http({
         method: 'POST',
         url: '/user/login',
         data: $scope.user,
         contentType: "application/json"
       }).success(function(response){
         $scope.loginError = false;
         $cookieStore.put('currentUser',response.username);
         $cookieStore.put('password',response.password);
         $cookieStore.put('userType',response.type);
         $cookieStore.put('logged',true);
         $window.location.href = $location.path('/').absUrl().replace('login.html','home.html');
       }).error(function(data){
        $cookieStore.put('logged',false);
        $scope.loginError = true;
       });
   }
   $scope.register = function(user){
      $scope.userForRegister.password = $.md5($scope.userForRegister.password);
      $http({
         method: 'POST',
         url: '/user/register',
         data: $scope.userForRegister,
         contentType: "application/json"
      }).success(function(response){
        if(response>0){
            $scope.go2Login();
        }else{
            alert("注册失败");
        }
      }).error(function(data){
        alert(data);
      });
   }
   $scope.validateUserInfo = function(){
        $scope.loginError = false;
   }
   $scope.checkUserName = function($event){
    var target = $event.target;
    var val = $(target).val();
    $http({
         method: 'GET',
         url: '/user/checkUsername',
         params: {username: val},
         contentType: "application/json"
      }).success(function(response){
        $scope.isUsernameExisted = response;
        validateView();
      }).error(function(data){
      });
   }
   $scope.checkAgreement = function($event){
        var checkbox = $event.target;
        $scope.acceptAgreement = checkbox.checked;
        validateView();
   }

   function validateView(){
        if($scope.acceptAgreement
            &&$scope.isConfirmedPasswordRight
            &&!$scope.isUsernameExisted){
            enabledRegisterSubmit();
            return;
        }
        disabledRegisterSubmit();
   }
   function disabledRegisterSubmit(){
        $("#register-btn").attr("disabled", true);
        $('#register-btn').attr("class", "submitDisabled");
   }
   function enabledRegisterSubmit(){
        $('#register-btn').attr("disabled", false);
        $('#register-btn').attr("class", "submitNormal");
   }

   $scope.isConfirmedPasswordRight = true;
   $scope.checkConfirmedPassword = function(){
        if($('#confirmPassword').val()!=$('#password-reg').val()){
            $scope.isConfirmedPasswordRight = false;
        }else{
            $scope.isConfirmedPasswordRight = true;
        }
        validateView();
   }
});
