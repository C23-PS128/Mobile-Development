package com.bangkit.capstone.beangreader.data.repository.firebase

import android.content.Intent
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.SignInResult
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FireBaseAuthImpl @Inject constructor(
    private val onTapClient: SignInClient
): FireBaseAuth {

    private val auth = Firebase.auth
    private val fireStore = Firebase.firestore

    override suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = onTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        val signInTask = auth.signInWithCredential(googleCredentials).await()
        val user = signInTask.user
        return SignInResult(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    email = email,
                    phone = phoneNumber,
                    profilePicture = photoUrl?.toString(),
                    isNewUser = !isUserAlreadyExists(uid)
                )
            },
            errorMessage = null
        )
    }

    override suspend fun signInWithEmail(email: String, password: String): SignInResult {
        val signInTask = auth.signInWithEmailAndPassword(email, password).await()
        val user = signInTask.user
        return SignInResult(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    email = email,
                    phone = phoneNumber,
                    profilePicture = photoUrl?.toString(),
                    isNewUser = !isUserAlreadyExists(uid)
                )
            },
            errorMessage = null
        )
    }

    override suspend fun signUpWithEmail(
        name: String,
        email: String,
        password: String
    ): SignInResult {
        val registerTask = auth.createUserWithEmailAndPassword(email, password).await()
        val user = registerTask.user
        return SignInResult(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName ?: name,
                    email = email,
                    phone = phoneNumber,
                    profilePicture = photoUrl?.toString(),
                    isNewUser = !isUserAlreadyExists(uid)
                )
            },
            errorMessage = null
        )
    }

    override suspend fun signOut() {
        onTapClient.signOut()
        auth.signOut()
    }

    override fun getSignedUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            email = email,
            phone = phoneNumber,
            profilePicture = photoUrl?.toString()
        )
    }

    private suspend fun isUserAlreadyExists(userId: String): Boolean {
        return try {
            val snapshot = fireStore.collection("user")
                .document(userId)
                .get()
                .await()
            snapshot.exists()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}