package ferrandez.daniel.storage

import io.realm.RealmObject

class BillBoardRealmService<T : RealmObject> : RealmService<T, Int>()