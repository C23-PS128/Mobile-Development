package com.bangkit.capstone.beangreader.data.repository.firebase

import android.content.Intent
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.SignInResult
import com.bangkit.capstone.beangreader.presentation.screen.authentication.model.UserData
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FireBaseAuthImpl @Inject constructor(
    private val onTapClient: SignInClient
) : FireBaseAuth {

    private val auth = Firebase.auth

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
                    profilePicture = photoUrl?.toString(),
                )
            },
            errorMessage = null
        )
    }

    override suspend fun signInWithEmail(emails: String, password: String): SignInResult {
        val signInTask = auth.signInWithEmailAndPassword(emails, password).await()
        val user = signInTask.user
        return SignInResult(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    email = email,
                    profilePicture = photoUrl?.toString(),
                )
            },
            errorMessage = null
        )
    }

    override suspend fun signUpWithEmail(
        name: String,
        emails: String,
        password: String
    ): SignInResult {
        val registerTask = auth.createUserWithEmailAndPassword(emails, password).await()
        registerTask.user?.updateProfile(
            UserProfileChangeRequest.Builder().setDisplayName(name).build()
        )
        val user = registerTask.user
        return SignInResult(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    email = email,
                    profilePicture = photoUrl?.toString(),
                )
            },
            errorMessage = null
        )
    }

    override suspend fun signOut() {
        onTapClient.signOut()
        auth.signOut()
    }

    override suspend fun getSignedUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            email = email,
            profilePicture = photoUrl?.toString()
        )
    }
}