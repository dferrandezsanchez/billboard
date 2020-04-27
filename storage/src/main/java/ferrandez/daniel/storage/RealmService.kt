package ferrandez.daniel.storage

import ferrandez.daniel.storage.extensions.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmQuery
import io.realm.Sort

abstract class RealmService<T : RealmObject, ID> {

    inline fun <reified T : RealmObject> findByPrimaryKey(id: ID): Flowable<T> =
        Flowable.defer {
            getPrimaryKeyFieldName(T::class.java)
                ?.let {
                    with(Realm.getDefaultInstance()) {
                        where(T::class.java)
                            .apply {
                                when (id) {
                                    is String -> equalTo(it, id)
                                    is Int -> equalTo(it, id)
                                    else -> throw IllegalArgumentException("Id type not supported by service")
                                }
                            }
                            .findAllAsync()
                            .toFlowableList(this)
                            .filter { it.firstOrNull() != null }
                            .map { it.first() }
                    }
                } ?: throw IllegalArgumentException("object.not.have.primary.key")
        }.addRealmSchedulers()

    inline fun <reified T : RealmObject> findAllSortedAsc(sort: String): Flowable<List<T>> =
        Flowable.defer {
            with(Realm.getDefaultInstance()) {
                where(T::class.java)
                    .findAllAsync()
                    .sort(sort, Sort.ASCENDING)
                    .toFlowableList(this)
            }
        }.addRealmSchedulers()

    inline fun <reified T : RealmObject> findAll(): Flowable<List<T>> =
        Flowable.defer {
            with(Realm.getDefaultInstance()) {
                where(T::class.java)
                    .findAllAsync()
                    .toFlowableList(this)
            }
        }.addRealmSchedulers()

    inline fun <reified T : RealmObject> findAllByPrimaryKey(
        id: Long,
        primaryKey: String
    ): Flowable<List<T>> =
        Flowable.defer {
            with(Realm.getDefaultInstance()) {
                where(T::class.java)
                    .equalTo(primaryKey, id)
                    .findAllAsync()
                    .toFlowableList(this)
            }
        }.addRealmSchedulers()

    inline fun <reified T : RealmObject> findAllByPrimaryKey(
        id: String,
        primaryKey: String
    ): Flowable<List<T>> =
        Flowable.defer {
            with(Realm.getDefaultInstance()) {
                where(T::class.java)
                    .equalTo(primaryKey, id)
                    .findAllAsync()
                    .toFlowableList(this)
            }
        }.addRealmSchedulers()

    fun findByQuery(query: RealmQuery<T>): Flowable<List<T>> =
        Flowable.defer {
            query.findAllAsync()
                .toFlowableList(Realm.getDefaultInstance())
        }.addRealmSchedulers()

    fun save(entity: T): Completable =
        Completable.defer {
            entity.saveManaged()
        }.addRealmSchedulers()

    fun save(entities: List<T>): Completable =
        Completable.defer {
            entities.saveAllManaged()
        }.addRealmSchedulers()

    fun delete(entity: T): Completable =
        Completable.defer {
            entity.deleteManaged()
        }.addRealmSchedulers()

    fun delete(query: RealmQuery<T>): Completable =
        Completable.defer {
            query.findAll().deleteAllManaged()
        }.addRealmSchedulers()

    inline fun <reified T : RealmObject> deleteALL(): Completable =
        Completable.defer {
            Realm.getDefaultInstance().where(T::class.java).findAll().deleteAllManaged()
        }.addRealmSchedulers()
}