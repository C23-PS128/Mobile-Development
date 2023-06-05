package com.bangkit.capstone.beangreader.di

import android.content.Context
import com.bangkit.capstone.beangreader.data.repository.firebase.FireBaseAuth
import com.bangkit.capstone.beangreader.data.repository.firebase.FireBaseAuthImpl
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseModule {

    @Binds
    @Singleton
    abstract fun provideFirebaseAuth(fireBaseAuthImpl: FireBaseAuthImpl): FireBaseAuth

    companion object {

        @Provides
        @Singleton
        fun provideSignInClient(@ApplicationContext context: Context): SignInClient {
            return Identity.getSignInClient(context)
        }
    }
}