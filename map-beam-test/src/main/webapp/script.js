"use strict";

angular
  .module("map-beam", [])
  .controller("MapController", function(
    $scope,
    $rootScope,
    $compile,
    locationService
  ) {

      function showMap(lat, long, distance,limit) {

          locationService.getLocations(lat,long,distance,limit).then(function (result) {
              $scope.cities = result.data;
              $scope.infowindow = new google.maps.InfoWindow({
                  content: "",
              });
              $scope.markers = [];
              for (var i = 0; i < $scope.cities.length; i++) {
                  var marker = new google.maps.Marker({
                      position: new google.maps.LatLng(
                          $scope.cities[i].lat,
                          $scope.cities[i].lng
                      ),
                      map: $scope.map,
                      title: $scope.cities[i].title,
                  });

                  var content =
                      '<a ng-click="cityDetail(' +
                      i +
                      ')" class="btn btn-default">View details</a>';
                  var compiledContent = $compile(content)($scope);

                  google.maps.event.addListener(
                      marker,
                      "click",
                      (function (marker, content, scope) {
                          return function () {
                              scope.infowindow.setContent(content);
                              scope.infowindow.open(scope.map, marker);
                          };
                      })(marker, compiledContent[0], $scope)
                  );
                  $scope.markers.push(marker);

              }

          });
      }

      function initialize() {
      $scope.map = new google.maps.Map(document.getElementById("map"), {
        zoom: 12,
        center: { lat: 1.357107, lng: 103.8194992 },
      });
          var x = document.getElementById("x").value
          var y = document.getElementById("y").value
          var lat = document.getElementById("lat").value
          var lng = document.getElementById("lng").value
      showMap(lat, lng, y,x);
        google.maps.event.addListener( $scope.map, 'click', function(event) {
            for (var i = 0; i < $scope.markers.length; i++) {
                $scope.markers[i].setMap(null);
            }
            document.getElementById("lat").setAttribute("value",event.latLng.lat())
            document.getElementById("lng").setAttribute("value",event.latLng.lng())
            var x = document.getElementById("x").value
            var y = document.getElementById("y").value
            showMap(event.latLng.lat(),event.latLng.lng(),y,x);

        });
    }

    $scope.cityDetail = function(index) {
      alert(JSON.stringify($scope.cities[index]));
    };

    google.maps.event.addDomListener(window, "load", initialize);
  });

angular.module("map-beam").service("locationService", function($http) {
  var service = {};

  service.getLocations = function(lat, long , distance,limit) {
    var url = "/api/locations/"+lat+"/"+long+"/"+distance+"?limit="+limit;
    return $http.get(url);
  };
  return service;
});