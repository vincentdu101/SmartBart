var Stations = (function(){

    ALL_STATIONS = {};

    function emptyTable() {
        $(".stations-table").empty();
    }

    function updateStations(data) {
        for (var i = 0; i < data.length; i++) {
            ALL_STATIONS[data[i].id] = data[i].description;
        }
    }

    function updateTable(data) {
        var $table = $("<table class='table table-bordered'>");
        $table.append("<thead><tr><th>Current</th><th>Next North</th><th>Next South</th></tr></thead><tbody>");
        for (var i = 0; i < data.length; i++) {
            var current = data[i].description;
            var north = data[i].nextNorthStationId === -1 ? "NONE" : ALL_STATIONS[data[i].nextNorthStationId];
            var south = data[i].nextSouthStationId === -1 ? "NONE" : ALL_STATIONS[data[i].nextSouthStationId];
            $table.append("<tr><td>" + current + "</th><td>" + north + "</td><td>" + south + "</td></tr>");
        }
        $(".stations-table").append($table.append("</tbody></table>"));
    }

    return {

        update: function(data) {
            emptyTable();
            updateStations(data);
            updateTable(data);
        }

    };

}());