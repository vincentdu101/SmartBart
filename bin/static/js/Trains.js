var Trains = (function(){

    var ALL_TRAINS = {};

    function emptyTable() {
        $(".trains-table").empty();
    }

    function updateTrains(trains) {
        for (var i = 0; i < trains.length; i++) {
            ALL_TRAINS[trains[i].id] = trains[i].description;
        }
    }

    function parseDate(date) {
        var newDate = new Date(date.year, date.monthValue - 1, date.dayOfMonth, date.hour, date.minute, date.second);
        return newDate.toLocaleString();
    }

    function updateTable(data) {
        var $trains = $("<ul class='list-group'></ul>");
        for (var i = 0; i < data.length; i++) {
            var train = data[i];
            $trains.append("<li class='list-group-item table-row row' data-table-id='" + train.id + "'>" +
            "<div class='col-md-10 col-sm-12'>" +
            "<h4>" + train.description + "</h4>" +
            "<h6>Current Station: " + train.currentStation.description + "</h6>" +
            "<h6>Last Update At: " + parseDate(train.trainStationProgress.modifiedAt) + "</h6></div>" +
            "<div class='col-md-2 col-sm-12'><button class='btn btn-primary seat-btn' onclick='Trains.addSeatBtnEvent(event)'>View Seats</button></div>" +
            "<div class='col-sm-12 seat-table'></div>" +
            "</li>");
        }

        $(".trains-table").empty().append($trains);
    }

    return {

        addSeatBtnEvent(event) {
            var tableId = $(event.currentTarget).parents(".list-group-item").data("table-id");
            StompClient.updateSeats(tableId);
        },

        update: function(data) {
            emptyTable();
            updateTrains(data);
            updateTable(data);
        }

    };

}());