# Programming Project 5
See Canvas for due date

## Project Outcomes:
Develop a Java program that uses:
* File Processing
* Interfaces 

## Prep Readings:
* Zybooks, chapters 1 - 12 

## Project Overview:
Government and organizations have become increasingly concerned with carbon footprint (annual releases of carbon dioxide in the atmosphere).
Create a Java program that uses Interfaces to calculate the carbon footprint of various objects.
Much of the design is left up to you as the programmer, however good object oriented design is required.
As we learned, Interfaces allow us to specify similar behavior for seemingly unrelated classes.
We will use Interfaces in this project to link a building, an automobile, and food.

## Project Requirements
* Create three class Building, Auto and Food.
* Provide each class with instance variables that relate to them but not to the other classes.
* Provide each class with accessor and mutator methods as well as at least one additional method that is unique to that class.
* Provide additional instance variables related to calculating the Carbon Footprint (CO2 emission) (See http://www.carbonglobe.com/calculator-equations.php for formula for each type).
	* The Building only uses electricity and pays $0.25 per kilowatt hour. 
	* Use the Vehicle calculation for Auto class
	* Food class 
		* Include an instance field that describe the category of food.
		* Use the food category field to determine the emission factor.
* Create an Interface called CarbonFootprint with one method getCarbonFootprint that returns a double representing the carbon footprint in pounds.
* The three classes, Building, Auto and Food, must implement the CarbonFootprint Interface.

* Create a main application class
	* Reads data from a file that contains the data required to create at least 3 instances of each of our three classes (so at least 9 object total)
	* The file format is left up to the developer but must be included in the submission. 
		* Since the type and amount of data differ for each category, it may be wise to use the first field on a given line to identify the category. 
	* Create objects based on the data from the file and place those objects in an array of the CarbonFootprint Interface type. 
	* Iterate through the array calling the getCarbonFootprint method polymorphically and output the category and type of item such as:

```
Spending $200 per month on MEAT creates 7666 pounds of CO2 per year
Spending $185 per month on VEGETABLE creates 5743 pounds of CO2 per year
Spending $110 per month on FRUIT creates 3415 pounds of CO2 per year
Spending $85 per month on JUNK creates 4288 pounds of CO2 per year
Leasing the 450 sqft. building owned by Bubba will create 909954 pounds of CO2 per year based on a factor of 123 KW per sqft. The monthly electric bill would be approximately $13837
Leasing the 4507 sqft. building owned by Chryster will create 9113694 pounds of CO2 per year based on a factor of 123 KW per sqft. The monthly electric bill would be approximately $138590
Leasing the 1835 sqft. building owned by Bitner will create 3710590 pounds of CO2 per year based on a factor of 123 KW per sqft. The monthly electric bill would be approximately $56426
Driving a 2011 Ford Escape 100 miles per week creates 4557 pounds of CO2 per year
Driving a 2014 Honda Odyssey 231 miles per week creates 9187 pounds of CO2 per year
Driving a 1999 Chevrolet Camaro 277 miles per week creates 10393 pounds of CO2 per year
```

The remaining design details are left up to the developer, including how to structure the application, how many classes to develop and the interrelationship of those classes.
Use the techniques you've learned in this course, including good object oriented design.

Create two UML Class Diagram for this project. 
The class diagrams should be created as follows:
1. The first iteration should be done before you code and should provide a design that the code follows. (Design Version)
2. The second iteration should be completed after the code is complete and should reflect the **exact** class structure of you final program. (Final Version)
3. The class diagrams should include 
	1. Access specifier (- or +).
	2. All instance fields with type.
	3. All methods with return type and parameter types.
	4. Associations, Generalization (Inheritance) and Aggregation and Multiplicity.
	5. Stereotyping - Interface or Abstract Classes.

Design Version - completed prior to coding the project to assist with coding.
Final Version - completed after the coding that accurately represents the final version of the code, generally used for maintenance purposes.
No need to include the class with main method in the UML diagrams.
Refer to the UML Distilled pdf on the content page as a reference for creating class diagrams

## Submission Requirements:
* Submit the design and final versions of your UML.
* Submit all source code
* Submit the input file needed to do a test run of your application.
* Include all unit test files you used to help develop your application.

### Make sure you turn in a copy of the text file that populates the objects

## Rubric
Grading Area | Weight
------------ | ------
Project actually works | 40
Code format and structure (Designed using OOP) | 25
Interface | 10
Implementation of Interface in Classes | 10
UML | 15
