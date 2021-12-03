package com.kaushik.sharedviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.kaushik.sharedviewmodel.databinding.FragmentFirstBinding


class FirstFragment : Fragment(R.layout.fragment_first) {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    // activityViewModel is used to scope the fragment view model to it parent activity
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater,container,false)

        // observing changes in shared model and updating the editText & textView
        sharedViewModel.name.observe(viewLifecycleOwner,{ name ->
            binding.etName.setText(name)
            binding.tvName.text = "first: $name"
        })


        binding.btnFragFirst.setOnClickListener {

            // saving the input name to shared view model
            val inputName = binding.etName.text.toString()
            sharedViewModel.saveName(inputName)

            /// navigate to second fragment on button click
            Navigation.findNavController(it).navigate(R.id.action_firstFragment_to_secondFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}