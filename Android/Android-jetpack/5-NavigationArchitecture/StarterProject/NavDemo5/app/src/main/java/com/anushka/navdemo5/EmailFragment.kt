package com.anushka.navdemo5


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.anushka.navdemo5.databinding.FragmentEmailBinding
import com.anushka.navdemo5.databinding.FragmentNameBinding

/**
 * A simple [Fragment] subclass.
 */
class EmailFragment : Fragment() {

    private lateinit var binding: FragmentEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container, false)
        val name = requireArguments().getString("name")
        binding.apply {
            submitButton.setOnClickListener{
                if(emailEditText.text.toString().isNotEmpty()){
                    val bundle:Bundle = bundleOf(
                        "email" to emailEditText.text.toString(),
                        "name" to name
                        )
                    //bundle.putString("name",name)
                    it.findNavController().navigate(R.id.action_emailFragment_to_welcomeFragment,bundle)
                }else Toast.makeText(activity,"Escibe texto", Toast.LENGTH_LONG).show()
            }
        }
        return binding.root
    }
}
