angular.module('DiskApp',['ngCookies']).controller("CartCtrl", function($cookieStore, $scope, $http, filterFilter) {
  $scope.isAdmin = false;
  $scope.username = "";
  $scope.cartSize = 0;
  $scope.cartNotEmpty = false;
  $scope.isLogged = false;
  $scope.isNotLogged = !$scope.isLogged;
  $scope.cart = [];

  $scope.init = function(){
    checkLoginStatus();
    getCart();
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
  function getCart(){
    $http({
        method: 'GET',
        url: '/cart/query',
        params: {username: $scope.username}
      }).success(function(data) {
        if(data.length>0){
            $scope.cart = data;
            $scope.cartNotEmpty = true;
            $scope.cartSize = data.length;
        }

      }).error(function(error) {
          $scope.cartNotEmpty = true;
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

    $scope.calculateTotalPrice();
  }

  $scope.calculateTotalPrice = function() {
    var checkBoxes = $(".check-self");

    $scope.totalPrice = 0.0;
    for(var i = 0; i < checkBoxes.length; i++)
      {
        if(checkBoxes[i].checked)
          {
            $scope.totalPrice += $scope.cart[i].quantity * $scope.cart[i].disk.discountedPrice;
          }
      }
  };
 });