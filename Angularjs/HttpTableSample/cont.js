
var app = angular.module('myApp',[]).controller('Ctrl',function($scope,$http)
{$http.get("http://www.w3schools.com/angular/customers.php").success(function(progress){$scope.names=progress.records;});
});
