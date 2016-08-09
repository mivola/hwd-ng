package com.voigt.hwd.comm

import groovyx.net.ws.WSClient;
def proxy = new WSClient("http://terraservice.net/TerraService.asmx?WSDL", this.class.classLoader)

proxy.create()

// Create the Place object
def place = proxy.create("com.terraserver_usa.terraserver.Place")

// Initialize the Place object
place.city = "mountain view"
place.state = "ca"
place.country = "us"

// Geocode the place
def result = proxy.ConvertPlaceToLonLatPt(place)

println "Longitude: ${result.lon}"
println "Latitude: ${result.lat}"

