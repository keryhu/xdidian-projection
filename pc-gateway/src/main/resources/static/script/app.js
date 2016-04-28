'use strict';
var app = angular.module('myApp', []);

app.config(['$httpProvider',function($httpProvider) {
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	}
]);

app.controller('myCtrl',['$scope','$http',function($scope, $http){
	$scope.credentials = {};
	$scope.login=function(){
		
		var parameter = JSON.stringify($scope.credentials);
	         //因为是在pc－gateway 端口下提交后台，，经过spirng zuul 转换 /property-register/ 到 property-register service 后来，然后对接
		//property/register方法。
	      $http.post('/property-register/property/register',parameter)
	           .success(function(data, status, headers,config){
	    	 // console.log(data);
	      });
	}
	
	
	$scope.user={};
	$scope.de=function(){
		$http.delete('/property-register/property/'+$scope.user.id)
		.success(function(data, status, headers,config){
			console.log('data'+data)
		});
	}
	
	$scope.logout=function(){
		$http.post('logout',{}).finally(function(){
			
			console.log('已经成功退出。。');
			//前台导到指定页面，后台不负责页面跳转
		});
		
	};
	
	
	
}])