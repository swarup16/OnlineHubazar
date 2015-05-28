/**
 * 
 */

var cartApp = angular.module('cartApp', []);
cartApp.controller('cartCtrl', function($scope, $http) {
    $scope.refreshCart = function(cartId) {
        $http.get('/hutbazzar/rest/cart/' + $scope.cartId)
                .success(function(data) {
                    $scope.cart = data;
                });
    };
    $scope.clearCart = function() {
        $http.delete('/hutbazzar/rest/cart/' + $scope.cartId)
                .success($scope.refreshCart($scope.cartId));
    };
    $scope.initCartId = function(cartId) {
        $scope.cartId = cartId;
        $scope.refreshCart($scope.cartId);
    };
    $scope.addToCart = function(id) {
        $http.put('/hutbazzar/rest/cart/add/' + id)
                .success(function(data) {

                    //$scope.refreshCart($http.get('/hutbazzar/rest/cart/get/cartId'));
                    alert("Product Successfully added to the Cart!");
                });
    };
    $scope.removeFromCart = function(id) {
        $http.put('/hutbazzar/rest/cart/remove/' + id)
                .success(function(data) {
                    $scope.refreshCart($http.get('/hutbazzar/rest/cart/get/cartId'));
                });
    };
});