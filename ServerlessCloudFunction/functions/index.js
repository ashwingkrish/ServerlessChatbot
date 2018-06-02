const functions = require('firebase-functions');

const admin = require('firebase-admin');
admin.initializeApp();


var message = {
  data: {
    title: 'Chatbot',
    body: 'You have unread messages.'
  },
  topic: 'any'
}
exports.sendNotification = functions.database.ref('messages').onWrite((change, context) => {
  admin.messaging().send(message)
  .then((response) => {
    console.log("Successfully sent message: ", response)
  })
  .catch((error) => {
    console.log("Error sending message: ", error)
  })
})
// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });
