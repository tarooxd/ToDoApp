//package com.example.todoapp.db.data
//
//import android.util.Log
//import com.example.todoapp.ToDo
//import io.realm.kotlin.Realm
//import io.realm.kotlin.ext.query
//import io.realm.kotlin.log.LogLevel
//import io.realm.kotlin.mongodb.App
//import io.realm.kotlin.mongodb.sync.SyncConfiguration
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//import org.mongodb.kbson.ObjectId
//
//object MongoDB : MongoRepository {
//    private val app = App.create(APP_ID)
//    private val user = app.currentUser
//    private lateinit var realm: Realm
//
//    init {
//        configureTheRealm()
//    }
//
//    override fun configureTheRealm() {
//        if (user != null) {
//            val config = SyncConfiguration.Builder(
//                user,
//                setOf(ToDo::class)
//            )
//                .initialSubscriptions { sub ->
//                    add(query = sub.query<ToDo>(query = "owner_id == $0", user.id))
//                }
//                .log(LogLevel.ALL)
//                .build()
//            realm = Realm.open(config)
//        }
//    }
//
//    override fun getData(): Flow<List<ToDo>> {
//        return realm.query<ToDo>().asFlow().map { it.list }
//    }
//
//    override suspend fun insertToDo(person: ToDo) {
//        if (user != null) {
//            realm.write {
//                try {
//                    copyToRealm(person.apply { owner_id = user.id })
//                } catch (e: Exception) {
//                    Log.d("MongoRepository", e.message.toString())
//                }
//            }
//        }
//    }
//
//    override suspend fun updateToDo(person: ToDo) {
//        realm.write {
//            val queriedToDo =
//                query<ToDo>(query = "_id == $0", ToDo._id)
//                    .first()
//                    .find()
//            if (queriedToDo != null) {
//                queriedToDo.title = ToDo.title
//            } else {
//                Log.d("MongoRepository", "Queried Person does not exist.")
//            }
//        }
//    }
//
//    override suspend fun deleteToDo(id: ObjectId) {
//        realm.write {
//            try {
//                val person = query<ToDo>(query = "_id == $0", id)
//                    .first()
//                    .find()
//                person?.let { delete(it) }
//            } catch (e: Exception) {
//                Log.d("MongoRepository", "${e.message}")
//            }
//        }
//    }
//}