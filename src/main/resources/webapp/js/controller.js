var diskApp = angular.module('DiskApp',['ngCookies']);
diskApp.controller("DisksListCtrl", function($cookieStore, $scope, $http, filterFilter) {
  $scope.disks = [];
  $scope.cart = [];

  $scope.isList = true;
  $scope.isCart = false;
  $scope.isLogged = false;
  $scope.isNotLogged = !$scope.isLogged;
  $scope.number = 10;
  $scope.totalPrice = 0.0;
  $scope.disk = {price:10.0, number:0}

  $scope.cartSize = 0;
  $scope.cartNotEmpty = false;
  $scope.isAdmin = false;
  $scope.username = "";

  $scope.init = function(){
    checkLoginStatus();
    getCartSize();
    getDisks();
  }


  $scope.goToCart = function() {
    $scope.isList = false;
    $scope.isCart = true;

    $http({
      method: 'GET',
      url: '/cart'
          }).success(function(data) {
            alert(data);
            $scope.cart = data;
          }).error(function(error) {
    });
  };

  $scope.goToList = function() {
      $scope.isList = true;
      $scope.isCart = false;
  };


  $scope.addDisk = function() {
      $http({
        method: 'POST',
        url: '/disks/add',
        data: JSON.stringify($scope.disk),
        contentType: "application/json"
      }).success(function(){
        getDisks();
      }
      );
    };

  $scope.removeDisk = function(disk) {
    $http({
      method: 'DELETE',
      url: '/disks/remove/' + getIndexOfDisk(disk)
    }).success(function(){
      getDisks();
    });

  };

  $scope.addToCart = function(disk) {
    disk.number = $scope.number;
    $http({
      method: 'POST',
      url: '/cart/add',
      data: JSON.stringify(_.omit(disk,"$$hashKey")),
      contentType: "application/json"
    }).success(function(){
    });
  };

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

    $scope.caculateTotalPrice();
  };

  $scope.caculateTotalPrice = function() {
    var checkBoxes = $(".check-self");

    $scope.totalPrice = 0.0;
    for(var i = 0; i < checkBoxes.length; i++)
      {
        if(checkBoxes[i].checked)
          {
            $scope.totalPrice += $scope.cart[i].number * $scope.cart[i].price;
          }
      }
    };

  function getIndexOfDisk(disk) {
    return $scope.disks.indexOf(disk);
  };
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
  function getCartSize(){
    $http({
          method: 'GET',
          url: '/cart/query',
          params: {username: $scope.username}
        }).success(function(data) {
          console.log("cartDetail:"+data);
          if(data.length>0){
            $scope.cartNotEmpty = true;
            $scope.cartSize = data.length;
          }

        }).error(function(error) {
            $scope.cartNotEmpty = true;
        });
  }

  function getDisks() {
    $http({
          method: 'GET',
          url: '/disks/list'
        }).success(function(data) {
          $scope.disks = data;
        }).error(function(error) {
    });
  };

});