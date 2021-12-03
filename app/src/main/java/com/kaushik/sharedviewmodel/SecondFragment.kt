package com.kaushik.sharedviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.kaushik.sharedviewmodel.databinding.FragmentSecondBinding


class SecondFragment : Fragment(R.layout.fragment_second) {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // observing changes in shared model and updating the editText & textView
        sharedViewModel.name.observe(viewLifecycleOwner,{ name ->
            binding.etName2.setText(name)
            binding.tvName2.text = "second: $name"
        })

        binding.btnSecondFragment.setOnClickListener {

            // saving the input name to shared view model
            val name = binding.etName2.text.toString()
            sharedViewModel.saveName(name)

            /// navigate to first fragment on button click
            Navigation.findNavController(it).navigate(R.id.action_secondFragment_to_firstFragment)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}