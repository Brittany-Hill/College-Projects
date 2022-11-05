# Project 3

# Due: Wednesday, December 1st 11:59 PM CST

## Objective 

The purpose of this project is to define automated workflows that can be utilized by Github Actions.   

## Problem Description

You will be making multiple workflows to accomplish tasks with Github Actions. When needing to send a notification email, use the email address forcensem2021@gmail.com. 

## Project Details

Before beginning the rest of this project, complete the following steps:

- Make the main branch protected so that noone can directly push to it without going through a pull request and make sure that all status checks have to pass (add both as rules)
- Write a simple NodeJS application (no webserver required) that contains a function for adding numbers together and returning the result
- Read about Jest or Mocha tests and write a test for your NodeJS application

Once these steps are done, you will write workflows for the following:

- Create a workflow that runs your tests on your NodeJS application using Github actions and both 12.x and 14.x versions of Node and produces a Github package if it succeeds. This should be done with two different jobs.
- Create a workflow that runs will send an email when a pull request is created
- Create a workflow that will add a label to your pull request when it is appropriately approved 
- Create two custom actions. The first is a Docker action that will write a file of random numbers. The second is a Javascript action that will read the file written by the first action, sum the values, and then print the resulting sum. 

Some of these will require the use of community actions, while others require you to research uploading and downloading artifacts from actions. Once you have completed these workflows, you should have a basic understanding of how to create Github actions.

## Submission Instructions
  
To submit the project, simply push your group's code to your repository. Each repository has a .gitignore in their directory to avoid pushing additional dependencies.

