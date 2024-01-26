loadRoutes();
function loadRoutes() {
	let URL = "http://localhost:8085/tourguide/api/route/all";

	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {

			let list = JSON.parse(this.responseText);

			let headers = ["Id", "From", "To", "Distance", "Save", "Chain"];
			let table = document.createElement("table");
			table.setAttribute("id", "dataTable");
			table.setAttribute("class", "table");
			table.setAttribute("data-toggle", "table");
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
					if (header == "Distance") {
						let cell = document.createElement("td");
						let distanceFld = document.createElement("input");
						distanceFld.setAttribute("id", "distanceFld" + route.id);
						distanceFld.setAttribute("type", "text");

						let textNode = document.createTextNode(route.distance);
						cell.appendChild(distanceFld);
						distanceFld.value = route.distance;
						row.appendChild(cell);
					}
					if (header == "Save") {
						let cell = document.createElement("td");
						let saveBtn = document.createElement("input");
						saveBtn.setAttribute("id", "saveBtn");
						saveBtn.setAttribute("type", "button");
						saveBtn.setAttribute("class", "btn btn-primary");


						saveBtn.onclick = function () {

							let thisDistance = document.getElementById("distanceFld" + route.id).value;
							console.log("thisDistance: " + thisDistance);
							if (!isNaN(thisDistance)) {

								let thisRoute = { id: route.id, distance: thisDistance, from: { id: route.from.id, destination: route.from.destination }, to: { id: route.to.id, destination: route.to.destination } };

								console.log(thisRoute.id, thisRoute.distance, thisRoute.from.destination, thisRoute.to.destination);


								updateRoute(thisRoute);
								alert("Updated.");
							} else {
								alert("Nummerieke waarde meesturen.");
							}
						};
						saveBtn.value = "Save";
						cell.appendChild(saveBtn);
						row.appendChild(cell);
					}

					if (header == "Chain") {
						let cell = document.createElement("td");
						let chainBtn = document.createElement("input");
						chainBtn.setAttribute("id", "chainBtn");
						chainBtn.setAttribute("type", "button");
						chainBtn.setAttribute("class", "btn btn-primary");


						chainBtn.onclick = function () {
							console.log("this route id: " + route.id);
							chain(route.id);

						};
						chainBtn.value = "Chain";
						cell.appendChild(chainBtn);
						row.appendChild(cell);
					}
				});

				table.appendChild(row);
			});


			var divContainer = document.getElementById("routeData");
			divContainer.appendChild(table);


		}
	};
	xhttp.open("GET", URL, true);
	xhttp.send();
}

function updateRoute(data) {
	let url = "http://localhost:8085/tourguide/api/route/update";
	var xhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
	xhr.open('POST', url);
	xhr.setRequestHeader('Content-Type', 'application/json');


	console.log(data.id, data.distance, data.from.destination, data.to.destination);


	xhr.send(JSON.stringify({ id: data.id, distance: data.distance, from: { id: data.from.id, destination: data.from.destination }, to: { id: data.to.id, destination: data.to.destination } }));
	return xhr;

}

function chain(id) {
	let url = `http://localhost:8085/tourguide/api/route/chain/${id}`;
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

	console.log("here");

	return xhr;
}