# Activity 2

## Purpose
The purpose of this lab is to get hands-on experience with communicating between different NodeJS services.

## Activity

Previously you have built upon the idea of creating a service in NodeJS. The task this time is to have one service that can perform GET requests on a second service. You need to create two services using Express and NodeJS for a fantasy sports league (or gaming, whatever you want it to be!). The first service should hold a map with two different users that each have a "roster" of 5 players. You don't need to create any special objects, just be able to store the user's names and their rosters in a data structure. The second service holds a list of players and the number of points they have scored that week.

The first service needs to do the following: 
- Provide a route "/getfinalscore/:username" that will return the total number of points that user's roster has scored for the week.
- Call the second service using axios by passing in each player on the user's roster.
- Sum up all the returned scores and then return that total

The second service needs to do the following:
- Provide a route "/getplayerscore/:playername" that will return the number of points for that week for the player

Feel free to discuss ways to do this within Discord, including possible functionality for axios or NodeJS that might be useful, but do not share code. 

## Submitting work
To submit the activity, simply push your server's code to your repository. Each person has .gitignore in their directory to avoid pushing additional dependencies.
