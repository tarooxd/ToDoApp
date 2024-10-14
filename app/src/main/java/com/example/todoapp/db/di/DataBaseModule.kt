//package com.example.todoapp.db.di
//
//import com.example.todoapp.ToDo
//import com.example.todoapp.db.data.MongoRepository
//import dagger.Provides
//import io.realm.kotlin.Realm
//import io.realm.kotlin.RealmConfiguration
//import javax.inject.Singleton
//
//object DataBaseModule {
//
//    @Singleton
//    @Provides
//    fun provideRealm(): Realm {
//        val config = RealmConfiguration.Builder(
//            schema = setOf(
//                ToDo::class
//            )
//        )
//            .compactOnLaunch()
////            .initialData()
//            .build()
//        return Realm.open(config)
//    }
//
//    @Singleton
//    @Provides
//    fun provideMongoRepository(realm: Realm) : MongoRepository {
//        return MongoRepositoryImpl(realm = realm)
//    }
//}