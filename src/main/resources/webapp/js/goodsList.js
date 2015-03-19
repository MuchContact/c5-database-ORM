angular.module('DiskApp',['ngCookies']).controller("GoodsCtrl", function($cookieStore, $scope, $http, filterFilter) {
  $scope.isAdmin = false;
  $scope.username = "";
  $scope.cartSize = 0;
  $scope.cartNotEmpty = false;
  $scope.isLogged = false;
  $scope.isNotLogged = !$scope.isLogged;
  $scope.cart = [];
  $scope.disks = [];

  $scope.init = function(){
    checkLoginStatus();
    getGoods();
  }
  function checkLoginStatus(){
    if($cookieStore.get('logged')){
         $scope.isLogged = true;
         $scope.isNotLogged = !$scope.isLogged;
         if($cookieStore.get('userType') > 0){
             $scope.isAdmin = true;
         }else{
             $scope.isAdmin = false;
         }
         $scope.username = $cookieStore.get('currentUser');
    }
  }
  function getGoods(){

    $http({
      method: 'GET',
      url: '/disks/list'
    }).success(function(data) {
      $scope.disks = data;
    }).error(function(error) {
      console.log(error);
    });
  }
  $scope.isAllChecked = function(){
    var checkBoxes = $(".check-self");
    var flag = 0;
    angular.forEach(checkBoxes, function(checkbox) {
      if(checkbox.checked) {
        flag++;
      }
    });

    if(flag == checkBoxes.length) {
      $('.check-all')[0].checked = true;
    }
    else {
      $('.check-all')[0].checked = false;
    }

  }

 });