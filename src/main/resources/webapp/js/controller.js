var diskApp = angular.module('DiskApp',['ngCookies']);
diskApp.controller("DisksListCtrl", function($cookieStore, $scope, $http, filterFilter) {
  $scope.disks = [];

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
    console.log(disk);
    disk.number = $scope.number;
    //data: JSON.stringify(_.omit(disk,"$$hashKey")),
    $http({
      method: 'POST',
      url: '/cart/addToCart',
      data: {diskId: disk.id, username: $scope.username},
      contentType: "application/json"
    }).success(function(){
        $scope.cartSize++;
    });
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