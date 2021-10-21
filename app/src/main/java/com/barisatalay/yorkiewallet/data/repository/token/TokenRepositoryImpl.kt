package com.barisatalay.yorkiewallet.data.repository.token

import com.barisatalay.yorkiewallet.data.local.dao.TokenDao
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import com.barisatalay.yorkiewallet.data.repository.BaseRepository
import io.reactivex.Single
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
        private val tokenDao: TokenDao,
) : TokenRepository, BaseRepository() {

    override fun getTokenList(walletAddress: String): Single<List<TokenEntity>> {
        return tokenDao.getTokenList(walletAddress)
    }
}