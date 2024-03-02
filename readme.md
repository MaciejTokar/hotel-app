# Project Description

This project is an example implementation of a hotel management system, 
utilizing Hibernate technology for object-relational mapping.
The system allows for room reservations,
management of customer and staff data, and tracking room availability in the hotel.

## Prerequisites

1. Java JDK 18
2. Maven
3. Database(MySQL)
4. Hibernate configuration

## Running instructions

1. Configure the 'hibernate.cfg.xml' file according to your database settings, base on 'hibernate.cfg.xml.example' 
2. Run 'data.sql' to add data to your database.
3. Run the application through the main class(Main.java).

## Project Structure

src/main/resources: Contains configuration file 'hibernate.cfg.xml.example'
src/main/resources: Create your own file 'hibernate.cfg.xml' base on 'hibernate.cfg.xml.example'

## Examples Functionality

#### CRUD for entities of the project: 
Hotel, Room, Client, Employee, Event, Facility, Reservation, Review

#### Additional functionality for entities:

1. Hotel: search by name and address
2. Room: search by type and person count, filters
3. Client: search, sorting and calculating price for reservation
4. Employee: search and average of salary
5. Event: sorting and summation of events
6. Reservation: search, sorting, client payments and earnings by reservations
for hotels
7. Review: search, sorting, average outcome for hotel and outcome by client