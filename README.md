# Serverless Chat-room 

Link to tutorial: https://www.youtube.com/watch?v=3kLcbWw7ptk

A chat room that is built with nothing but Firebase Database, Firebase authentication, Firebase Cloud Functions and Firebase Cloud Messages.

The basic functionality of this app is to have multiple users sign onto it, and message on a common portal. As of now, only the last 10 messages are loaded, but it can be increased according to the user's requirements.

It consists of 3 different folders:
* CompleteApp: This contains the full working source code for the Android app
* StubbedApp: This contains source code with missing blocks of code and TODO statements so as to fill those missing blocks up.
* ServerlessFunction: Contains Firebase Cloud Functions that are to be executed

To implement the cloud functions, just perform the following tasks:

* First install ```npm``` and ```node``` if you don't have them yet
* Execute the command: ```npm install -g firebase-tools```
* Change the current working directly to ServerlessFunction and then login to your account by typing ```firebase login```
* Create your own directory
* Inside your directory, call ```firebase init```: Select functions from the dialog, select project
* Copy index.js from ServerlessCloudFunctions/functions to functions directory inside your newly created directory
* Then simply deploy it to the server using ```firebase deploy```
* If it doesn't work, to check error logs, run the same command with ```firebase deploy --debug```
