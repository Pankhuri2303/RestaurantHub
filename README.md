# RestaurantHub
This project contains api to fetch restaurant deals and other related info.

Project :
Maven
Springboot 4.1x
Java 17



There are 2 endpoints implemented
1. To get the deals at given time of day: API: GET localhost:8080/deals QUERY Params: timeOfDay:2:00PM
2. Get the peak time: GET localhost:8080/peakTimeForDeals

   Used json file to fetch details in my db class and return it to the Service.

 DB schema:
 <img width="818" height="672" alt="Screenshot 2026-02-01 at 8 10 17 PM" src="https://github.com/user-attachments/assets/065ebd63-8607-450f-9ea4-b10d3d3a6f31" />

Since this usecase needs some very evident relationship to be maintained between the tables,so I would recommend implementing SQL DB. 

Among, Relational DB, POSTGRESQL can be considered as it is open source, handles concurrency really well as opposed to MYSQL,has several extensions like POSTGIS,TIMESCALE which can be utilized,is scalable and ost efficient.
