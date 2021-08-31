package com.naga.costco.flickr.viewer.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.naga.costco.flickr.viewer.R
import com.naga.costco.flickr.viewer.adapter.ImageRecyclerViewAdapter
import com.naga.costco.flickr.viewer.viewmodel.ImageListViewModel
import kotlinx.android.synthetic.main.fragment_image_list.*

class ImageListFragment : Fragment(), OnClickListener {

    private val TAG = ImageListFragment::class.java.simpleName
    private val imagesViewModel: ImageListViewModel by viewModels()
    private val imagesAdapter = ImageRecyclerViewAdapter()
    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        displayImages()
        view.findViewById<Button>(R.id.search_button).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.search_button -> {
                hideKeyboard(v)
                val editTextHello = searchBox.text.toString()
                imagesViewModel.fetchImages3(editTextHello)
            }
        }
    }

    private fun displayImages() {
        try {
            photosRecyclerView.adapter = imagesAdapter
            photosRecyclerView.layoutManager = GridLayoutManager(activity, 3)

            imagesViewModel.photosLiveData.observe(viewLifecycleOwner,
                Observer { list ->
                    with(imagesAdapter) {
                        photos.clear()
                        photos.addAll(list)
                        notifyDataSetChanged()
                    }
                })
        } catch (e: Exception) {
            Log.e(TAG, "onCreate: " + e.message)
        }
    }

    private fun hideKeyboard(view: View) {
        view.apply {
            val imm = ContextCompat.getSystemService(view.context, InputMethodManager::class.java)
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}