package com.mayburger.eatclone.ui.main.explore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mayburger.eatclone.BR
import com.mayburger.eatclone.R
import com.mayburger.eatclone.databinding.FragmentExploreBinding
import com.mayburger.eatclone.model.CategoryDataModel
import com.mayburger.eatclone.model.RestaurantDataModel
import com.mayburger.eatclone.ui.adapters.CategoryAdapter
import com.mayburger.eatclone.ui.adapters.RestaurantAdapter
import com.mayburger.eatclone.ui.base.BaseFragment
import com.mayburger.eatclone.ui.restaurant.RestaurantActivity
import com.mayburger.eatclone.ui.search.SearchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_explore.*
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding, ExploreViewModel>(),
    ExploreNavigator, CategoryAdapter.Callback, RestaurantAdapter.Callback {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_explore
    override val viewModel: ExploreViewModel by viewModels()

    @Inject
    lateinit var restaurantAdapter: RestaurantAdapter

    @Inject
    lateinit var collectionAdapter: RestaurantAdapter

    @Inject
    lateinit var categoriesAdapter: CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        viewDataBinding?.lifecycleOwner = viewLifecycleOwner
        initSort()
        initCategories()
        initCollection()
    }

    fun initCategories() {
        rvCategories.adapter = categoriesAdapter
        categoriesAdapter.setListener(this)
    }

    fun initSort() {
        rvSort.adapter = restaurantAdapter
        restaurantAdapter.setListener(this)
        restaurantAdapter.asGrid()
    }

    fun initCollection() {
        rvCollection.adapter = collectionAdapter
        collectionAdapter.setListener(this)
        collectionAdapter.asCollection()
    }

    override fun onClickSeeAll(){
        RestaurantActivity.startActivity(requireActivity())
    }

    override fun onClickSearch() {
        activity?.let { SearchActivity.startActivity(it) }
    }

    override fun onSelectedItem(restaurant: RestaurantDataModel) {
        activity?.let { RestaurantActivity.startActivity(it, restaurant) }
    }

    override fun onSelectedItem(restaurant: CategoryDataModel) {

    }

}