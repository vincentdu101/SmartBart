var StompClient = (function() {
    var stompClient = null;
    var stationsCallback;
    var trainsAndSeatsCallback;
    var seatsCallback;

    function setConnected(connected) {
        $("#connect").prop("disabled", connected);
        $("#disconnect").prop("disabled", !connected);
        if (connected) {
            $("#conversation").show();
        }
        else {
            $("#conversation").hide();
        }
        $("#greetings").html("");
    }

    function connect() {
        var socket = new SockJS('/gs-guide-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            setConnected(true);
            console.log('Connected: ' + frame);

            stompClient.subscribe('/topic/greetings', function (greeting) {
                showGreeting(JSON.parse(greeting.body).content);
            });

            stompClient.subscribe('/topic/allStations', function (stations) {
                stationsCallback(JSON.parse(stations.body));
            });

            stompClient.subscribe('/topic/updateStations', function (stations) {
                stationsCallback(JSON.parse(stations.body));
            });

            stompClient.subscribe('/topic/allTrains', function (trainsAndSeats) {
                trainsAndSeatsCallback(JSON.parse(trainsAndSeats.body));
            });

            stompClient.subscribe('/topic/updateTrains', function (trainsAndSeats) {
                trainsAndSeatsCallback(JSON.parse(trainsAndSeats.body));
            });

            stompClient.subscribe('/topic/allSeats', function (seats) {
                seatsCallback(JSON.parse(seats.body));
            });

            stompClient.subscribe('/topic/updateSeats', function (seats) {
                seatsCallback(JSON.parse(seats.body));
            });

            stompClient.send("/app/stations/all");
            stompClient.send("/app/trains/all");
        });
    }

    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("Disconnected");
    }

    function sendName() {
        stompClient.send("/app/hello");
        stompClient.send("/app/stations/all");
//        stompClient.send("/app/stations/update", {}, JSON.stringify({'name': $("#name").val()}));
    }

    function showGreeting(message) {
        $("#greetings").append("<tr><td>" + message + "</td></tr>");
    }

    return {

        updateSeats: function(tableId) {
            stompClient.send("/app/seats/all", {}, JSON.stringify({'tableId': tableId}));
        },

        init: function(stationsDone, trainsAndSeatsDone, seatsDone) {
            stationsCallback = stationsDone;
            trainsAndSeatsCallback = trainsAndSeatsDone;
            seatsCallback = seatsDone;

            $("form").on('submit', function (e) {
                e.preventDefault();
            });
            $( "#connect" ).click(function() { connect(); });
            $( "#disconnect" ).click(function() { disconnect(); });
            $( "#send" ).click(function() { sendName(); });
        }
    };

})();

