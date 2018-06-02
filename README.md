# Serverless Chat-room

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
* After you authenticate your account, make sure that all packages are installed. Change directory to functions, and type in ```npm install```
* Then simply deploy it to the server using ```firebase deploy```
