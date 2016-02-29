//defining module
var myApp = angular.module('myApp', ['vcRecaptcha']);

//creating custom directive
myApp.directive('ngCompare', function () {
    return {
        require: 'ngModel',
        link: function (scope, currentEl, attrs, ctrl) {
            var comparefield = document.getElementsByName(attrs.ngCompare)[0]; //getting first element
            compareEl = angular.element(comparefield);

            //current field key up
            currentEl.on('keyup', function () {
                if (compareEl.val() != "") {
                    var isMatch = currentEl.val() === compareEl.val();
                    ctrl.$setValidity('compare', isMatch);
                    scope.$digest();
                }
            });

            //Element to compare field key up
            compareEl.on('keyup', function () {
                if (currentEl.val() != "") {
                    var isMatch = currentEl.val() === compareEl.val();
                    ctrl.$setValidity('compare', isMatch);
                    scope.$digest();
                }
            });
        }
    }
});

myApp.directive('myDatepicker', function() {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {
            $(function() {
                element.datepicker({
                    //dateFormat: "dd.mm.yy",
                    format: "yyyy-mm-dd",
                    onSelect:function (dateText, inst) {
                        ngModelCtrl.$setViewValue(dateText);
                        scope.$apply();
                    }
                });
            });
        }
    }
});

// create angular controller
myApp.controller('mainController', function ($scope) {


    // function to submit the form after all validation has occurred
    $scope.submitForm = function () {

        if ($scope.regForm.$valid) {
            // Set the 'submitted' flag to true
            $scope.submitted = true;

        }
        else {
            alert("Please correct errors!");
        }
    };

});

