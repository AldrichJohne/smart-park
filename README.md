Installation

clone repository: https://github.com/AldrichJohne/smart-park.git

------------------------------
Accessing the H2 database

url-> jdbc:h2:mem:smartpark

username -> sa

password -> "leave it blank"

----------------------------

Build and Run ->
mvn spring-boot:run

Run Unit Test ->
mvn test
_______
API Endpoints
-
View Parking Lot

GET

http://localhost:8080/api/v1/parking-lot/{lotId}

---

Register a Parking Lot

POST

http://localhost:8080/api/v1/parking-lot

Body:

{
    "lotId": "LOT03",
    "location": "Megamall",
    "capacity": 2,
    "occupiedSpace": 0
}

---

View all vehicles in a parking lot

GET

http://localhost:8080/api/v1/parking-lot/vehicles/{lotId}

---

Register a vehicle

POST

http://localhost:8080/api/v1/vehicle

Body:

{
    "licensePlate": "ZZZ-2222",
    "type": "TRUCK",
    "ownerName": "Mar Moo",
    "parkingLot": null
}

---

Checkin a vehicle

POST

http://localhost:8080/api/v1/vehicle/{licens-plate}/check-in/{lotId}

---

Checkout a vehicle

POST

http://localhost:8080/api/v1/vehicle/{license-plate}/check-out

---

Postman Collection located at smartpark/postman/
