loadBusDistances();
function loadBusDistances() {
    const LIST_URL = "http://localhost:8085/tourguide/api/busdistance/list";

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let list = JSON.parse(this.responseText);

            let headers = ["License plate", "Distance covered"];
            let table = document.createElement("table");
            table.setAttribute("id", "dataTable");
            let headerRow = document.createElement("tr");

            headers.forEach(header => {
                let thisHeader = document.createElement("th");
                let textNode = document.createTextNode(header);
                thisHeader.appendChild(textNode);
                headerRow.appendChild(thisHeader);
            });

            table.appendChild(headerRow);




            list.forEach(distance => {
                let row = document.createElement("tr");


                headers.forEach(header => {

                    if (header == "License plate") {
                        let cell = document.createElement("td");
                        let textNode = document.createTextNode(distance.bus.licensePlate);
                        cell.appendChild(textNode);
                        row.appendChild(cell);
                    }

                    if (header == "Distance covered") {
                        let cell = document.createElement("td");
                        let textNode = document.createTextNode(distance.totalDistance);
                        cell.appendChild(textNode);
                        row.appendChild(cell);
                    }
                });


                // Object.values(distance).forEach(text => {
                //     let cell = document.createElement("td");

                //     let textNode = document.createTextNode(text);
                //     cell.appendChild(textNode);
                //     row.appendChild(cell);
                // });
                table.appendChild(row);
            });

            var divContainer = document.getElementById("busDistanceData");
            divContainer.appendChild(table);

        }
    };
    xhttp.open("GET", LIST_URL, true);
    xhttp.send();
}

loadRoutes();
function loadRoutes() {
    const LIST_URL = "http://localhost:8085/tourguide/api/route/all";

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let list = JSON.parse(this.responseText);

            let headers = ["Id", "From", "To"];
            let table = document.createElement("table");
            table.setAttribute("id", "dataTable");
            let headerRow = document.createElement("tr");

            headers.forEach(header => {
                let thisHeader = document.createElement("th");
                let textNode = document.createTextNode(header);
                thisHeader.appendChild(textNode);
                headerRow.appendChild(thisHeader);
            });

            table.appendChild(headerRow);

            list.forEach(route => {
                let row = document.createElement("tr");


                headers.forEach(header => {

                    if (header == "Id") {
                        let cell = document.createElement("td");
                        let textNode = document.createTextNode(route.id);
                        cell.appendChild(textNode);
                        row.appendChild(cell);
                    }

                    if (header == "From") {
                        let cell = document.createElement("td");
                        let textNode = document.createTextNode(route.from.destination);
                        cell.appendChild(textNode);
                        row.appendChild(cell);
                    }
                    if (header == "To") {
                        let cell = document.createElement("td");
                        let textNode = document.createTextNode(route.to.destination);
                        cell.appendChild(textNode);
                        row.appendChild(cell);
                    }
                });



                table.appendChild(row);
            });

            var divContainer = document.getElementById("routesData");
            divContainer.appendChild(table);

        }
    };
    xhttp.open("GET", LIST_URL, true);
    xhttp.send();

}

function loadTours() {

    let div = document.getElementById("dateData");
    while (div.firstChild) {
        div.removeChild(div.lastChild);
    }

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {

            let tourDataList = JSON.parse(this.responseText);
            let headers = ["Id", "Tour name", "Tour date", "Route", "Driver", "Bus"];
            let table = document.createElement("table");
            table.setAttribute("id", "dataTable");
            let headerRow = document.createElement("tr");

            headers.forEach(header => {
                let thisHeader = document.createElement("th");
                let textNode = document.createTextNode(header);
                thisHeader.appendChild(textNode);
                headerRow.appendChild(thisHeader);
            });

            table.appendChild(headerRow);


            tourDataList.forEach(tour => {

                let row = document.createElement("tr");

                headers.forEach(header => {
                    if (header == "Id") {
                        let cell = document.createElement("td");
                        let textNode = document.createTextNode(tour.id);
                        cell.appendChild(textNode);
                        row.appendChild(cell);
                    }
                    if (header == "Tour name") {
                        let cell = document.createElement("td");
                        let textNode = document.createTextNode(tour.tourName);
                        cell.appendChild(textNode);
                        row.appendChild(cell);
                    }
                    if (header == "Tour date") {
                        let cell = document.createElement("td");
                        let textNode = document.createTextNode(tour.tourDate);
                        cell.appendChild(textNode);
                        row.appendChild(cell);
                    }
                    if (header == "Route") {
                        let cell = document.createElement("td");
                        let textNode = document.createTextNode(tour.route.from.destination + " - " + tour.route.to.destination);
                        cell.appendChild(textNode);
                        row.appendChild(cell);
                    }
                    if (header == "Driver") {
                        let cell = document.createElement("td");
                        let textNode = document.createTextNode(tour.driver.name);
                        cell.appendChild(textNode);
                        row.appendChild(cell);
                    }
                    if (header == "Bus") {
                        let cell = document.createElement("td");
                        let textNode = document.createTextNode(tour.bus.licensePlate);
                        cell.appendChild(textNode);
                        row.appendChild(cell);
                    }

                });
                table.appendChild(row);
            });

            var divContainer = document.getElementById("dateData");
            divContainer.appendChild(table);
        }
    };

    let thisYear = document.getElementById("fyear").value;

    let TOURS_URL = `http://localhost:8085/tourguide/api/tour/year/${thisYear}`;
    console.log(TOURS_URL);
    xhttp.open("GET", TOURS_URL);
    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send();
}

loadYears();
function loadYears() {

    let years = [2021, 2022, 2023];
    let fyear = document.getElementById("fyear");
    fyear.setAttribute("onchange", "loadTours()");

    years.forEach(element => {
        let option = document.createElement("option");
        option.setAttribute("value", element);
        let textNode = document.createTextNode(element);
        option.appendChild(textNode);
        fyear.appendChild(option);
    });

    loadTours();
}