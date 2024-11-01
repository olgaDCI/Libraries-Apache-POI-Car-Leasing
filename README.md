# Leasing car cost calculator

## Description

In this exercise, we'll learn how to work with Apache POI to read the contents of an Excel file.

## Tasks

Before you start your work, take a look at the **LeasingCar.java** class and make yourself familiar with it's methods and fields. The class is a simple storage class that will provide us with a way to save the main cost factors of different car models (i.e. individual objects).

You'll find a populated Excel file LeasingCars.xls in the src/main/resources directory.  

> Check the [official guides](https://poi.apache.org/components/spreadsheet/quick-guide.html) for pointers to the correct API classes and methods.

### Read the contents of the Excel file
Import the libraries (in lib/) and read from the *LeasingCars.xlsx* file in your code. Calculate the total monthly for each car and output it for the user.

* Create a new Object of the LeasingCars class for each row in the Excel file
* Calculate the monthly total cost depending on the user input (see prepared code parts).

The output should look like this:
````
Please enter the amount of kilometers you drive monthly (no decimals):
1500
Please enter the percent-discount for your insurance plan:
17
Monthly cost for the BMW 8er is 494.83
Monthly cost for the VW Sahara is 525.17
Monthly cost for the Seat Castille is 456.75
Monthly cost for the Skoda Atlantis is 480.58

The Seat Castille is the best offer with a total cost of 456.75
````
