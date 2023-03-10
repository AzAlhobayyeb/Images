package com.example.images.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*

import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.images.R
import com.example.images.adapter.ImageGridAdpter
import com.example.images.adapter.ImageListener
import com.example.images.databinding.FragmentImageListBinding
import com.example.images.viewmodel.ImageViewModel


class ImageListFragment : Fragment() {

    private val viewModel: ImageViewModel by activityViewModels()
    private val SEARCH_PREFIX = "https://unsplash.com"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentImageListBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.imageList.adapter = ImageGridAdpter(ImageListener { imageData ->
            viewModel.onImageClicked(imageData)
            findNavController().navigate(R.id.action_imageListFragment_to_imageDetailsFragment)
            })
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.image_menu, menu)
        val unsplashMenu = menu.findItem(R.id.unsplash_menu)
        setIcon(unsplashMenu)
    }

    private fun visitUnslash() {
            val queryUri: Uri = Uri.parse(SEARCH_PREFIX)
            val intent = Intent(Intent.ACTION_VIEW, queryUri)
            context?.startActivity(intent)
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.visit_unslash->{ visitUnslash() }
        }
        return super.onOptionsItemSelected(item)
    }

}
