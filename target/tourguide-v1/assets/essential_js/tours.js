loadTours();
function loadTours() {
	let TOURS_URL = "http://localhost:8085/tourguide/api/tour/all";

	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {

			let tourDataList = JSON.parse(this.responseText);

			let headers = ["Id", "Tour name", "Tour date", "Route", "Driver", "Bus", "Send notification", "Delete"];
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

					if(header == "Send notification"){
						let cell = document.createElement("td");
						let select = document.createElement("select");
						select.setAttribute("id", "typeSelect");
						
						let types = [" ", "EMAIL", "SMS", "PUSH"];
						types.forEach(type =>{
							let option = document.createElement("option");
							option.setAttribute("value", type);
							select.onchange = function(){
								if(select.value != " "){ 
									factoryMethod(select.value);
								}else{
									alert("Nothing is selected.")
								}

							};
							let textNode = document.createTextNode(type);
							option.appendChild(textNode);
							select.appendChild(option);
						});

						cell.appendChild(select);
						row.appendChild(cell);
					}
					if (header == "Delete") {
						let cell = document.createElement("td");
						let deleteBtn = document.createElement("input");
						deleteBtn.setAttribute("id", "deleteBtn");
						deleteBtn.setAttribute("type", "button");
						deleteBtn.setAttribute("class", "btn btn-danger");


						deleteBtn.onclick = function () {
							deleteTour(tour.id);
						};
						deleteBtn.value = "Delete";
						cell.appendChild(deleteBtn);
						row.appendChild(cell);
					}
				});
				table.appendChild(row);
			});

			var divContainer = document.getElementById("tourData");
			divContainer.appendChild(table);
		}
	};
	xhttp.open("GET", TOURS_URL, true);
	xhttp.send();
}

setRoutes();
function setRoutes() {
	const ROUTE_URL = "http://localhost:8085/tourguide/api/route/all";

	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			let routeList = JSON.parse(this.responseText);

			let select = document.getElementById("froute");
			routeList.forEach(route => {
				let option = document.createElement("option");
				option.setAttribute("value", route.id);
				let textNode = document.createTextNode(route.from.destination + " - " + route.to.destination);
				option.appendChild(textNode);
				select.appendChild(option);
			});

		}
	};
	xhttp.open("GET", ROUTE_URL, true);
	xhttp.send();
}

setDrivers();
function setDrivers() {
	const DRIVER_URL = "http://localhost:8085/tourguide/api/driver/all";

	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			let driverList = JSON.parse(this.responseText);

			let select = document.getElementById("fdriver");
			driverList.forEach(driver => {
				let option = document.createElement("option");
				option.setAttribute("value", driver);
				let textNode = document.createTextNode(driver.name);
				option.appendChild(textNode);
				select.appendChild(option);
			});

		}
	};
	xhttp.open("GET", DRIVER_URL, true);
	xhttp.send();
}

setBuses();
function setBuses() {
	const BUS_URL = "http://localhost:8085/tourguide/api/bus/all";

	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			let busList = JSON.parse(this.responseText);

			let select = document.getElementById("fbus");
			busList.forEach(bus => {
				let option = document.createElement("option");
				option.setAttribute("value", bus.id);
				let textNode = document.createTextNode(bus.licensePlate);
				option.appendChild(textNode);
				select.appendChild(option);
			});

		}
	};
	xhttp.open("GET", BUS_URL, true);
	xhttp.send();
}

function submitTour() {
	var verwerken = true;

	let tourNaam = document.forms["tourForm"]["fname"].value;
	if (tourNaam == "") {
		document.tourForm.fname.focus;
		alert("Please provide tour name.");
		verwerken = false;
		return verwerken;
	}

	let tourDatum = document.tourForm.ftourdate.value;
	if (tourDatum == "") {
		document.tourForm.ftourdate.focus;
		alert("Please provide tour date.");
		verwerken = false;
		return verwerken;
	}

	let tourRoute = document.tourForm.froute.value;
	console.log(tourRoute);
	var tourR;
	if (tourRoute == "") {
		document.tourForm.froute.focus;
		alert("Please provide tour route.");
		verwerken = false;
		return verwerken;
	} else {
		const GET_BY_ID = "http://localhost:8085/tourguide/api/route/getById";

		var xhttp = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");

		xhttp.open("GET", GET_BY_ID, true);
		xhttp.setRequestHeader('Content-Type', 'application/json');
		console.log(tourRoute);
		xhttp.send(JSON.stringify({ routeId: tourRoute }));

		tourR = JSON.parse(this.responseText);

		console.log(tourR.id);
		console.log(tourR.from.destination);
		console.log(tourR.to.destination);
		console.log(tourR.distance);
	}

	let tourDriver = document.tourForm.fdriver.value;
	if (tourDriver == "") {
		document.tourForm.fdriver.focus;
		alert("Please provide tour driver.");
		verwerken = false;
		return verwerken;
	}

	let tourBus = document.tourForm.fbus.value;
	if (tourBus == "") {
		document.tourForm.fbus.focus;
		alert("Please provide tour bus.");
		verwerken = false;
		return verwerken;
	}

	verwerken = false;
	if (verwerken) {
		console.log("----");
		let POST_URL = "http://localhost:8085/tourguide/api/tour/insert";
		var tour = {
			tourName: tourNaam,
			tourDate: tourDatum,
			route: {
				id: tourRoute.id,
				from: { id: tourRoute.from.id, destination: tourRoute.from.destination },
				to: { id: tourRoute.to.id, destination: tourRoute.to.destination },
				distance: tourRoute.distance
			},
			driver: { id: tourDriver.id, name: tourDriver.name, dob: tourDriver.dob },
			bus: { id: tourBus.id, licensePlate: tourBus.licensePlate }
		};


		postAjax(POST_URL, tour);

		document.forms.tourForm.reset();

	}
}

function postAjax(url, data) {
	var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
	xhr.open('POST', url);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify({
		tourName: data.tourName,
		tourDate: data.tourDate,
		route: {
			id: data.id,
			from: { id: data.route.from.id, destination: data.routefrom.destination },
			to: { id: data.route.to.id, destination: data.route.to.destination }
		},
		driver: { id: data.driver.id, name: data.driver.name, dob: data.driver.dob },
		bus: { id: data.bus.id, licensePlate: data.bus.licensePlate }
	}));
	return xhr;
}

function deleteTour(tourId) {
	let url = `http://localhost:8085/tourguide/api/tour/delete/${tourId}`;

	var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
	xhr.open('DELETE', url);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.setRequestHeader("Accept", "*/*");
	xhr.send();
	return xhr;
}

function factoryMethod(type){
	let url = `http://localhost:8085/tourguide/api/tour/factorymethod/${type}`;
	var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");

	xhr.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {		
			let text = this.responseText;
			alert(text);
		}
	};

	xhr.open('GET', url);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send();

	return xhr;
}