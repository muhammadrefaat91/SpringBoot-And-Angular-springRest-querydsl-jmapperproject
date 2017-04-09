(function() {
	var app = angular.module('customerModule', []);
	
	
	app.controller('AddCustomerController', ['$http', '$scope','$timeout', function($http, $scope, $timeout){
		this.submit = function() {
			$scope.errorMessage="";
			$scope.successMessage="";
			var customerObj = {"id":$scope.id, "firstName": $scope.firstName, "lastName": $scope.lastName, "mobileNumber": $scope.mobileNumber,
					"address": $scope.address, "email": $scope.email};
		var customerJson = JSON.stringify(customerObj);
			if($scope.id === null) {
	 			$http.post('customer/add', customerJson)
				.then(function successCallback(response) {
					$scope.successMessage = response.data.successMessage;
					resetForm();
					$timeout(function () { $scope.successMessage=null; }, 2000); 
				}, 
				function errorCallback(response) {
					$scope.errorMessage = response.data.message;
					$timeout(function () { $scope.errorMessage=null; }, 2000)
				});
			} else if($scope.id != null) {
 				$http.post('customer/edit', customerJson)
				.then(function successCallback(response) {
					$scope.successMessage = response.data.successMessage;
					$scope.id=null;
					resetForm();
					$timeout(function () { $scope.successMessage=null; }, 2000); 
				}, 
				function errorCallback(response) {
					$scope.errorMessage = response.data.message;
			         $timeout(function () { $scope.errorMessage=null; }, 2000);   

				});
			}
			
		};
		
		var resetForm = function() {
			$scope.firstName=null;
			$scope.lastName=null;
			$scope.mobileNumber=null;
			$scope.email=null;
			$scope.address=null;
		};
		
		//find a customer 
		this.find =  function(id) { 
		$http.get('customer/find', {params:{id:id}})
		.then(function successCallback(response) {
			$scope.lastName =  response.data.lastName;
			$scope.email =  response.data.email;
			$scope.id =  response.data.id;
			$scope.mobileNumber =  Number(response.data.mobileNumber);
			$scope.address =  response.data.address;
			$scope.firstName = response.data.firstName;
		});
	 };
	}]);

	
	// search for customers
	
	app.controller('searchCustomerController', ['$http', '$scope',  '$location',   function($http, $scope, $location, $timeout){
		var customers={};
		var customer={};
		
		$scope.searchforCustomers = function() {
		 
			$http.get('customer/search', {params:{"firstName": $scope.sfirstName, "lastName": $scope.slastName,
				"email": $scope.semail, "mobileNumber": $scope.smobileNumber}})
			 
			.then(function successCallback(response) {
				$scope.customers = response.data;
			});
		};
		
		// delete a customer
		$scope.deleteCustomer = function($index,id) {
			$http.delete('customer/delete', {params:{"id": id}})
			.then(function successCallback(response) {
				  $scope.customers.splice($index,1);     
			});
		};
		
		
	
	}]);
	
	})(); 