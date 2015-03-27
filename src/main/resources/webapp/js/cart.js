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
  function mergeCartResponse(response){
        var group = _.groupBy(response, function(item){ return item.disk.id; });
        return  _.map(group, function(item){ item[0].quantity = _.size(item); return item[0] });
  }
  function getCart(){
    $http({
        method: 'GET',
        url: '/cart/query',
        params: {username: $scope.username}
      }).success(function(data) {
        if(data.length>0){
            $scope.cart = mergeCartResponse(data);
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
    function validateCartSize(){
        var size=0;
        _.each($scope.cart, function(item){size+item.quantity});
        return size;
    }
  $scope.deleteFromCart = function(id){
    $http({
          method: 'POST',
          url: '/cart/delete',
          data: {diskId: id, username: $scope.username},
          contentType: "application/json"
        }).success(function(){
            $scope.cart = _.filter($scope.cart, function(item){ return item.disk.id != id; });
            console.log($scope.cart);
            validateCartSize();
            console.log($scope.cartSize);
        });
  }
  function getCheckedDiskIds(){
    var checkBoxes = $(".check-self");
    var result = [];
    _.each(checkBoxes, function(checkbox) {
         if(checkbox.checked) {
           result.push(checkbox.value);
         }
       });
    return result;
  }
  $scope.makeOrder = function(){
    var disks = getCheckedDiskIds();
    $http({
          method: 'GET',
          url: '/cart/order',
          params: {diskIds: disks.toString(), username: $scope.username},
          contentType: "application/json"
        }).success(function(){
            $scope.cart = _.filter($scope.cart, function(item){
                console.log(item);
                return _.indexOf(disks, ''+item.disk.id)<0;
            });
            console.log($scope.cart);
            validateCartSize();
            console.log($scope.cartSize);
        });
  }
 });