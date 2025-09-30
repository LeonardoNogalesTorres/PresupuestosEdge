package com.example.presupuestosedge

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    // GastoAnalyzer provider
    @Provides
    @Singleton
    fun provideGastoAnalyzer(@ApplicationContext context: Context): GastoAnalyzer {
        return GastoAnalyzer(context)
    }

    @Provides
    fun provideTransaccionDao(db: AppDatabase): TransaccionDao {
        return db.transaccionDao()
    }

    @Provides
    @Singleton
    fun provideTransaccionRepository(db: AppDatabase, gastoAnalyzer: GastoAnalyzer): TransaccionRepository { // Added GastoAnalyzer
        return TransaccionRepository(db, gastoAnalyzer) // Pass GastoAnalyzer
    }
}