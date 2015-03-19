angular.module('LoginModule',['ngCookies']).controller("UserCtrl", function($cookieStore, $scope, $http, $window, $location, filterFilter) {
    //if the followed definition is not set then scope.user is not accessible in function initialize
    $scope.user = {};
    $scope.login = true;

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
    $scope.login = false;
   }
   $scope.go2Login = function(){
    $scope.login = true;
   }
   $scope.login=function(user){
      $http({
         method: 'POST',
         url: '/user/login',
         data: $scope.user,
         contentType: "application/json"
       }).success(function(response){
         $cookieStore.put('currentUser',response.username);
         $cookieStore.put('password',response.password);
         $cookieStore.put('userType',response.type);
         $cookieStore.put('logged',true);
         $window.location.href = $location.path('/').absUrl().replace('login.html','home.html');
       }).error(function(data){
        $cookieStore.put('logged',false);
        alert('认证失败！');
       });
   }
   $scope.register = function(user){
      $http({
         method: 'POST',
         url: '/user/register',
         data: $scope.user,
         contentType: "application/json"
      }).success(function(response){

      }).error(function(data){
      });
   }
});