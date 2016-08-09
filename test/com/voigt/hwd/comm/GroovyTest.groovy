package com.voigt.hwd.comm


import groovyx.net.ws.WSClient

println "START"
def openLigaDB = new WSClient("http://www.openligadb.de/Webservices/Sportsdata.asmx?WSDL", this.class.classLoader)
openLigaDB.create()
//def openLigaDB = new WSClient("http://vega-020-mafo:8080/mafo/MafoWebservice?wsdl", this.class.classLoader)
def leagues = openLigaDB.GetAvailLeagues()
println leagues

def match = openLigaDB.GetMatchByMatchID(1103)
println "Spiel ${match.matchID} wurde zwischen ${match.nameTeam1} und ${match.nameTeam2} gespielt."

def play = openLigaDB.GetMatchdataByGroupLeagueSaison(24,"bl1",2008)
println play
for (game in play.matchdata){
    println "id: ${game.matchID}; date=${game.matchDateTime.day}.${game.matchDateTime.month}.${game.matchDateTime.year} time=${game.matchDateTime.hour}:${game.matchDateTime.minute}; ${game.nameTeam1} - ${game.nameTeam2}: ${game.pointsTeam1} - ${game.pointsTeam2}"
}