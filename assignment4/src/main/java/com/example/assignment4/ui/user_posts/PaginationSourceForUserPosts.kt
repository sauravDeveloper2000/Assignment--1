package com.example.assignment4.ui.user_posts

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.assignment4.api.UserPostsApiService
import com.example.assignment4.model_class.user_posts_model_class.UserPosts
import okio.IOException

private const val FIRST_PAGE = 1

class PaginationSourceForUserPosts(
    private val userPostsApiService: UserPostsApiService
) : PagingSource<Int, UserPosts>() {

    override fun getRefreshKey(state: PagingState<Int, UserPosts>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserPosts> {
        return try {
            val page = params.key ?: FIRST_PAGE
            val response: List<UserPosts> = userPostsApiService.getUsersPosts()
            LoadResult.Page(
                data = response,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }

}