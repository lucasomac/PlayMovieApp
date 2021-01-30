package br.com.digitalhouse.playmovieapp.services

import com.google.firebase.firestore.FirebaseFirestore

var db = FirebaseFirestore.getInstance()
var collectionQuestions = db.collection("questions")
var collectionUsers = db.collection("users")