package com.orti.shoppingtodo

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.InvocationTargetException


/**
 * Factory for creating a ViewModel
 */
class ItemViewModelFactory private constructor(instance: ItemRepository) :
    ViewModelProvider.Factory {
    private val mRepository: ItemRepository = instance
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return try {
            modelClass.getConstructor(ItemRepository::class.java)
                .newInstance(mRepository)

        } catch (e: InstantiationException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: NoSuchMethodException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        } catch (e: InvocationTargetException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        }
    }

    companion object {
        fun createFactory(activity: Activity): ItemViewModelFactory {
            val context = activity.applicationContext
                ?: throw IllegalStateException("Not yet attached to Application")
            return ItemViewModelFactory(ItemRepository.getInstance(context)!!)
        }
    }

}